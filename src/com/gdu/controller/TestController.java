package com.gdu.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestController implements Initializable {

	@FXML
	private Pane paneHome, paneStudent, pane3;

	@FXML
	private JFXButton buttonHome, buttonStudent;

	@FXML
	public void buttonHomeClicked() {
		paneHome.toFront();

	}
	
	@FXML
	public void buttonStudentClicked() {
		paneStudent.toFront();

	}
	
	@FXML
	public void btnRegistrationClicked() {
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/addStudent.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initStyle(StageStyle.UNDECORATED);
		    stage.setTitle("ABC");
		    stage.setScene(new Scene(root1));  
		    stage.show();
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
