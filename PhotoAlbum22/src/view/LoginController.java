package view;

import java.io.IOException;
import java.util.ArrayList;
import app.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import view.MainController;

public class LoginController{
	
	@FXML MainController main = new MainController();
	@FXML AnchorPane window;
	@FXML TextField username;
	@FXML Button enter;
	ArrayList<User> users = AdminController.getUsers();
	static User currentUser;
	
	public void enterButtonClicked(ActionEvent e){

		String user = username.getText();
		if(user.equals("") || user == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("Must input a username");
			alert.show();
			return;
		}
		
		if(user.equalsIgnoreCase("admin")){
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("admin.fxml"));
				SplitPane rootLayout = (SplitPane) loader.load();
				AdminController adminController = loader.getController();
				adminController.start();
				Scene scene = new Scene(rootLayout);
				scene.getStylesheets().add("/view/application.css");
				stage.setScene(scene);
				((Node)e.getSource()).getScene().getWindow().hide();
				stage.show();	
				
			} catch (IOException m) {
				m.printStackTrace();
			}
		}
		else if(searchForUser(users, user) == -1){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("User does not exist");
			alert.show();
			return;
		}
		else if(searchForUser(users, user) != -1){
			currentUser = users.get(searchForUser(users, user));
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
		
	}
	
	public int searchForUser(ArrayList<User> users, String username){
		if(users == null) return -1;
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getName().equals(username)){
				return i;
			}
		}
		return -1;
	}
	
	public void init(MainController mainControl){
		main = mainControl;
	}

}
