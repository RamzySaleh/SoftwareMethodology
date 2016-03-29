package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainController {
	@FXML AdminController adminController;
	@FXML LoginController loginController;

	@FXML public void initialize(){
		adminController.init(this);
		loginController.init(this);
	}

	@FXML public void logOutButtonClicked() {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Log out");
		alert.setContentText("Are you sure you want to log out?");
		alert.show();
		
	}
	
}
