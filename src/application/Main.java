package application;
	
import com.gdu.model.Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Model model = new Model();
			model.getAllStudent();
			Parent root = FXMLLoader.load(getClass()
	                   .getResource("../com/gdu/view/login.fxml"));
	           primaryStage.setTitle("My Application");
	           primaryStage.setScene(new Scene(root));
	           primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
