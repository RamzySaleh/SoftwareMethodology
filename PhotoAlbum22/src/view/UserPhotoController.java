package view;

import java.awt.Desktop;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.SplitPane;
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
	@FXML Label oldestPhotoDate;
	@FXML Label oldestPhotoRange;
	@FXML Label newestPhotoRange;
	MainController main = new MainController();
	
    private Desktop desktop = Desktop.getDesktop();
    Album currentAlbum;
    ImageView currentImageView;
    int currentImageViewIndex;
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	
	public void start(Album album){
		
		 currentAlbum = album;
		 ColorAdjust normal = new ColorAdjust();
         normal.setBrightness(0);
         ColorAdjust dark = new ColorAdjust();
         dark.setBrightness(-0.5);
         
		 username.setText(LoginController.currentUser.getName());		 
		 albumName.setText(album.getName());
		 
		 scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
		 scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
		 scrollPane.setFitToWidth(true);
		 scrollPane.setContent(tilePane);
		 tilePane.setPadding(new Insets(15,15,15,15));
		 tilePane.setHgap(15);
		 tilePane.setVgap(15);
		 
		 
		 ArrayList<Photo> photos = new ArrayList<Photo>();
		 photos = album.getPhotos();
		
		 numberOfPhotos.setText(String.valueOf(photos.size()));
		 
		 if(photos.isEmpty()){
			return;
		 }
		 
		 findOldestDate();
		 findNewestDate();
		 
		 ArrayList<ImageView> images = new ArrayList<ImageView>();
		 images.clear();
		 for(int x = 0; x < photos.size(); x++){
			 //Adding all ImageViews from the Photo object to ArrayList for display
			 images.add(new ImageView(photos.get(x).getImage()));
		 }
		
		 for(int i=0; i < images.size();i++){
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
							scene.getStylesheets().add("/view/application.css");
							stage.setScene(scene);
							stage.show();
							mainWindow.getScene().getWindow().hide();
							
						} catch (IOException m) {
							m.printStackTrace();
						}
						
					} else if (event.getClickCount() == 1){
						for (int j = 0; j < images.size(); j++) images.get(j).setEffect(dark);
						currentImageView = currentImage;
						currentImageViewIndex = currentValue;
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
	public void slideShowButtonClicked(ActionEvent e){
		try {
			if(numberOfPhotos.getText().equals("0")) {
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Sorry");
	        	alert.setHeaderText("Could not open slideshow.");
	        	alert.setContentText("You have no photos in this album. Try adding photos.");

	        	alert.showAndWait();
	        	return;
			}
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("userPhotoSlideshow.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			UserPhotoSlideshowController userPhotoSlideshowController = loader.getController();
			userPhotoSlideshowController.start(currentAlbum);
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add("/view/application.css");
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception z) {
			z.printStackTrace();
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
	
	public void moveButtonClicked(ActionEvent e){
		
		if (currentImageView == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Select a photo.");
			alert.setContentText("Please select a photo you want to move!");
			alert.show();
			return;
		}
		Image image = currentImageView.getImage();
		
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("movePhoto.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			UserMovePhotoController userMovePhotoController = loader.getController();
			userMovePhotoController.start(image, currentImageViewIndex, currentAlbum);
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add("/view/application.css");
			stage.setScene(scene);
			((Node)e.getSource()).getScene().getWindow().hide();
			stage.show();
		}
		catch (Exception z) {
			z.printStackTrace();
		}
		
		
	}
	
	public void backToAlbumListClicked(ActionEvent e){
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
            newPhoto.setTimeOfCapture(file);
            currentAlbum.addOnePhoto(newPhoto);
            start(currentAlbum);
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
	
	public void findOldestDate(){
		
		Calendar oldestDate;
		ArrayList<Photo> photos = currentAlbum.getPhotos();
		if (photos.size() == 0) return;

		Calendar cal = photos.get(0).getTimeOfCapture();

		if (photos.size() == 1) {	
			if (cal == null) {
				System.out.println("cal null!");
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			
			oldestPhotoDate.setText(sdf.format(cal.getTime()));
			oldestPhotoRange.setText(sdf.format(cal.getTime()));
			newestPhotoRange.setText(sdf.format(cal.getTime()));
			return;
			
		}
		
		
		oldestDate = cal;

		for (int i = 1; i < photos.size(); i++){
			cal = photos.get(i).getTimeOfCapture();
			if (cal == null) {
				System.out.println("cal null!");
				return;
			}
			if (oldestDate == null){
				System.out.println("oldest date null");
				return;
			}
			if(cal.before(oldestDate)){
				oldestDate = cal;
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		oldestPhotoDate.setText(sdf.format(oldestDate.getTime()));
		oldestPhotoRange.setText(sdf.format(oldestDate.getTime()));
		
		
	}
	public void findNewestDate(){
		
		Calendar newestDate;
		ArrayList<Photo> photos = currentAlbum.getPhotos();
		if (photos.size() == 0) return;
		
		Calendar cal = photos.get(0).getTimeOfCapture();
		
		if (photos.size() == 1) {
			
			if (cal == null) {
				System.out.println("cal null!");
				return;
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			oldestPhotoDate.setText(sdf.format(cal.getTime()));
			oldestPhotoRange.setText(sdf.format(cal.getTime()));
			newestPhotoRange.setText(sdf.format(cal.getTime()));
			
			return;
			
		}

		newestDate = cal;
		
		for (int i = 1; i < photos.size(); i++){
			cal = photos.get(i).getTimeOfCapture();
			if (cal == null) {
				System.out.println("cal null!");
				return;
			}
			if (newestDate == null){
				System.out.println("oldest date null");
				return;
			}
			if(newestDate.before(cal)){
				newestDate = cal;
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		newestPhotoRange.setText(sdf.format(newestDate.getTime()));
		
	}
	
	

}
