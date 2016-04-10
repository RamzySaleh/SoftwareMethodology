package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import com.sun.javafx.iio.ImageStorage;

import app.Album;
import app.User;
import view.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert.AlertType;

public class UserPhotoExpandController {
	
	@FXML BorderPane borderPane;
	@FXML Button back;
	@FXML Label timeOfCapture;
	@FXML Label tags;
	Album currentAlbum;
	
	MainController main = new MainController();
	private ObservableList<String> obsList;
	
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
	public void startPhotoExpand(ImageView image, Album album){
		currentAlbum = album;
		image.setFitWidth(225);
		image.setFitHeight(225);
		image.setSmooth(true);
		image.setCache(true);
		image.setDisable(true);
		borderPane.setCenter(image);
		
	}

}
