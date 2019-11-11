package com.gdu.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class TestController implements Initializable {

	@FXML
	private Pane paneTrangChu, paneSinhVien, pane3;

	@FXML
	private JFXButton buttonTrangChu, buttonSinhVien, button3;

	@FXML
	public void buttonTrangChuClicked() {
		paneTrangChu.toFront();

	}
	
	@FXML
	public void buttonSinhVienClicked() {
		paneSinhVien.toFront();

	}
	
	@FXML
	public void button3Clicked() {
		pane3.toFront();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
