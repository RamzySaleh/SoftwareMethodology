package app;
	
import java.util.ArrayList;

import javafx.application.Application;
import view.LoginController;
import view.MainController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class PhotoAlbum extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private ArrayList<User> users;
	@Override
	
	/**
	 * TODO
	 * Save list of users. Each user should be associated with its respective albums
	 */
	
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
			/**
			 * BorderPane root = new BorderPane();
			 * Scene scene = new Scene(root,400,400);
			 * scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 * primaryStage.setScene(scene);
			 * primaryStage.show();
			 */
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
