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
	@FXML Button albumCreate;
	@FXML Button albumCreateCancel;
	@FXML Button createButton;
	@FXML Button searchButton;
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
		 addSuccess.setVisible(false);
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
		
		
	}
	public void cancelButtonClicked(ActionEvent e){
		
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
	

}
