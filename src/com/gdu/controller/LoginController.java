package com.gdu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	TextField txtCodeLogin;
	
	@FXML
	PasswordField txtPassword;
	
	@FXML
	private void handleButtonAction(ActionEvent event)
	{
		Button btnLogin = ((Button)event.getSource());
		btnLogin.getOnMouseClicked();
		System.out.println("ok");
	}
	
	
	
	
}
