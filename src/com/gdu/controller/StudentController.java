package com.gdu.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gdu.entity.Student;
import com.gdu.model.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController implements Initializable{

	@FXML
    private TableView<Student> tbData;
    @FXML
    public TableColumn<Student, String> full_name;
    @FXML
    public TableColumn<Student, String> student_code;
    @FXML
    public TableColumn<Student, String> identity_card_number;
    @FXML
    public TableColumn<Student, String> date_of_birth;
    @FXML
    public TableColumn<Student, String> sex;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		full_name.setCellValueFactory(new PropertyValueFactory<Student,String>("full_name"));
		student_code.setCellValueFactory(new PropertyValueFactory<Student,String>("student_code"));
		sex.setCellValueFactory(new PropertyValueFactory<Student,String>("sex"));
		identity_card_number.setCellValueFactory(new PropertyValueFactory<Student,String>("identity_card_number"));
		date_of_birth.setCellValueFactory(new PropertyValueFactory<Student,String>("date_of_birth"));
		
		List<Student> listStudent = Model.studentList;
		ObservableList<Student> student = FXCollections.observableArrayList(listStudent);
		tbData.setItems(student);
	}
}
