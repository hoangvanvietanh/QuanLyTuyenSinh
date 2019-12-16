package com.gdu.controller;

import java.time.LocalDate;

import com.gdu.entity.Student;
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
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class AddStudentController {
	private String modeUpdateOrNew = "";
	private StudentRegistration studentUpdate = new StudentRegistration();
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
	JFXTextField email;
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

	public void setData(StudentRegistration student) {
		studentUpdate = student;
		txtStudentCode.setText(student.getStudentCode());
		txtFullName.setText(student.getFullName());
		txtAddress.setText(student.getAddress());
		txtAddressNow.setText(student.getAddressNow());
		txtAddressSchool.setText(student.getAddressSchool());
		txtChemistryScoresInSchoolReport.setText(student.getChemistryScoresInSchoolReport());
		txtChemistryScoresOfGraduationTest.setText(student.getChemistryScoresOfGraduationTest());
		txtDistrict.setText(student.getDistrict());
		txtDistrictSchool.setText(student.getDistrictSchool());
		txtIdOfStudent.setText(student.getIdOfStudent());
		txtLiteraryScoresInSchoolReport.setText(student.getLiteraryScoresInSchoolReport());
		txtLiteraryScoresOfGraduationTest.setText(student.getChemistryScoresOfGraduationTest());
		txtMathScoresInSchoolReport.setText(student.getMathScoresInSchoolReport());
		txtMathScoresOfGraduationTest.setText(student.getMathScoresOfGraduationTest());
		txtNameOfFather.setText(student.getNameOfFather());
		txtNameOfMother.setText(student.getNameOfMother());
		txtNotes.setText(student.getNotes());
		txtPhoneNumber.setText(student.getPhoneNumber());
		txtPhoneNumberOfFather.setText(student.getPhoneNumberOfFather());
		txtPhoneNumberOfMother.setText(student.getPhoneNumberOfMother());
		txtPhysicsScoresInSchoolReport.setText(student.getPhysicsScoresInSchoolReport());
		txtPhysicsScoresOfGraduationTest.setText(student.getPhysicsScoresOfGraduationTest());
		txtPlaceOfBirth.setText(student.getPlaceOfBirth());
		txtProvince.setText(student.getProvince());
		txtProvinceSchool.setText(student.getProvinceSchool());
		txtSubDistrict.setText(student.getSubDistrict());
		txtSubDistrictSchool.setText(student.getSubDistrictSchool());
		email.setText(student.getEmail());
		modeUpdateOrNew = "update";
		LocalDate dateOfBirthChange = LocalDate.parse(student.getDateOfBirth());
		dateOfBirth.setValue(dateOfBirthChange);
	}

	@FXML
	private void saveClicked() {
		System.out.println(txtStudentCode.getText());
		Model model = new Model();
		StudentRegistration student = new StudentRegistration();
		if(modeUpdateOrNew.equals("update"))
		{
			System.out.println("ok update");
			student = studentUpdate;
		}
		else
		{
			student.setStudentCode(String.valueOf(Model.studentsRestrationList.size()));
		}
		// Thông tin chung
		student.setFullName(txtFullName.getText());
		student.setPlaceOfBirth(txtPlaceOfBirth.getText());
		student.setIdOfStudent(txtIdOfStudent.getText());
		student.setPhoneNumber(txtPhoneNumber.getText());
		student.setDateOfBirth(dateOfBirth.getValue().toString());
		student.setEmail(email.getText());
		student.setDateOfBirth(dateOfBirth.getValue().toString());
		if(radMale.isSelected())
    	{
			student.setGender("Nam");
    	}
    	else
    	{
    		student.setGender("Nữ");
    	}
		student.setAreaCode(cbCodeOfPlace.getValue());
		student.setMajorRegistration(cbMajor.getValue());
		// Thông tin hộ khẩu
		student.setAddress(txtAddress.getText());
		student.setSubDistrict(txtSubDistrict.getText());
		student.setDistrict(txtDistrict.getText());
		student.setProvince(txtProvince.getText());
		// Thông tin phụ huynh
		student.setNameOfFather(txtNameOfFather.getText());
		student.setPhoneNumberOfFather(txtPhoneNumberOfFather.getText());
		student.setNameOfMother(txtNameOfMother.getText());
		student.setPhoneNumberOfMother(txtPhoneNumberOfMother.getText());
		// Điểm học bạ
		student.setMathScoresInSchoolReport(txtMathScoresInSchoolReport.getText());
		student.setChemistryScoresInSchoolReport(txtChemistryScoresInSchoolReport.getText());
		student.setPhysicsScoresInSchoolReport(txtPhysicsScoresInSchoolReport.getText());
		student.setLiteraryScoresInSchoolReport(txtLiteraryScoresInSchoolReport.getText());
		// Điểm thi tốt nghiệp
		student.setMathScoresOfGraduationTest(txtMathScoresOfGraduationTest.getText());
		student.setChemistryScoresOfGraduationTest(txtChemistryScoresOfGraduationTest.getText());
		student.setPhysicsScoresOfGraduationTest(txtPhysicsScoresOfGraduationTest.getText());
		student.setLiteraryScoresOfGraduationTest(txtLiteraryScoresOfGraduationTest.getText());
		// Thông tin trường cấp 3
		student.setAddressSchool(txtAddressSchool.getText());
		
		student.setDistrictSchool(txtDistrictSchool.getText());
		student.setSubDistrictSchool(txtSubDistrictSchool.getText());
		student.setProvinceSchool(txtProvinceSchool.getText());
		// Thông tin thêm
		student.setAddressNow(txtAddressNow.getText());
		student.setNotes(txtNotes.getText());
		student.setEmail(email.getText());
		
		if(modeUpdateOrNew.equals("update"))
		{
			model.updateStudents(student);
			int index = -1;
        	for(StudentRegistration students : Model.studentsRestrationList)
        	{
        		if(students.getStudentCode().equals(txtStudentCode.getText()))
        		{
        			index = Model.studentsRestrationList.indexOf(students);
        			
        		}
        	}
        	if(index != -1)
        	{
        		System.out.println("index: " + index);
        		System.out.println("Chỉnh sửa ok");
        		Model.studentsRestrationList.remove(index);
        		Model.studentsRestrationList.add(student);
        		System.out.println(student.getDateOfBirth());
        	}
		}
		else
		{
			student.setStatus("Chờ duyệt");
			model.insertStudents(student);
			Model.studentsRestrationList.add(student);
		}
		
		Stage stage = (Stage) stageInformationOfStudent.getScene().getWindow();
		stage.close();
	}

}
