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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	@FXML ScrollPane scrollPane;
	MainController main = new MainController();
	private ObservableList<String> obsList;
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	public void start(){
		
		 scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
		 scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
		 scrollPane.setFitToWidth(true);
		 scrollPane.setContent(tilePane);
		 tilePane.setPadding(new Insets(15,15,15,15));
		 tilePane.setHgap(15);
		 tilePane.setVgap(15);
		 ImageView image = new ImageView("/view/shirt.jpg");
		 ImageView image2 = new ImageView("/view/shirt.jpg");
		 ImageView image3 = new ImageView("/view/shirt.jpg");
		 ImageView image4 = new ImageView("/view/shirt.jpg");
		 ImageView image5 = new ImageView("/view/shirt.jpg");
		 ImageView image6 = new ImageView("/view/shirt.jpg");
		 ImageView image7 = new ImageView("/view/shirt.jpg");
		 ImageView image8 = new ImageView("/view/shirt.jpg");
		 ImageView image9 = new ImageView("/view/shirt.jpg");
		 ImageView image10 = new ImageView("/view/shirt.jpg");
		 ArrayList<ImageView> images = new ArrayList<ImageView>();
		 images.add(image);
		 images.add(image2);
		 images.add(image3);
		 images.add(image4);
		 images.add(image5);
		 images.add(image6);
		 images.add(image7);
		 images.add(image8);
		 images.add(image9);
		 images.add(image10);
		 for(int i = 0; i < images.size(); i++){
			 images.get(i).setFitWidth(100);
			 images.get(i).setFitHeight(100);
		 }
		 
		 tilePane.getChildren().addAll(images);
	}
	
	public void logOutButtonClicked(){
		
		
	}
	
	

}
