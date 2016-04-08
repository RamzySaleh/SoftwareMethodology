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
import javafx.geometry.Insets;
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
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert.AlertType;

public class UserPhotoController {
	@FXML TilePane tilePane;
	MainController main = new MainController();
	private ObservableList<String> obsList;
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	public void start(){
		 tilePane.setPadding(new Insets(15,15,15,15));
		 tilePane.setHgap(15);
		 Label test = new Label("labelin");
		 Label test2 = new Label("labelin");
		 Label test3 = new Label("labelin");
		 Label test4 = new Label("labelin");
		 Label test5 = new Label("labelin");
		 Label test6 = new Label("labelin");
		 tilePane.getChildren().addAll(test,test2,test3,test4,test5,test6);
	}
	
	public void logOutButtonClicked(){
		
		
	}
	
	

}
