package view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
public class AdminController {
	
	MainController main = new MainController();
	
	@FXML Button logOut;
	
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

}
