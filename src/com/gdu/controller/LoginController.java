package com.gdu.controller;

import java.io.IOException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.gdu.entity.Student;
import com.gdu.model.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	TextField txtCodeLogin;
	
	@FXML
	PasswordField txtPassword;
	
	@FXML
	private void btnDangNhapclick(ActionEvent event) throws IOException
	{
		List<Student> listStudent = Model.studentList;
		int flag = 0;
		for(Student student: listStudent)
		{
			if(student.getStudent_code().equals(txtCodeLogin.getText()))
			{
				if(BCrypt.checkpw(txtPassword.getText(), student.getPassword()))
				{
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../com/gdu/view/home.fxml"));
					Parent root1 = (Parent) fxmlLoader.load();
			        Stage stage = new Stage();
			        stage.setScene(new Scene(root1));  
			        stage.show();
					flag++;
				}
			}
		}
		if(flag == 0)
		{
			System.out.println("Login fail");
		}
		
	}
	
	
	
	
}
