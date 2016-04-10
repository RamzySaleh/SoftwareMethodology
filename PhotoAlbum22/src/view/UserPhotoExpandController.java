package view;

import java.util.Calendar;
import app.Album;
import view.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class UserPhotoExpandController {
	
	@FXML BorderPane borderPane;
	@FXML Button back;
	@FXML Label timeOfCapture;
	@FXML Label username;
	@FXML ListView<String> tagListView;
	@FXML Button logOut;
	Album currentAlbum;
	
	MainController main = new MainController();
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	public void backButtonClicked(){
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("userPhoto.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			UserPhotoController userPhotoController = loader.getController();
			userPhotoController.start(currentAlbum);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.show();
			borderPane.getScene().getWindow().hide();
		}
		catch(Exception r){
			r.printStackTrace();
		}
		
	}
	public void logOutButtonClicked(ActionEvent e){
		try{
			main.logOutButtonClicked(e);
		}
		catch(Exception r){
			r.printStackTrace();
		}
	}
	public void startPhotoExpand(ImageView image, Album album, int index){
		
		ObservableList<String> obsList = FXCollections.observableArrayList();
		
		username.setText(LoginController.currentUser.getName());
		currentAlbum = album;
		image.setFitWidth(225);
		image.setFitHeight(225);
		image.setSmooth(true);
		image.setCache(true);
		image.setDisable(true);
		borderPane.setCenter(image);
		
		String[] tags = album.getPhotos().get(index).tagsAsString();
		
		for (int i = 0; i < tags.length; i++) obsList.add(tags[i]);
		
		tagListView.setItems(obsList);
		
		Calendar cal = album.getPhotos().get(index).getTimeOfCapture();
		
		timeOfCapture.setText(String.valueOf(cal.getTimeInMillis()));
		
		
	}

}
