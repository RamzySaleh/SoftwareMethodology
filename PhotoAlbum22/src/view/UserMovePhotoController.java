package view;
import java.awt.Desktop;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import app.Album;
import app.Photo;
import app.User;
import view.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;

public class UserMovePhotoController {
	@FXML Button OKButton;
	@FXML Button cancelButton;
	@FXML ListView<String> albumListView;
	Image imageToBeMoved;
	int currentIndex;
	Album currentAlbum;
	private ObservableList<String> obsList;
	
	public void start(Image image, int imageIndex, Album album){
		currentIndex = imageIndex;
		currentAlbum = album;
		User currentUser = LoginController.currentUser;
		imageToBeMoved = image;
		ArrayList<Album> albumList = LoginController.currentUser.getAlbums();
		obsList = FXCollections.observableArrayList();
		 for(int i = 0; i < (currentUser.getAlbums()).size(); i++){
			 if((currentUser.getAlbums()).isEmpty()) break;
			 obsList.add((currentUser.getAlbums()).get(i).getName());
		 }
		albumListView.setItems(obsList);
	}
	
	public void OKButtonClicked(ActionEvent e){
		//First, we move the photo to the album the User wants to move it to
		int indexOfOldAlbum = 0;
		int indexOfNewAlbum = albumListView.getSelectionModel().getSelectedIndex();
		User currentUser = LoginController.currentUser;
		ArrayList<Album> albums = currentUser.getAlbums();
		Album albumOfChoice = albums.get(indexOfNewAlbum);
		albumOfChoice.addOnePhoto(currentAlbum.getPhotos().get(currentIndex));
		//Removing old album without photo
		albums.remove(indexOfNewAlbum);
		//Add album with photo added
		albums.add(indexOfNewAlbum,albumOfChoice);
		LoginController.currentUser.setAlbums(albums);
		
		//Next, we remove the photo from the old album
		//First, find the index of the current Album
		for(int i = 0; i < albums.size(); i++){
			if(albums.get(i).getName().equals(currentAlbum.getName())){
				indexOfOldAlbum = i;
				break;
			}
		}
		//Remove the photo from this album
		Album albumToBeChanged = albums.get(indexOfOldAlbum);
		ArrayList<Photo> photos = albumToBeChanged.getPhotos();
		photos.remove(currentIndex);
		albumToBeChanged.setPhotos(photos);
		//Removing old album with photo
		albums.remove(indexOfOldAlbum);
		//Add album with photo removed
		albums.add(indexOfOldAlbum,albumToBeChanged);
		LoginController.currentUser.setAlbums(albums);
		
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("userPhoto.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			UserPhotoController userPhotoController = loader.getController();
			userPhotoController.start(currentAlbum);
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add("/view/application.css");
			stage.setScene(scene);
			((Node)e.getSource()).getScene().getWindow().hide();
			stage.show();
		}
		catch (Exception z) {
			z.printStackTrace();
		}
		
		
	}
	public void cancelButtonClicked(ActionEvent e){
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("userPhoto.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			UserPhotoController userPhotoController = loader.getController();
			userPhotoController.start(currentAlbum);
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add("/view/application.css");
			stage.setScene(scene);
			((Node)e.getSource()).getScene().getWindow().hide();
			stage.show();
		}
		catch (Exception z) {
			z.printStackTrace();
		}
		
	}
}
