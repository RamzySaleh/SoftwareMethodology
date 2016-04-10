package view;

import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

public class MainController {
	@FXML AdminController adminController;
	@FXML LoginController loginController;
	@FXML UserMainController userMainController;

	@FXML public void initialize(){
		adminController.init(this);
		loginController.init(this);
		userMainController.init(this);
	}

	@FXML public void logOutButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Log out");
		alert.setContentText("Are you sure you want to log out?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
			((Node)e.getSource()).getScene().getWindow().hide();
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("login.fxml"));
				AnchorPane rootLayout = (AnchorPane) loader.load();
				Scene scene = new Scene(rootLayout);
				scene.getStylesheets().add("/view/application.css");
				stage.setScene(scene);
				stage.show();
			}
			catch(Exception q){
				q.printStackTrace();
			}
		}
		else{
			return;
		}
	}
	
}
