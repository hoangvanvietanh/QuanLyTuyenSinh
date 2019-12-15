package com.gdu.controller;

import com.gdu.entity.StudentRegistration;
import com.gdu.model.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class AddStudentController {

	@FXML
	JFXTextField txtStudentCode;
	@FXML
	JFXTextField txtFullName;
	@FXML
	JFXTextField txtPlaceOfBirth;
	@FXML
	JFXTextField txtIdOfStudent;
	@FXML
	JFXTextField txtPhoneNumber;
	@FXML
	JFXTextField txtAddress;
	@FXML
	JFXTextField txtSubDistrict;
	@FXML
	JFXTextField txtDistrict;
	@FXML
	JFXTextField txtProvince;
	@FXML
	JFXTextField txtNameOfFather;
	@FXML
	JFXTextField txtPhoneNumberOfFather;
	@FXML
	JFXTextField txtNameOfMother;
	@FXML
	JFXTextField txtPhoneNumberOfMother;
	@FXML
	JFXTextField txtMathScoresInSchoolReport;
	@FXML
	JFXTextField txtChemistryScoresInSchoolReport;
	@FXML
	JFXTextField txtPhysicsScoresInSchoolReport;
	@FXML
	JFXTextField txtLiteraryScoresInSchoolReport;
	@FXML
	JFXTextField txtMathScoresOfGraduationTest;
	@FXML
	JFXTextField txtChemistryScoresOfGraduationTest;
	@FXML
	JFXTextField txtPhysicsScoresOfGraduationTest;
	@FXML
	JFXTextField txtLiteraryScoresOfGraduationTest;
	@FXML
	JFXTextField txtAddressSchool;
	@FXML
	JFXTextField txtDistrictSchool;
	@FXML
	JFXTextField txtSubDistrictSchool;
	@FXML
	JFXTextField txtProvinceSchool;
	@FXML
	JFXTextField txtAddressNow;
	@FXML
	JFXTextArea txtNotes;
	@FXML
	JFXDatePicker dateOfBirth;
	@FXML
	JFXDatePicker dateApply;
	@FXML
	JFXComboBox<String> cbCodeOfPlace;
	@FXML
	JFXComboBox<String> cbMajor;
	@FXML
	JFXRadioButton radMale;
	@FXML
	JFXRadioButton radFemale;
	@FXML
	JFXCheckBox checkboxRegistrationForm;
	@FXML
	JFXCheckBox checkboxPriorityPaper;
	@FXML
	JFXCheckBox checkboxSchoolReport;
	@FXML
	JFXCheckBox checkboxDiploma;
	@FXML
	JFXCheckBox checkboxGraduationCertificate;
	@FXML
	JFXCheckBox checkboxCopyOfID;
	@FXML
	JFXCheckBox checkboxImage;
	@FXML
	JFXCheckBox checkboxBirthCertificate;

	@FXML
	private JFXButton btnSave;
	
	@FXML
	private AnchorPane stageInformationOfStudent;
	
	@FXML
	private void saveClicked() {
		System.out.println(txtStudentCode.getText());
		Model model = new Model();
		StudentRegistration student = new StudentRegistration();
		//Thông tin chung
		student.setStudentCode(txtStudentCode.getText());
		student.setFullName(txtFullName.getText());
		student.setPlaceOfBirth(txtPlaceOfBirth.getText());
		student.setIdOfStudent(txtIdOfStudent.getText());
		student.setPhoneNumber(txtPhoneNumber.getText());
	    student.setDateOfBirth(dateOfBirth.getValue().toString());
		student.setDateApply(dateApply.toString());
		student.setGender("Nam");
		student.setAreaCode(cbCodeOfPlace.getValue());
		student.setMajorRegistration(cbMajor.getValue());
		//Thông tin hộ khẩu
		student.setAddress(txtAddress.getText());
		student.setSubDistrict(txtSubDistrict.getText());
		student.setDistrict(txtDistrict.getText());
		student.setProvince(txtProvince.getText());
		//Thông tin phụ huynh
		student.setNameOfFather(txtNameOfFather.getText());
		student.setPhoneNumberOfFather(txtPhoneNumberOfFather.getText());
		student.setNameOfMother(txtNameOfMother.getText());
		student.setPhoneNumberOfMother(txtPhoneNumberOfMother.getText());
		//Điểm học bạ
		student.setMathScoresInSchoolReport(txtMathScoresInSchoolReport.getText());
		student.setChemistryScoresInSchoolReport(txtChemistryScoresInSchoolReport.getText());
		student.setPhysicsScoresInSchoolReport(txtPhysicsScoresInSchoolReport.getText());
		student.setLiteraryScoresInSchoolReport(txtLiteraryScoresInSchoolReport.getText());
		//Điểm thi tốt nghiệp
		student.setMathScoresOfGraduationTest(txtMathScoresOfGraduationTest.getText());
		student.setChemistryScoresOfGraduationTest(txtChemistryScoresOfGraduationTest.getText());
		student.setPhysicsScoresOfGraduationTest(txtPhysicsScoresOfGraduationTest.getText());
		student.setLiteraryScoresOfGraduationTest(txtLiteraryScoresOfGraduationTest.getText());
		//Thông tin trường cấp 3
		student.setAddressSchool(txtAddressSchool.getText());;
		student.setDistrictSchool(txtDistrictSchool.getText());
		student.setSubDistrictSchool(txtSubDistrictSchool.getText());
		student.setProvinceSchool(txtProvinceSchool.getText());
		//Thông tin thêm
		student.setAddressNow(txtAddressNow.getText());
		student.setNotes(txtNotes.getText());	
		student.setStatus("Chờ duyệt");
		model.insertStudents(student);
		Model.studentsRestrationList.add(student);
		Stage stage = (Stage) stageInformationOfStudent.getScene().getWindow();
		stage.close();
	}
}
