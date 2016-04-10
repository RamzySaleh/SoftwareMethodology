package view;
import java.util.ArrayList;
import java.util.Optional;
import app.User;
import view.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
public class AdminController {
	
	MainController main = new MainController();
	
	@FXML Button logOut;
	@FXML TextField newUser;
	@FXML Button deleteButton;
	@FXML Button createButton;
	@FXML Label addSuccess;
	@FXML Label addFail;
	@FXML ListView<String> userListView;
	@FXML AnchorPane userCreateView;
	public static ArrayList<User> users;
	public static ArrayList<User> getUsers(){
		return users;
	}
	private ObservableList<String> obsList;
	
	/**
	 * TODO
	 * Must implement persistence.
	 */
	
	public void start() {
		
			 if(users == null) users = new ArrayList<User>();
			 addFail.setVisible(false);
			 obsList = FXCollections.observableArrayList();
			 for(int i = 0; i < users.size(); i++){
				 if(users.isEmpty()) break;
				 obsList.add(users.get(i).getName());
			 }
			 userListView.setItems(obsList);
			 userListView.getSelectionModel().select(0);

			 userListView
		 	.getSelectionModel()
		 	.selectedItemProperty();
			 
	}
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	
	public void logOutButtonClicked(ActionEvent e){
		/**
		 * TODO
		 * Make sure any changes are saved
		 */
		try{
			main.logOutButtonClicked(e);
		}
		catch(Exception r){
			r.printStackTrace();
		}
	}
	
	public void deleteButtonClicked(ActionEvent e){
		if(obsList.isEmpty()){
			return;
		}
		else{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete user");
			alert.setContentText("Are you sure you want to delete user "+userListView.getSelectionModel().getSelectedItem()+"?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.OK){
				int index = searchForUser(userListView.getSelectionModel().getSelectedItem());
				users.remove(index);
				start();
			}
		}
	}
	public void createButtonClicked(ActionEvent e){
		addFail.setVisible(false);
		addSuccess.setVisible(false);
		
		if(newUser.getText().trim().isEmpty()){
			addFail.setVisible(false);
			addFail.setVisible(false);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No input");
			alert.setContentText("Must input username");
			alert.show();
		}
		else if ((searchForUser(newUser.getText()) != -1) || newUser.getText().equalsIgnoreCase("admin")){
			addFail.setVisible(true);
		}
		else {
			User addUser = new User(newUser.getText());
			if(users == null) users = new ArrayList<User>();
			users.add(addUser);
			addSuccess.setVisible(true);
			start();
			newUser.clear();
		}
	}
	public int searchForUser(String username){
		if(users == null) return -1;
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getName().equals(username)){
				return i;
			}
		}
		return -1;
	}

}
