package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import app.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
	@FXML AnchorPane window;
	@FXML TextField username;
	@FXML Button enter;
	ArrayList<User> users;
	
	public void enterButtonClicked(ActionEvent e){

		String user = username.getText();
		if(user.equals("") || user == null || username.isEditable()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("Must input a username");
			alert.show();
			return;
		}
		
		if(searchForUser(users, user) == -1){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("User does not exist");
			alert.show();
			return;
		}
		System.out.println("User found, click successful");
		
	}
	
	public int searchForUser(ArrayList<User> users, String username){
		if(users == null) return -1;
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getName().equals(username)){
				return 1;
			}
		}
		return -1;
	}

}
