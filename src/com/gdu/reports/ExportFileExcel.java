package com.gdu.reports;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.gdu.entity.StudentRegistration;
import com.gdu.model.Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportFileExcel extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Model model = new Model();
		model.getAllStudentRegistration();
		List<StudentRegistration> listStudentRegistration = Model.studentsRestrationList;
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

		FileOutputStream fileOut = new FileOutputStream("danh_sach_hoc_sinh.xls");
		workbook.write(fileOut);
		fileOut.close();
		System.out.println("done");
		Platform.exit();

	}
}
