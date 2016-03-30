package app;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class PhotoAlbum extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private LoginController loginController;
	
	@Override
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/login.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Sign in");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
