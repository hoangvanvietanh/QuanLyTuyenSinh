package com.gdu.controller;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.gdu.entity.History;
import com.gdu.entity.StudentRegistration;
import com.gdu.model.Model;
import com.gdu.reports.PrintReport;
import com.gdu.ultils.GMail;
import com.gdu.ultils.GetCurrentPath;
import com.gdu.ultils.Time;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class ProgressController {

	@FXML
	private Pane paneProgress;

	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private AnchorPane indexPane;

	@FXML
	private ProgressIndicator progressIndicator;

	@FXML
	private Pane paneQuesion;

	@FXML
	private JFXButton btnAgree;

	@FXML
	private JFXProgressBar barProgress;
	
	@FXML
	private JFXButton btnExit;
	
	@FXML
	private JFXButton btnStart;

	@FXML
	private Label labelNotice;

	private StudentRegistration studentRegistration;

	private VBox vBox;

	private Label label;

	double percentage = 0;

	
	
	@FXML
	void btnAgreeClicked() throws Exception {
		start(studentRegistration);
	}

	@FXML
	void btnExitClicked() {

	}
	
	@FXML
	void btnStart() {
		if(percentage >= 1)
		{
			Stage stage = (Stage) indexPane.getScene().getWindow();
			stage.close();
		}
		else
		{
			barProgress.setProgress(barProgress.INDETERMINATE_PROGRESS);
			start(studentRegistration);
			btnStart.setText("Đóng");
		}
	}

	private void createNotice(String text) {
		percentage = percentage + 0.077;
		progressIndicator.setProgress(percentage);
		label = new Label();
		label.setText(text);
		vBox.getChildren().add(label);
		if(percentage>=1)
		{
			barProgress.setProgress(1);
		}
	}

	public String getDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void start(StudentRegistration selectedStudent) {
		//paneProgress.toFront();
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		createTask(time.format(formatter) +" - Bắt đầu.......1/13",9);
		try {
			createTask(getDate() +" - Khởi tạo file pdf....2/13",10);
			PrintReport printReport = new PrintReport();
			createTask(getDate() +" - Chuẩn bị lưu file report....3/13",11);
			printReport.saveReport(selectedStudent);
			createTask(getDate() +" - File report lưu thành công....4/13",12);
			
			createTask(getDate() +" - Chuẩn bị cập nhật trạng thái của thí sinh....5/13",13);
			Model model = new Model();
			selectedStudent.setStatus("Trúng tuyển");
			model.updateStudents(selectedStudent);
			createTask(getDate() +" - Trạng thái của thí sinh " + selectedStudent.getFullName() +" được cập nhật....6/13" , 14);
			int index = -1;
			createTask(getDate() +" - Cập nhật thông tin trên hệ thống...7/13", 15);
			for (StudentRegistration students : Model.studentsRestrationList) {
				if (students.getStudentCode().equals(selectedStudent.getStudentCode())) {
					index = Model.studentsRestrationList.indexOf(students);
				}
			}
			if (index != -1) {
				Model.studentsRestrationList.remove(index);
				Model.studentsRestrationList.add(selectedStudent);
				createTask(getDate() +" - Cập nhật thành công...8/13",16);
				History history = new History();
				history.setDate(Time.getDateTime());
				history.setContent("Thí sinh " + selectedStudent.getFullName() + " có mã số "
						+ selectedStudent.getStudentCode() + " trúng tuyển");
				model.insertHistory(history);
				Model.historyList.add(history);
				createTask(getDate() +" - Cập nhật thành công lịch sử sửa đổi....9/13",17);
				createTask(getDate() +" - Chuẩn bị gửi mail thông báo cho thí sinh...10/13",18);
				GMail.sendMail(selectedStudent);
				createTask(getDate() +" - Email đã được gửi thành công...11/13", 19);
				createTask(getDate() +" - Kết thúc..12/13", 20);
				createTask(getDate() +" - Hoàn tất quá trình thông báo report..13/13", 21);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createTask(String text,long second)
	{
		Task<Void> sleeper = new Task<Void>() {
            protected Void call() throws Exception {
                try {
                    Thread.sleep(second*1000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
                createNotice(text);
            }
        });
        new Thread(sleeper).start();
	}

	public void setData(StudentRegistration selectedStudent) {
		vBox = new VBox();
		anchorPane.getChildren().add(vBox);
		label = new Label();
		label.setText("Lưu ý: Khi bấm bắt đầu sẽ mất 10s để đấy dữ liệu...");
		label.setStyle("-fx-text-fill: #DF0101;-fx-font-size: 15px;");
		vBox.getChildren().add(label);
		this.studentRegistration = selectedStudent;
	}	
}
