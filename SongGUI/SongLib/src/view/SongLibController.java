package view;

/**
 * 
 * @author Ramzy Saleh
 * @author Sara Zayed
 * 	
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import app.Song;
import app.Song.CustomComparator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
	@FXML Button confirmEdit;
	@FXML Button cancelEdit;
	
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
			 displaySong.setText(songs.get(0).getSongName());
			 displayArtist.setText(songs.get(0).getArtistName());
			 displayAlbum.setText(songs.get(0).getAlbumName());
			 displayYear.setText(songs.get(0).getYear());
		 }

		 songListView
	 	.getSelectionModel()
	 	.selectedItemProperty();
		 
		 songListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
					 showItem(main);
	            }
	     });
	 } 
	 
	 public void showItem(Stage main){
		 int index = songListView.getSelectionModel().getSelectedIndex();
		 if (index == -1) {
			 for (int i = 0; i < songs.size(); i++) {
				 if (songs.get(i).getNewestSong()){
					 index = i;
					 break;
				 }
			 }
		 }
		 if (index == -1) index = 0;
		 songListView.getSelectionModel().clearAndSelect(index);
		 displaySong.setText(songs.get(index).getSongName());
		 displayArtist.setText(songs.get(index).getArtistName());
		 displayAlbum.setText(songs.get(index).getAlbumName());
		 displayYear.setText(songs.get(index).getYear());

	 }
	
	/**
	 * 
	 * addButtonClicked COMPLETED!
	 * 
	 */
	public void addButtonClicked(ActionEvent e){ 
		songListView.getSelectionModel().clearSelection();
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Add New Song");
		dialog.setHeaderText("Enter song name, artist, album, and year.");
		
		Label songLabel = new Label("Song: ");
		Label artistLabel = new Label("Artist: ");
		Label albumLabel = new Label("Album: ");
		Label yearLabel = new Label("Year: ");
		TextField songField = new TextField();
		TextField artistField = new TextField();
		TextField albumField = new TextField();
		TextField yearField = new TextField();

		GridPane grid = new GridPane();
		grid.add(songLabel, 0, 0);
		grid.add(artistLabel, 0, 1);
		grid.add(albumLabel, 0, 2);
		grid.add(yearLabel, 0, 3);
		
		grid.add(songField, 1, 0);
		grid.add(artistField, 1, 1);
		grid.add(albumField, 1, 2);
		grid.add(yearField, 1, 3);
		
		dialog.getDialogPane().setGraphic(grid);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		
		if(result.get() == ButtonType.OK){
			
			String song = songField.getText();
			String artist = artistField.getText();
			String album = albumField.getText();
			String year = yearField.getText();
			
			if(song.equals("") || song == null || artist.equals("") || artist == null){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Songs require both a SONG NAME and ARTIST!");
				alert.show();
				return;
			}
			
			int indexOfSong = searchForSong(songs, song, artist);
			
			if (indexOfSong == -1) {
				// Add the song
				Song newSong = new Song(song, artist, album, year);
				for(int i = 0; i < songs.size(); i++) songs.get(i).setNewestSong(false);
				newSong.setNewestSong(true);
				songs.add(newSong);
				Collections.sort(songs, new CustomComparator());
				obsList.clear();
				int index = 0;
				for(int i = 0; i < songs.size(); i++) obsList.add(songs.get(i).getSongName());
				for(int i = 0; i < songs.size(); i++) if(songs.get(i).getNewestSong()) index = i;;
				songListView.getSelectionModel().clearAndSelect(index);
				displaySong.setText(songs.get(index).getSongName());
				displayArtist.setText(songs.get(index).getArtistName());
				displayAlbum.setText(songs.get(index).getAlbumName());
				displayYear.setText(songs.get(index).getYear());
				FXCollections.sort(obsList, String.CASE_INSENSITIVE_ORDER);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Song already exists! Try again.");
				alert.show();
				return;
			}
		} else {
			return;
		}
		
	
		
	}

	/**
	 * 
	 * editButtonClicked COMPLETED!
	 * 
	 */
	public void editButtonClicked(ActionEvent e){ 
		
		// Make field editable and apply styling
		displaySong.setEditable(true);
		displayArtist.setEditable(true);
		displayAlbum.setEditable(true);
		displayYear.setEditable(true);

		displaySong.getStyleClass().clear(); displaySong.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		displayArtist.getStyleClass().clear(); displayArtist.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		displayAlbum.getStyleClass().clear(); displayAlbum.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		displayYear.getStyleClass().clear(); displayYear.getStyleClass().addAll("text-field", "text-input", "acceptingInput");

		
		
	}
	
	/**
	 * 
	 * deleteButtonClicked
	 * 
	 */
	
	public void deleteButtonClicked(ActionEvent e){ 

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
	 * acceptButtonClicked
	 * 
	 */
	public void confirmEditClicked(ActionEvent e){ 
		
		// Check to see if the user hit accept and they were not editing!
		if(displaySong.isEditable() == false){
			return;
		}
		
		String song = displaySong.getText();
		String artist = displayArtist.getText();
		String album = displayAlbum.getText();
		String year = displayYear.getText();

		int index = songListView.getSelectionModel().getSelectedIndex();
		
		obsList.set(index, song);
		
		songs.get(index).editSong(song);
		songs.get(index).editArtist(artist);
		songs.get(index).editAlbum(album);
		songs.get(index).editYear(year);
		
		Collections.sort(songs, new CustomComparator());
		FXCollections.sort(obsList);
		
		displaySong.setEditable(false);
		displayArtist.setEditable(false);
		displayAlbum.setEditable(false);
		displayYear.setEditable(false);
		
		displaySong.getStyleClass().clear(); displaySong.getStyleClass().addAll("text-field", "text-input", "declineInput");
		displayArtist.getStyleClass().clear(); displayArtist.getStyleClass().addAll("text-field", "text-input", "declineInput");
		displayAlbum.getStyleClass().clear(); displayAlbum.getStyleClass().addAll("text-field", "text-input", "declineInput");
		displayYear.getStyleClass().clear(); displayYear.getStyleClass().addAll("text-field", "text-input", "declineInput");

				
	}
	
	/**
	 * 
	 * cancelButtonClicked COMPLETED!
	 * 
	 */
	public void cancelEditClicked(ActionEvent e){ 
		
		if(displaySong.isEditable() == false){
			return;
		}
		
		displaySong.setEditable(false);
		displayArtist.setEditable(false);
		displayAlbum.setEditable(false);
		displayYear.setEditable(false);
		
		displaySong.getStyleClass().clear(); displaySong.getStyleClass().addAll("text-field", "text-input", "declineInput");
		displayArtist.getStyleClass().clear(); displayArtist.getStyleClass().addAll("text-field", "text-input", "declineInput");
		displayAlbum.getStyleClass().clear(); displayAlbum.getStyleClass().addAll("text-field", "text-input", "declineInput");
		displayYear.getStyleClass().clear(); displayYear.getStyleClass().addAll("text-field", "text-input", "declineInput");

		displaySong.setText(songs.get(0).getSongName());
		displayArtist.setText(songs.get(0).getArtistName());
		displayAlbum.setText(songs.get(0).getAlbumName());
		displayYear.setText(songs.get(0).getYear());
		
	}
	
	public int searchForSong(ArrayList<Song> songs, String songName, String artistName){
		
		for (int j = 0; j < songs.size(); j++){
			if (songs.get(j).equals(songName, artistName)){
				return j;
			}
		}
		
		return -1;
	}
	

}
