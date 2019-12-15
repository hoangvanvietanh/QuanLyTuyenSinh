package com.gdu.controller;

import java.awt.color.CMMException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.stream.events.StartDocument;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.gdu.entity.Status;
import com.gdu.entity.Student;
import com.gdu.entity.StudentRegistration;
import com.gdu.entity.StudentRegistration;
import com.gdu.model.Model;
import com.jfoenix.controls.JFXButton;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class TestController implements Initializable {

	public List<StudentRegistration> listStudentRegistration = Model.studentsRestrationList;
	
	@FXML
	private Pane paneHome, paneStudent, pane3;

	@FXML
	private JFXButton buttonHome, buttonStudent;

	@FXML
    private TableView<StudentRegistration> tbData;
	@FXML
    public TableColumn<String,String> stt;
	@FXML
    public TableColumn<StudentRegistration, String> status;
    @FXML
    public TableColumn<StudentRegistration, String> full_name;
    @FXML
    public TableColumn<StudentRegistration, String> date_of_birth;
    @FXML
    public TableColumn<StudentRegistration, String> place_of_birth;
    @FXML
    public TableColumn<StudentRegistration, String> math_scores_US;
    @FXML
    public TableColumn<StudentRegistration, String> physical_scores_US;
    @FXML
    public TableColumn<StudentRegistration, String> chemistry_scores_US;
    @FXML
    public TableColumn<StudentRegistration, String> literature_scores_US;
    @FXML
    public TableColumn<StudentRegistration, String> math_scores;
    @FXML
    public TableColumn<StudentRegistration, String> physical_scores;
    @FXML
    public TableColumn<StudentRegistration, String> chemistry_scores;
    @FXML
    public TableColumn<StudentRegistration, String> literature_scores;
    @FXML
    public AnchorPane stageHome;
    
	@FXML
	public void buttonHomeClicked() {
		paneHome.toFront();

	}
	
	@FXML
	public void buttonStudentClicked() {
		paneStudent.toFront();
		

	}
	@FXML
	public void reportClicked() throws IOException
	{
		Stage stage = (Stage) stageHome.getScene().getWindow();
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Chọn đường dẫn lưu file");
		File selectedDirectory = directoryChooser.showDialog(stage);

		if(selectedDirectory == null){
		     //No Directory selected
		}else{
			exportFileExcel(listStudentRegistration,selectedDirectory.getAbsolutePath());
		}
		
	}
	
	@FXML
	public void btnRegistrationClicked() {
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/addStudent.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		   // stage.initModality(Modality.APPLICATION_MODAL);
		   // stage.initStyle(StageStyle.UNDECORATED);
		    stage.setTitle("Đăng ký");
		    stage.setScene(new Scene(root1));  
		    stage.show();
		    stage.setOnHidden(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.out.println("Đã load data");
					loadData();
				}
			});
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Model models = new Model();
		models.getAllStudentRegistration();
		loadData();

	}
	@FXML
    private void btnSelectedRow()
    {
    	StudentRegistration sv = tbData.getSelectionModel().getSelectedItem();
    	System.out.println(sv.getFullName());
    }
	public void loadData()
	{   
		stt.setCellValueFactory(column-> new ReadOnlyObjectWrapper(tbData.getItems().indexOf(column.getValue())+1));
		stt.setSortable(false);
		full_name.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("fullName"));
		date_of_birth.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("dateOfBirth"));
		place_of_birth.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("placeOfBirth"));
		math_scores_US.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("mathScoresOfGraduationTest"));
		physical_scores_US.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("physicsScoresOfGraduationTest"));
		chemistry_scores_US.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("chemistryScoresOfGraduationTest"));
		literature_scores_US.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("literaryScoresOfGraduationTest"));
		math_scores.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("mathScoresInSchoolReport"));
		physical_scores.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("physicsScoresInSchoolReport"));
		chemistry_scores.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("chemistryScoresInSchoolReport"));
		literature_scores.setCellValueFactory(new PropertyValueFactory<StudentRegistration,String>("literaryScoresInSchoolReport"));
		status.setCellValueFactory(new PropertyValueFactory<StudentRegistration, String>("status"));
		ObservableList<StudentRegistration> StudentRegistration = FXCollections.observableArrayList(listStudentRegistration);
		tbData.setItems(StudentRegistration);
		
		ObservableList<String> items = FXCollections.observableArrayList();
		ComboBox<String> cb = new ComboBox<String>();
		cb.getItems().add("1");
		cb.getItems().add("2");
	}
	
	public void exportFileExcel(List<StudentRegistration> listStudent, String pathSaveFile) throws IOException
	{
		List<StudentRegistration> listStudentRegistration = listStudent;
//        TableView<Person> table = new TableView<Person>();
//
//        
//        
//        ObservableList<Person> teamMembers = getTeamMembers();
//        Person p1 = new Person();
//        p1.setFirstName("viet anh");
//        p1.setLastName("Hoang Van");
//        teamMembers.add(p1);
//        
//        Person p2 = new Person();
//        p2.setFirstName("viet anh");
//        p2.setLastName("Hoang Van");
//        teamMembers.add(p2);
//        
//        table.setItems(teamMembers);
		TableView<StudentRegistration> tbData = new TableView<StudentRegistration>();
		TableColumn<StudentRegistration, String> stt = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> full_name = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> date_of_birth = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> place_of_birth = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> math_scores_US = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> physical_scores_US = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> chemistry_scores_US = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> literature_scores_US = new TableColumn<StudentRegistration, String>(
				"");
		TableColumn<StudentRegistration, String> math_scores = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> physical_scores = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> chemistry_scores = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> literature_scores = new TableColumn<StudentRegistration, String>("");
		TableColumn<StudentRegistration, String> status = new TableColumn<StudentRegistration, String>("");

		stt.setCellValueFactory(column -> new ReadOnlyObjectWrapper(tbData.getItems().indexOf(column.getValue()) + 1));
		full_name.setCellValueFactory(new PropertyValueFactory<StudentRegistration, String>("fullName"));
		date_of_birth.setCellValueFactory(new PropertyValueFactory<StudentRegistration, String>("dateOfBirth"));
		place_of_birth.setCellValueFactory(new PropertyValueFactory<StudentRegistration, String>("placeOfBirth"));
		math_scores_US.setCellValueFactory(
				new PropertyValueFactory<StudentRegistration, String>("mathScoresOfGraduationTest"));
		physical_scores_US.setCellValueFactory(
				new PropertyValueFactory<StudentRegistration, String>("physicsScoresOfGraduationTest"));
		chemistry_scores_US.setCellValueFactory(
				new PropertyValueFactory<StudentRegistration, String>("chemistryScoresOfGraduationTest"));
		literature_scores_US.setCellValueFactory(
				new PropertyValueFactory<StudentRegistration, String>("literaryScoresOfGraduationTest"));
		math_scores
				.setCellValueFactory(new PropertyValueFactory<StudentRegistration, String>("mathScoresInSchoolReport"));
		physical_scores.setCellValueFactory(
				new PropertyValueFactory<StudentRegistration, String>("physicsScoresInSchoolReport"));
		chemistry_scores.setCellValueFactory(
				new PropertyValueFactory<StudentRegistration, String>("chemistryScoresInSchoolReport"));
		literature_scores.setCellValueFactory(
				new PropertyValueFactory<StudentRegistration, String>("literaryScoresInSchoolReport"));
		status.setCellValueFactory(new PropertyValueFactory<StudentRegistration, String>("status"));
		ObservableList<StudentRegistration> StudentRegistration = FXCollections
				.observableArrayList(listStudentRegistration);
//		ObservableList<StudentRegistration> title = FXCollections.observableArrayList();
//		StudentRegistration stu = new StudentRegistration();
//		stu.setFullName("Họ tên");
//		stu.setDateOfBirth("Ngày sinh");
//		stu.setPlaceOfBirth("Nơi sinh");
//		stu.setMathScoresInSchoolReport("Điểm");
//		stu.setChemistryScoresInSchoolReport("Điểm");
//		stu.setLiteraryScoresInSchoolReport("Điểm");
//		stu.setPhysicsScoresInSchoolReport("Điểm");
//		stu.setMathScoresOfGraduationTest("Điểm");
//		stu.setPhysicsScoresOfGraduationTest("Điểm");
//		stu.setChemistryScoresOfGraduationTest("Điểm");
//		stu.setLiteraryScoresOfGraduationTest("Điểm");
//		stu.setMathScoresOfGraduationTest("Điểm");
//		stu.setStatus("Trạng thái");
//		title.add(stu);
//		tbData.setItems(title);
		tbData.setItems(StudentRegistration);
//        TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("First Name");
//        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
//        TableColumn<Person,String> lastNameCol = new TableColumn<Person,String>("Last Name");
//        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

//        ObservableList<TableColumn<Person, ?>> columns = table.getColumns();
//        columns.add(firstNameCol);
//        columns.add(lastNameCol);

		ObservableList<TableColumn<StudentRegistration, ?>> columns = tbData.getColumns();
		columns.add(stt);
		columns.add(full_name);
		columns.add(date_of_birth);
		columns.add(place_of_birth);
		columns.add(math_scores_US);
		columns.add(physical_scores_US);
		columns.add(chemistry_scores_US);
		columns.add(literature_scores_US);
		columns.add(math_scores);
		columns.add(physical_scores);
		columns.add(chemistry_scores);
		columns.add(literature_scores);
		columns.add(status);
		Workbook workbook = new HSSFWorkbook();
		Sheet spreadsheet = workbook.createSheet("sample");

		Row row = spreadsheet.createRow(0);

		for (int j = 0; j < tbData.getColumns().size(); j++) {
			row.createCell(j).setCellValue(tbData.getColumns().get(j).getText());
		}

		for (int i = 0; i < tbData.getItems().size(); i++) {
			if (i == 0) {
				row.createCell(0).setCellValue("STT");
				row.createCell(1).setCellValue("Họ tên");
				row.createCell(2).setCellValue("Ngày sinh");
				row.createCell(3).setCellValue("Nơi sinh");
				row.createCell(4).setCellValue("Điểm");
				row.createCell(5).setCellValue("Điểm");
				row.createCell(6).setCellValue("Điểm");
				row.createCell(7).setCellValue("Điểm");
				row.createCell(8).setCellValue("Điểm");
				row.createCell(9).setCellValue("Điểm");
				row.createCell(10).setCellValue("Điểm");
				row.createCell(11).setCellValue("Điểm");
				row.createCell(12).setCellValue("Trạng thái");
			}
			row = spreadsheet.createRow(i + 1);

			for (int j = 0; j < tbData.getColumns().size(); j++) {
				if (tbData.getColumns().get(j).getCellData(i) != null) {
					row.createCell(j).setCellValue(tbData.getColumns().get(j).getCellData(i).toString());
				} else {
					row.createCell(j).setCellValue("");
				}
			}
		}

		FileOutputStream fileOut = new FileOutputStream(pathSaveFile+"/danh_sach_hoc_sinh.xls");
		workbook.write(fileOut);
		fileOut.close();
	}
}
