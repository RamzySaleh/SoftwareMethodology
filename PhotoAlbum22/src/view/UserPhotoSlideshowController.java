package view;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import app.Album;
import app.Photo;
import app.User;
import view.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class UserPhotoSlideshowController {
	
	@FXML Button forwardSlideshow;
	@FXML Button backSlideshow;
	@FXML BorderPane borderPane;
	@FXML Label username;
	@FXML Label albumName;
	User currentUser = LoginController.currentUser;
	int currentIndex = 0;
	ArrayList<Photo> photos;
	MainController main = new MainController();
	
	public void start(Album album){
		username.setText(currentUser.getName());
		albumName.setText(album.getName());
		photos = album.getPhotos();
		Image firstImage = photos.get(0).getImage();
		currentIndex = 0; //redundant, only placed here for clarity
		ImageView image = new ImageView(firstImage);
		image.setFitWidth(225);
		image.setFitHeight(225);
		image.setSmooth(true);
		image.setCache(true);
		image.setDisable(true);
		borderPane.setCenter(image);
	}
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	public void forwardButtonClicked(ActionEvent e){
		currentIndex++;
		if(currentIndex >= photos.size()){
			currentIndex = 0;
		}
		Image nextImage = photos.get(currentIndex).getImage();
		ImageView image = new ImageView(nextImage);
		image.setFitWidth(225);
		image.setFitHeight(225);
		image.setSmooth(true);
		image.setCache(true);
		image.setDisable(true);
		borderPane.setCenter(image);
		
	}
	public void backwardButtonClicked(ActionEvent e){
		currentIndex--;
		if(currentIndex < 0){
			currentIndex = photos.size()-1;
			if (currentIndex < 0) currentIndex = 0;
		}
		Image nextImage = photos.get(currentIndex).getImage();
		ImageView image = new ImageView(nextImage);
		image.setFitWidth(225);
		image.setFitHeight(225);
		image.setSmooth(true);
		image.setCache(true);
		image.setDisable(true);
		borderPane.setCenter(image);
		
	}

}
