package com.gdu.ultils;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Progress extends Application  {

	public void setProgress(double percent)
	{
		Stage stage = new Stage();
		 Group root = new Group();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.initStyle(StageStyle.UNDECORATED);
	        stage.setTitle("Đang thực hiện");
	 
//	        final Slider slider = new Slider();
//	        slider.setMin(0);
//	        slider.setMax(50);
	         
	        final ProgressBar pb = new ProgressBar(0);
	        final ProgressIndicator pi = new ProgressIndicator(0);
	 
	        
	        //pb.setProgress(0.1);
	        pi.setProgress(0.1);
	        pi.setMinHeight(150);
	        pi.setMinWidth(150);
//	        slider.valueProperty().addListener(
//	            (ObservableValue<? extends Number> ov, Number old_val, 
//	            Number new_val) -> {
//	               
//	        });
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        
	        final HBox hb = new HBox();
	        hb.setSpacing(5);
	        hb.setAlignment(Pos.CENTER);
	        hb.getChildren().addAll(pi);
	        
	        final VBox vb = new VBox();
	        vb.getChildren().add(hb);
	        
	        grid.getChildren().add(vb);
	        
	        scene.setRoot(grid);
	        stage.show();
	}

	public void exit()
	{
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
