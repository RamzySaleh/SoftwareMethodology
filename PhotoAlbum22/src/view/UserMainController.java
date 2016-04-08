package view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import app.Album;
import app.User;
import view.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert.AlertType;

public class UserMainController {

	@FXML Button logOut;
	@FXML Button deleteButton;
	@FXML Button createButton;
	@FXML Button searchButton;
	@FXML Button albumCreate;
	@FXML Button back;
	@FXML Button searchOK;
	@FXML Button searchBack;
	@FXML Label username;
	@FXML Label addSuccess;
	@FXML Label addFail;
	@FXML Label mustInputText;
	@FXML Label albums;
	@FXML TextField newAlbumName;
	@FXML TextField monthLow;
	@FXML TextField dayLow;
	@FXML TextField yearLow;
	@FXML TextField monthHigh;
	@FXML TextField dayHigh;
	@FXML TextField yearHigh;
	@FXML ListView<String> albumListView;
	@FXML AnchorPane createAlbumAnchor;
	@FXML AnchorPane searchAnchor;
	
	MainController main = new MainController();
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	private ObservableList<String> obsList;
	
	public void OKButtonClicked(ActionEvent e){
		addFail.setVisible(false);
		addSuccess.setVisible(false);
		mustInputText.setVisible(false);
		if(newAlbumName.getText().trim().isEmpty()){
			mustInputText.setVisible(true);
			return;
		}
		if((searchForAlbum(newAlbumName.getText()) != -1)){
		addFail.setVisible(true);
		}
		else{
		Album addAlbum = new Album(newAlbumName.getText());
		ArrayList<Album> albumList = (LoginController.currentUser.getAlbums());
		albumList.add(addAlbum);
		LoginController.currentUser.albums = albumList;
		addSuccess.setVisible(true);
		start();
		newAlbumName.clear();
		}
		
	}
	
	
	public void start() {
		 // create an ObservableList
		 // from an ArrayList
			 deleteButton.setDisable(true);
			 username.setVisible(true);
			 User currentUser = LoginController.currentUser;
			 username.setText(currentUser.getName());
			 username.setTextAlignment(TextAlignment.CENTER);
			 addSuccess.setVisible(false);
			 addFail.setVisible(false);
			 obsList = FXCollections.observableArrayList();
			 for(int i = 0; i < (currentUser.getAlbums()).size(); i++){
				 if((currentUser.getAlbums()).isEmpty()) break;
				 obsList.add((currentUser.getAlbums()).get(i).getName());
			 }
			 if(!obsList.isEmpty()){
				 deleteButton.setDisable(false);
			 }
			 albumListView.setItems(obsList);
			 albumListView.getSelectionModel().select(0);

			 albumListView
		 	.getSelectionModel()
		 	.selectedItemProperty();
			
			 albumListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
				 @Override
				 public void handle(MouseEvent click){
					 if(click.getClickCount() == 2){
						 try {
								Stage stage = new Stage();
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(getClass().getResource("userPhoto.fxml"));
								AnchorPane rootLayout = (AnchorPane) loader.load();
								UserPhotoController userPhotoController = loader.getController();
								userPhotoController.start();
								Scene scene = new Scene(rootLayout);
								scene.getStylesheets().add("/view/application.css");
								stage.setScene(scene);
								((Node)click.getSource()).getScene().getWindow().hide();
								stage.show();	
								
							} catch (IOException m) {
								m.printStackTrace();
							}
						 
					 }
				 }
				 
			 });
			 
		 }
	
	
	public void logOutButtonClicked(ActionEvent e){
		try{
			main.logOutButtonClicked(e);
			}
			catch(Exception r){
				r.printStackTrace();
			}
	}
	public void deleteButtonClicked(ActionEvent e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete user");
		alert.setContentText("Are you sure you want to delete album "+albumListView.getSelectionModel().getSelectedItem()+"?");
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK){
			int index = searchForAlbum(albumListView.getSelectionModel().getSelectedItem());
			ArrayList<Album> albumList = (LoginController.currentUser.getAlbums());
			albumList.remove(index);
			LoginController.currentUser.albums = albumList;
			start();
		}
		
	}
	public void backButtonClicked(ActionEvent e){
		
		if(!newAlbumName.getText().trim().isEmpty()){
			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Unsaved changes");
			warning.setContentText("Are you sure you want to go back? You haven't saved your changes.");
			Optional<ButtonType> result = warning.showAndWait();
			
			if(result.get() == ButtonType.OK){
				createAlbumAnchor.setVisible(false);
				albums.setVisible(true);
				
			}
		}
		else {
			createAlbumAnchor.setVisible(false);
			albums.setVisible(true);
			createButton.setDisable(false);
		}
	}
	public void createButtonClicked(ActionEvent e){
		createButton.setDisable(true);
		createAlbumAnchor.setVisible(true);
		albums.setVisible(false);
	}
	public void searchButtonClicked(ActionEvent e){
		searchAnchor.setVisible(true);
		createAlbumAnchor.setVisible(false);
		albums.setVisible(false);
		createButton.setDisable(true);
		
	}
	
	public int searchForAlbum(String name){
		ArrayList<Album> albums = LoginController.currentUser.getAlbums();
		
		for(int i = 0; i < albums.size(); i++){
			if(albums.get(i).getName().equals(name)){
				return i;
			}
		}
		return -1;
	}
	
	public void searchOKButtonClicked(ActionEvent e){
		
	}
	public void searchBackButtonClicked(ActionEvent e){
		
	}
	
}
