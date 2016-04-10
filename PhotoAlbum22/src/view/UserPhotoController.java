package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import com.sun.javafx.iio.ImageStorage;

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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert.AlertType;

public class UserPhotoController {
	@FXML AnchorPane mainWindow;
	@FXML TilePane tilePane;
	@FXML ScrollPane scrollPane;
	@FXML BorderPane borderPane;
	@FXML Label timeOfCapture;
	@FXML Label tags;
	@FXML ImageView currentImage = new ImageView();
	MainController main = new MainController();
	private ObservableList<String> obsList;
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	public void start(){
		 int i = 0;
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
		 for(i=0; i < images.size();i++){
			 ImageView currentImage = images.get(i);
			 images.get(i).setFitWidth(75);
			 images.get(i).setFitHeight(75);
			 images.get(i).setOnMouseClicked(new EventHandler<MouseEvent>(){
		     
				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					
					if(event.getClickCount() == 2){
					//IMPLEMENTATION OF LARGER DISPLAY
						try {
							Stage stage = new Stage();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("userPhotoDisplay.fxml"));
							AnchorPane rootLayout = (AnchorPane) loader.load();
							UserPhotoExpandController userPhotoExpandController = loader.getController();
							userPhotoExpandController.startPhotoExpand(currentImage);
							Scene scene = new Scene(rootLayout);
							stage.setScene(scene);
							stage.show();
							mainWindow.getScene().getWindow().hide();
							
						} catch (IOException m) {
							m.printStackTrace();
						}
						
					}
					
				}
				 
				 
			 });
			 
		 }
		 System.out.println(images.size());
		 tilePane.getChildren().addAll(images);
		 
	}
	
	public void logOutButtonClicked(ActionEvent e){
		
		try{
			main.logOutButtonClicked(e);
		}
		catch(Exception r){
			r.printStackTrace();
		}
		
	}
	
	

}
