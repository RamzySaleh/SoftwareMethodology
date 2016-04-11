package view;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import app.Album;
import app.Photo;
import app.User;
import view.MainController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
public class UserSearchResultsController {
	
	@FXML TilePane tilePane;
	@FXML ScrollPane scrollPane;
	@FXML TextField newAlbumName;
	@FXML Label addFail;
	@FXML Label addSuccess;
	ArrayList<Photo> newPhotos;
	User currentUser = LoginController.currentUser;
	
	public void start(ArrayList<Photo> photos){
		 newPhotos = photos;
		 scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
		 scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
		 scrollPane.setFitToWidth(true);
		 scrollPane.setContent(tilePane);
		 tilePane.setPadding(new Insets(15,15,15,15));
		 tilePane.setHgap(15);
		 tilePane.setVgap(15);
		 
		 ArrayList<ImageView> images = new ArrayList<ImageView>();
		 for(int i = 0; i < photos.size(); i++){
			 images.add(new ImageView(photos.get(i).getImage()));
		 }
		 for(int i=0; i < images.size();i++){
			 images.get(i).setFitWidth(75);
			 images.get(i).setFitHeight(75);
		 }
		 try{
			 tilePane.getChildren().clear();
			 tilePane.getChildren().addAll(images);
		 } catch (IllegalArgumentException e){
			 e.printStackTrace();
		 }
		
	}
	public void OKButtonClicked(ActionEvent e){
		addFail.setVisible(false);
		addSuccess.setVisible(false);
		
		if(newAlbumName.getText().trim().isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Must input text");
			alert.show();
		}
		ArrayList<Album> albums = currentUser.getAlbums();
		for(int i = 0; i < albums.size(); i++){
			if(albums.get(i).getName().equalsIgnoreCase(newAlbumName.getText())){
				addFail.setVisible(true);
			}
		}
		if(!addFail.isVisible()){
			Album newAlbum = new Album(newAlbumName.getText());
			newAlbum.setPhotos(newPhotos);
			LoginController.currentUser.albums.add(newAlbum);
			addSuccess.setVisible(true);
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
				stage.show();
			}
			catch(Exception z){
				z.printStackTrace();
			}
		}
	}

}
