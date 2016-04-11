package view;

import java.awt.Desktop;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
	
    private Desktop desktop = Desktop.getDesktop();
    Album currentAlbum;
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	public void start(Album album){
		
		 currentAlbum = album;
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
		
		 if(photos.isEmpty()){
			return;
		 }
		 
		 ArrayList<ImageView> images = new ArrayList<ImageView>();
		 
		 for(int x = 0; x < photos.size(); x++){
			 //Adding all ImageViews from the Photo object to ArrayList for display
			 images.add(new ImageView(photos.get(i).getImage()));
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
						tilePane.getChildren().clear();
						start(albumForDeletion);
					}
					
				}
				 
				 
			 });
			 
		 }
		 try{
			 tilePane.getChildren().clear();
			 tilePane.getChildren().addAll(images);
		 } catch (IllegalArgumentException e){
			 
		 }
	}
	
	public void logOutButtonClicked(ActionEvent e){
		
		try{
			main.logOutButtonClicked(e);
		}
		catch(Exception r){
			r.printStackTrace();
		}
		
	}
	
	public void addButtonClicked(ActionEvent e){

		Stage stage = new Stage();
			stage.setTitle("File Chooser Sample");
		 
	        final FileChooser fileChooser = new FileChooser();
	        final Button openButton = new Button("Open a Picture...");
	      
	        openButton.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                    configureFileChooser(fileChooser);
	                    File file = fileChooser.showOpenDialog(stage);
	                    if (file != null) {
	                        addFile(file);
	                    }
	                }
	            });
	 
	 
	 
	        final GridPane inputGridPane = new GridPane();
	 
	        GridPane.setConstraints(openButton, 0, 1);
	        inputGridPane.setHgap(6);
	        inputGridPane.setVgap(6);
	        inputGridPane.getChildren().addAll(openButton);
	        
	        final Pane rootGroup = new VBox(12);
	        rootGroup.getChildren().addAll(inputGridPane);
	        rootGroup.setPadding(new Insets(12, 12, 12, 12));
	 
	        stage.setScene(new Scene(rootGroup));
	        stage.show();
	        start(currentAlbum);
	}
	
	private static void configureFileChooser(
	        final FileChooser fileChooser) {      
	            fileChooser.setTitle("View Pictures");
	            fileChooser.setInitialDirectory(
	                new File(System.getProperty("user.home"))
	            );                 
	            fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("All Images", "*.*"),
	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	                new FileChooser.ExtensionFilter("PNG", "*.png")
	            );
	}
	
	private void addFile(File file) {
        try {
            Photo newPhoto = new Photo();
            Image im = null;
            WritableImage wr = null;
            BufferedImage bi = ImageIO.read(file);
            
            if (bi != null) {
            	wr = new WritableImage(bi.getWidth(), bi.getHeight());
            	PixelWriter pw = wr.getPixelWriter();
            	for (int i = 0; i < bi.getWidth(); i++){
            		for (int j = 0; j < bi.getHeight(); j++) {
            			pw.setArgb(i, j, bi.getRGB(i,j));
            		}
            	}
            }
            im = wr;
            newPhoto.setImage(im);
            currentAlbum.addOnePhoto(newPhoto);
            
        } catch (IOException ex) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not open specified file.");
        	alert.setContentText("Please try again or try a different file.");

        	alert.showAndWait();
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
