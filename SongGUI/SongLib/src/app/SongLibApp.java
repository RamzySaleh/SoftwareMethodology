package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 
 * @author Ramzy Saleh
 * @author Sara Zayed
 * 	
 */

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import view.SongLibController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ChoiceBox;

public class SongLibApp extends Application { //get functionality for javaFX application
	private Stage primaryStage;
	private AnchorPane rootLayout;
	public static ArrayList<Song> arrayListSongObjects;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Song Application");
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/songlib.fxml"));
			rootLayout = (AnchorPane) loader.load();
			SongLibController listController = loader.getController();
			listController.start(primaryStage);
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add("/view/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			/**
			 * 
			 * TODO Add all the songs to the ListView that were previously found from the text file. 
			 * They are stored in the ArrayList.
			 *  
			 */
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	public static void main(String[] args) {
		
		arrayListSongObjects = new ArrayList<Song>();
		
		File fp = new File("Songs.txt");
		
		// Check if the file exists. If so, load the songs
		if (fp.exists()) loadSongsFromTextFile(fp);
		
		launch(args);
		
		saveSongsToTextFile();
		
	}

	/*
	 * TODO Implement
	 */
	public static void loadSongsFromTextFile(File fp){
		
		try {
			FileInputStream in = new FileInputStream(fp);
			
			/**
			 * TODO
			 * 1. Read the songs, information is separated by '~' <---- tilde. Use delimiter.
			 * 2. Create Song object for each, and add it to the ArrayList.
			 */
			
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * TODO Implement
	 */
	public static void saveSongsToTextFile(){
		
		File fp = new File("Songs.txt");
		
		if (fp.exists()) {
			// Delete the old file!
			fp.delete();
		}
		
		/**
		 * TODO Now save the songs!
		 * 1. Split information by '~'. All fields not given are simple empty strings.
		 * 2. Save one song per line.
		 */

		
	}
	

}
