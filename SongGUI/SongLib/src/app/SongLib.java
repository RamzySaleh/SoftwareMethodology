package app;

/**
 * 
 * @author Ramzy Saleh
 * @author Sara Zayed
 * 	
 */

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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import app.Song.CustomComparator;
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

public class SongLib extends Application { //get functionality for javaFX application
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
			listController.start(primaryStage, arrayListSongObjects);
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add("/view/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	public static void main(String[] args) {
		
		arrayListSongObjects = new ArrayList<Song>();
		
		File fp = new File("Songs.txt");
		
		// Check if the file exists. If so, load the songs
		if (fp.exists()) loadSongsFromTextFile(fp);
		
		
		System.out.println("Size of arraylistsong = " + arrayListSongObjects.size());
		
		for(int i = 0; i < arrayListSongObjects.size(); i++){
		
			System.out.println("Testing: "+ arrayListSongObjects.get(i).getSongName());
			
		}
		launch(args);
		
		saveSongsToTextFile();
		
	}


	public static void loadSongsFromTextFile(File fp){
		
		try {
			Scanner in = new Scanner(fp);
			
			while(in.hasNext()){
				String token[] = in.nextLine().split("~");
				for(int i = 0; i < 4; i++){
					if(token[i] == null){
						token[i] = "";
					}
				} //not sure if this for loop is necessary but JUST IN CASE
				
				Song hello = new Song(token[0], token[1], token[2], token[3]);
				arrayListSongObjects.add(hello);
			}
			
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Collections.sort(arrayListSongObjects, new CustomComparator());
		
	}
	
	public static void saveSongsToTextFile(){
		
		File fp = new File("Songs.txt");
		
		if (fp.exists()) {
			// Delete the old file!
			fp.delete();
		}
		
		PrintWriter writer;
		
		try {
			writer = new PrintWriter("Songs.txt", "UTF-8");
			
			for (int i = 0; i < arrayListSongObjects.size(); i++) {
				String songAsString = "";
				String songName = arrayListSongObjects.get(i).getSongName();
				String artistName = arrayListSongObjects.get(i).getArtistName();
				String albumName = arrayListSongObjects.get(i).getAlbumName();
				String year = arrayListSongObjects.get(i).getYear();
				
				if (songName.equals("") || songName == null) songName = " ";
				if (artistName.equals("") || songName == null) artistName = " ";
				if (albumName.equals("") || songName == null) albumName = " ";
				if (year.equals("") || songName == null) year = " ";
				
				songAsString = songName+"~"+artistName+"~"+albumName+"~"+year;

				writer.println(songAsString);
			}
		
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	

}
