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

public class UserCreateController {
	@FXML Button logOut;
	@FXML Label username;
	@FXML ListView<String> albumListView;
	@FXML Button deleteButton;
	@FXML Label addSuccess;
	@FXML Label addFail;
	@FXML Label mustInputText;
	@FXML Button albumCreate;
	@FXML Button back;
	@FXML Button createButton;
	@FXML Button searchButton;
	@FXML TextField newAlbumName;
	MainController main = new MainController();
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	private ObservableList<String> obsList;
	
	public void start(){
		
		 username.setVisible(true);
		 createButton.setDisable(true);
		 User currentUser = LoginController.currentUser;
		 username.setText(currentUser.getName());
		 username.setTextAlignment(TextAlignment.CENTER);
		 addFail.setVisible(false);
		 obsList = FXCollections.observableArrayList();
		 for(int i = 0; i < (currentUser.getAlbums()).size(); i++){
			 if((currentUser.getAlbums()).isEmpty()) break;
			 obsList.add((currentUser.getAlbums()).get(i).getName());
		 }
		
		 albumListView.setItems(obsList);
		 albumListView.getSelectionModel().select(0);

		 albumListView
	 	.getSelectionModel()
	 	.selectedItemProperty();
	}
	
	public void OKButtonClicked(ActionEvent e){
		addFail.setVisible(false);
		addSuccess.setVisible(false);
		mustInputText.setVisible(false);
		if(!newAlbumName.getText().trim().isEmpty()){
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
	public void backButtonClicked(ActionEvent e){
		
		if(!newAlbumName.getText().trim().isEmpty()){
			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Unsaved changes");
			warning.setContentText("Are you sure you want to go back? You haven't saved your changes.");
			Optional<ButtonType> result = warning.showAndWait();
			
			if(result.get() == ButtonType.OK){
				try {
					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("userMain.fxml"));
					SplitPane rootLayout = (SplitPane) loader.load();
					UserMainController userMainController = loader.getController();
					userMainController.start();
					Scene scene = new Scene(rootLayout);
					scene.getStylesheets().add("/view/application.css");
					stage.setScene(scene);
					((Node)e.getSource()).getScene().getWindow().hide();
					stage.show();	
					
				} catch (IOException m) {
					m.printStackTrace();
				}
				
			}
			else return;
		}
		
		
	}
	
	public void logOutButtonClicked(ActionEvent e){
		    try {
			main.logOutButtonClicked(e);
			}
			catch(Exception r){
				r.printStackTrace();
			}
	}
	
	public void searchButtonClicked(ActionEvent e){
		
		
	}
	public void deleteButtonClicked(ActionEvent e){
		
	}
	public int searchForAlbum(String name){
		ArrayList<Album> albums = LoginController.currentUser.getAlbums();
		
		for(int i = 0; i < albums.size(); i++){
			if(albums.get(i).getName().equals(name)){
				return 1;
			}
		}
		return -1;
	}

}
