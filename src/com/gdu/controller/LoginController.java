package com.gdu.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.gdu.config.ConnectMongoDB;
import com.gdu.entity.Admin;
import com.gdu.model.Model;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	TextField txtCodeLogin;
	
	@FXML
	PasswordField txtPassword;
	
	@FXML
	ImageView idImage;
	
	@FXML
	Pane paneAlreadyLogin;
	
	@FXML
	Pane paneLogin;
	
	@FXML
	Label labelNotice;
	
	
	@FXML
	JFXRadioButton radOnline;
	
	@FXML
	JFXRadioButton radOffline;
	
	public String uriOnline ="mongodb+srv://vietanh:113114115@cluster0-jfbgb.mongodb.net/test?retryWrites=true&w=majority";
	public String uriOffline = "mongodb://localhost:27017";
	private Boolean checkLogout = false;
	
	@FXML
	private void btnHome() throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../com/gdu/view/home.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
	}
	
	@FXML
	private void btnLogout()
	{
		paneLogin.toFront();
		checkLogout = true;
	}
	
	@FXML
	private void btnDangNhapclick(ActionEvent event) throws IOException
	{
			if(radOffline.isSelected())
			{
				System.out.println(uriOffline);
				ConnectMongoDB.connectUri= uriOffline;
				Model.historyList.clear();
				Model.studentList.clear();
				Model.studentsRestrationList.clear();
				Model.adminList.clear();
				Model model = new Model();
				model.getAllAdmin();
			}
			else
			{
				System.out.println("online");
				ConnectMongoDB.connectUri= uriOnline;
				Model.historyList.clear();
				Model.studentList.clear();
				Model.studentsRestrationList.clear();
				Model.adminList.clear();
				Model model = new Model();
				model.getAllAdmin();
			}
		
		List<Admin> listadmin = Model.adminList;
		int flag = 0;
		for(Admin admin: listadmin)
		{
			if(admin.getUser().equals(txtCodeLogin.getText()))
			{
				if(admin.getRole().equals("quan_ly_tuyen_sinh"))
				{
					if(BCrypt.checkpw(txtPassword.getText(), admin.getPassword()))
					{
						checkLogout = false;
						labelNotice.setVisible(false);
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../com/gdu/view/home.fxml"));
						Parent root1 = (Parent) fxmlLoader.load();
				        Stage stage = new Stage();
				        stage.setScene(new Scene(root1));  
				        stage.show();
						flag++;
						paneAlreadyLogin.toFront();
					}
				}
				
			}
		}
		if(flag == 0)
		{
			System.out.println("Login fail");
			labelNotice.setText("Tài khoản hoặc mật khẩu sai!!!");
			labelNotice.setVisible(true);
		}
		
	}
	
	
	public LoginController() {
		
	}
	
}
