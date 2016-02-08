package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ChoiceBox;

public class SongLib extends Application { //get functionality for javaFX application
	Button button;
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Song Application");
		button = new Button();
		button.setText("Just a test button! Cool!");
		button.setOnAction(e -> {
			
		System.out.println("hey now");
		
		});
		
		ChoiceBox<String> songs = new ChoiceBox<String>();
		songs.getItems().add("Apples");
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args); //method inside application class that sets up program as javaFX application
	//calls a method called start
	}

	

}
