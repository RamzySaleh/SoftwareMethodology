package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import app.Album;
import app.Photo;
import app.User;
import view.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Alert.AlertType;

public class UserPhotoController {
	@FXML AnchorPane mainWindow;
	@FXML TilePane tilePane;
	@FXML ScrollPane scrollPane;
	@FXML BorderPane borderPane;
	@FXML ImageView currentImage = new ImageView();
	@FXML Label username;
	@FXML Button addPhoto;
	@FXML Button moveToAlbum;
	@FXML Button backToAlbumList;
	@FXML Button slideshow;
	@FXML Button logOut;
	@FXML Label albumName;
	@FXML Label numberOfPhotos;
	@FXML DatePicker oldestPhotoDate;
	@FXML DatePicker oldestPhotoRange;
	@FXML DatePicker newestPhotoRange;
	MainController main = new MainController();
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	public void start(Album album){
		
		 ColorAdjust normal = new ColorAdjust();
         normal.setBrightness(0);
         ColorAdjust dark = new ColorAdjust();
         dark.setBrightness(-0.3);
         
		 username.setText(LoginController.currentUser.getName());		 
		 albumName.setText(album.getName());
		 
		 int i = 0;
		 scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
		 scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
		 scrollPane.setFitToWidth(true);
		 scrollPane.setContent(tilePane);
		 tilePane.setPadding(new Insets(15,15,15,15));
		 tilePane.setHgap(15);
		 tilePane.setVgap(15);
		 
		 
		 ArrayList<Photo> photos = new ArrayList<Photo>();
		 photos = album.getPhotos();
		 
		 
		 ImageView image13 = new ImageView("/view/shirt.jpg");
		 ImageView image12 = new ImageView("/view/shirt.jpg");
		 ImageView image10 = new ImageView("/view/shirt.jpg");
	
		 
		 //if(photos.isEmpty()){
			// return;
		 // }
		 ArrayList<ImageView> images = new ArrayList<ImageView>();
		 
		 images.add(image10);
		 images.add(image12);
		 images.add(image13);
		 for(int x = 0; x < photos.size(); x++){
			 //Adding all ImageViews from the Photo object to ArrayList for display
			 images.add(photos.get(i).getImage());
		 }
		 for(i=0; i < images.size();i++){
			 ImageView currentImage = images.get(i);
			 int currentValue = i;
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
							userPhotoExpandController.startPhotoExpand(currentImage, album, currentValue);
							Scene scene = new Scene(rootLayout);
							stage.setScene(scene);
							stage.show();
							mainWindow.getScene().getWindow().hide();
							
						} catch (IOException m) {
							m.printStackTrace();
						}
						
					} else if (event.getClickCount() == 1){
						for (int j = 0; j < images.size(); j++) images.get(j).setEffect(dark);
						currentImage.setEffect(normal);
					}
					
				}
				 
				 
			 });
			 images.get(i).setOnDragDetected(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Delete photo");
					alert.setContentText("Are you sure you want to delete this photo?");
					
					Optional<ButtonType> result = alert.showAndWait();
					
					if(result.get() == ButtonType.OK){
						//what we'd have to do here is find the relevant album, reset it to the new album without the deleted photo, and restart
						Album albumForDeletion = searchForAlbum(album.getName());
						albumForDeletion.getPhotos().remove(currentValue);
						LoginController.currentUser.getAlbums().remove(getAlbumIndex(album.getName()));
						LoginController.currentUser.getAlbums().add(albumForDeletion);
						start(albumForDeletion);
					}
					
				}
				 
				 
			 });
			 
		 }
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
	
	public Album searchForAlbum(String name){
		User currentUser = LoginController.currentUser;
		for(int i = 0; i < currentUser.albums.size(); i++){
			if(currentUser.albums.get(i).getName().equals(name)){
				return currentUser.albums.get(i);
			}
		}
		
		return null;
	}
	
	public int getAlbumIndex(String name){
		User currentUser = LoginController.currentUser;
		for(int i = 0; i < currentUser.albums.size(); i++){
			if(currentUser.albums.get(i).getName().equals(name)){
				return i;
			}
		}
		
		return -1;
	}
	
	

}
