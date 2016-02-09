package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import app.Song;
import app.Song.CustomComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
public class SongLibController {
	
	@FXML ListView<String> songListView;
	
	@FXML TextField displaySong;
	@FXML TextField displayArtist;
	@FXML TextField displayAlbum;
	@FXML TextField displayYear;
	@FXML Button addButton;
	@FXML Button editButton;
	@FXML Button deleteButton;
	
	@FXML TextField songTextField;
	@FXML TextField artistTextField;
	@FXML TextField albumTextField;
	@FXML TextField yearTextField;
	@FXML Button acceptButton;
	@FXML Button cancelButton;
	
	private ObservableList<String> obsList;
	private ArrayList<Song> songs;
	 public void start(Stage main, ArrayList<Song> songs) {
	 // create an ObservableList
	 // from an ArrayList
		 obsList = FXCollections.observableArrayList();
		 this.songs = songs;
		 for(int i = 0; i < songs.size(); i++){
			 if(songs.isEmpty()) break;
			 obsList.add(songs.get(i).getSongName());
		 }

		 songListView.setItems(obsList);
		 songListView.getSelectionModel().select(0);
		 if(!songs.isEmpty()){
			 displaySong.appendText(songs.get(0).getSongName());
			 displayArtist.appendText(songs.get(0).getArtistName());
			 displayAlbum.appendText(songs.get(0).getAlbumName());
			 displayYear.appendText(songs.get(0).getYear());
		 }
	 
	 songListView
	 	.getSelectionModel()
	 	.selectedItemProperty()
	 	.addListener((obs, oldVal, newVal) -> showItem(main));
	 } 
	 
	 public void showItem(Stage main){

		 displaySong.clear();
		 displayArtist.clear();
		 displayAlbum.clear();
		 displayYear.clear();
		 displaySong.appendText(songs.get(songListView.getSelectionModel().getSelectedIndex()).getSongName());
		 displayArtist.appendText(songs.get(songListView.getSelectionModel().getSelectedIndex()).getArtistName());
		 displayAlbum.appendText(songs.get(songListView.getSelectionModel().getSelectedIndex()).getAlbumName());
		 displayYear.appendText(songs.get(songListView.getSelectionModel().getSelectedIndex()).getYear());
	 }
	
	/**
	 * 
	 * addButtonClicked COMPLETED!
	 * 
	 */
	public void addButtonClicked(ActionEvent e){ 
		System.out.println("Add BUTTON CLICKED!");
		
		// Make field editable and apply styling
		songTextField.setEditable(true);
		artistTextField.setEditable(true);
		albumTextField.setEditable(true);
		yearTextField.setEditable(true);
		songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		
	
		
	}

	/**
	 * 
	 * editButtonClicked COMPLETED!
	 * 
	 */
	public void editButtonClicked(ActionEvent e){ 
		System.out.println("Edit BUTTON CLICKED!");
		
		// Load display information so we can edit it
		songTextField.setText(displaySong.getText());
		artistTextField.setText(displayArtist.getText());
		albumTextField.setText(displayAlbum.getText());
		yearTextField.setText(displayYear.getText());
		
		// Make field editable and apply styling
		songTextField.setEditable(true);
		artistTextField.setEditable(true);
		albumTextField.setEditable(true);
		yearTextField.setEditable(true);
		songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		
		
	}
	
	/**
	 * 
	 * deleteButtonClicked NEEDS WORK, see TODO
	 * 
	 */
	
	public void deleteButtonClicked(ActionEvent e){ 
		System.out.println("Delete BUTTON CLICKED!");
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Song");
		alert.setHeaderText("Are you sure you want to delete?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
			String name = displaySong.getText();
			String artist = displayArtist.getText();
			int i = searchForSong(songs, name, artist);
			songs.remove(i);
			obsList.remove(i);
		}
	}
	
	
	
	/**
	 * 
	 * acceptButtonClicked NEEDS WORK, see TODO
	 * 
	 */
	public void acceptButtonClicked(ActionEvent e){ 
		System.out.println("Accept BUTTON CLICKED!");
		
		
		// Check to see if the user hit accept and they were not editing or adding!
		if(songTextField.isEditable() == false){
			return;
		}
		
		String song = songTextField.getText();
		String artist = artistTextField.getText();
		String album = albumTextField.getText();
		String year = yearTextField.getText();
		
		int indexOfSong = searchForSong(songs, song, artist);
		
		if (indexOfSong == -1) {
			// Add the song
			System.out.println("Line 175");
			Song newSong = new Song(song, artist, album, year);
			songs.add(newSong);
			Collections.sort(songs, new CustomComparator());
			obsList.add(song);
			FXCollections.sort(obsList);
			
			// Stop field from being editable and apply styling
			songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
			artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
			albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
			yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
			songTextField.setEditable(false);
			artistTextField.setEditable(false);
			albumTextField.setEditable(false);
			yearTextField.setEditable(false);
			// Clear information after use
			songTextField.clear();
			artistTextField.clear();
			albumTextField.clear();
			yearTextField.clear();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("Song already exists! Try again.");
			alert.show();
			return;
		}
				
	}
	
	/**
	 * 
	 * cancelButtonClicked COMPLETED!
	 * 
	 */
	public void cancelButtonClicked(ActionEvent e){ 
		System.out.println("Cancel BUTTON CLICKED!");
		
		// Clear Information from add/delete, user would like to cancel
		songTextField.clear();
		artistTextField.clear();
		albumTextField.clear();
		yearTextField.clear();
		
		// Stop field from being editable and apply styling
		songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		songTextField.setEditable(false);
		artistTextField.setEditable(false);
		albumTextField.setEditable(false);
		yearTextField.setEditable(false);
	}
	
	public int searchForSong(ArrayList<Song> songs, String songName, String artistName){
		
		for (int j = 0; j < songs.size(); j++){
			if (songs.get(j).equals(songName, artistName)){
				System.out.println("Found same song!");
				return j;
			}
		}
		
		return -1;
	}
	

}
