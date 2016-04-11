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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class UserMainController {

	@FXML Button logOut;
	@FXML Button deleteButton;
	@FXML Button createButton;
	@FXML Button editButton;
	@FXML Button searchButton;
	@FXML Button albumCreate;
	@FXML Button back;
	@FXML Button searchOK;
	@FXML Button searchBack;
	@FXML Button addTagButton;
	@FXML Button deleteTagButton;
	@FXML Label username;
	@FXML Label addSuccess;
	@FXML Label addFail;
	@FXML Label mustInputText;
	@FXML Label albums;
	@FXML TextField newAlbumName;
	@FXML DatePicker lowEndDate;
	@FXML DatePicker highEndDate;
	@FXML ListView<String> albumListView;
	@FXML ListView<String> tagsForSearch;
	@FXML AnchorPane createAlbumAnchor;
	@FXML AnchorPane searchAnchor;
	
	ArrayList<String[]> tagsForSearchArr = new ArrayList<String[]>();
	MainController main = new MainController();
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	private ObservableList<String> obsList;
	private ObservableList<String> tagsObsList;
	public void OKButtonClicked(ActionEvent e){
		addFail.setVisible(false);
		addSuccess.setVisible(false);
		mustInputText.setVisible(false);
		if(newAlbumName.getText().trim().isEmpty()){
			mustInputText.setVisible(true);
			return;
		}
		if((searchForAlbum(newAlbumName.getText()) != -1)){
			addFail.setVisible(true);
		}
		else{
			Album addAlbum = new Album(newAlbumName.getText());
			ArrayList<Photo> photos = new ArrayList<Photo>();
			addAlbum.setPhotos(photos);
			ArrayList<Album> albumList = (LoginController.currentUser.getAlbums());
			albumList.add(addAlbum);
			LoginController.currentUser.albums = albumList;
			addSuccess.setVisible(true);
			start();
			newAlbumName.clear();
		}
		
	}
	
	
	public void start() {
		 // create an ObservableList
		 // from an ArrayList
			 
			 searchAnchor.setVisible(false);
			 ArrayList<Album> albumList = LoginController.currentUser.getAlbums();
			 deleteButton.setDisable(true);
			 username.setVisible(true);
			 User currentUser = LoginController.currentUser;
			 username.setText(currentUser.getName());
			 username.setTextAlignment(TextAlignment.CENTER);
			 addSuccess.setVisible(false);
			 addFail.setVisible(false);
			 obsList = FXCollections.observableArrayList();
			 for(int i = 0; i < (currentUser.getAlbums()).size(); i++){
				 if((currentUser.getAlbums()).isEmpty()) break;
				 obsList.add((currentUser.getAlbums()).get(i).getName());
			 }
			 if(!obsList.isEmpty()){
				 deleteButton.setDisable(false);
			 }
			 albumListView.setItems(obsList);
			 albumListView.getSelectionModel().select(0);

			 albumListView
		 	.getSelectionModel()
		 	.selectedItemProperty();
			
			 albumListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
				 @Override
				 public void handle(MouseEvent click){
					 if(click.getClickCount() == 2){
						 try {
								Stage stage = new Stage();
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(getClass().getResource("userPhoto.fxml"));
								AnchorPane rootLayout = (AnchorPane) loader.load();
								UserPhotoController userPhotoController = loader.getController();
								userPhotoController.start(albumList.get(searchForAlbum(albumListView.getSelectionModel().getSelectedItem())));
								Scene scene = new Scene(rootLayout);
								scene.getStylesheets().add("/view/application.css");
								stage.setScene(scene);
								((Node)click.getSource()).getScene().getWindow().hide();
								stage.show();	
								
							} catch (IOException m) {
								m.printStackTrace();
							}
						 
					 }
				 }
				 
			 });
			 
		 }
	
	
	public void logOutButtonClicked(ActionEvent e){
		try{
			main.logOutButtonClicked(e);
		}
		catch(Exception r){
			r.printStackTrace();
		}
	}
	public void deleteButtonClicked(ActionEvent e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete user");
		alert.setContentText("Are you sure you want to delete album "+albumListView.getSelectionModel().getSelectedItem()+"?");
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK){
			int index = searchForAlbum(albumListView.getSelectionModel().getSelectedItem());
			ArrayList<Album> albumList = (LoginController.currentUser.getAlbums());
			albumList.remove(index);
			LoginController.currentUser.albums = albumList;
			start();
		}
		
	}
	public void backButtonClicked(ActionEvent e){
		
		if(!newAlbumName.getText().trim().isEmpty()){
			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Unsaved changes");
			warning.setContentText("Are you sure you want to go back? You haven't saved your changes.");
			Optional<ButtonType> result = warning.showAndWait();
			
			if(result.get() == ButtonType.OK){
				createAlbumAnchor.setVisible(false);
				albums.setVisible(true);
				createButton.setDisable(false);
			}
		}
		else {
			createAlbumAnchor.setVisible(false);
			albums.setVisible(true);
			createButton.setDisable(false);
		}
	}
	public void createButtonClicked(ActionEvent e){
		createButton.setDisable(true);
		createAlbumAnchor.setVisible(true);
		albums.setVisible(false);
	}
	public void searchButtonClicked(ActionEvent e){
		searchAnchor.setVisible(true);
		createAlbumAnchor.setVisible(false);
		albums.setVisible(false);
		createButton.setDisable(true);
		
	}
	
	public int searchForAlbum(String name){
		ArrayList<Album> albums = LoginController.currentUser.getAlbums();
		
		for(int i = 0; i < albums.size(); i++){
			if(albums.get(i).getName().equals(name)){
				return i;
			}
		}
		return -1;
	}
	
	public void searchOKButtonClicked(ActionEvent e){
		boolean searchComplete = false;
		User currentUser = LoginController.currentUser;
		if((lowEndDate.getValue() == null) && (highEndDate.getValue() == null) && ((tagsForSearchArr == null) || tagsForSearchArr.isEmpty())){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Oops! At least one field is required to search.");
			alert.show();
		}
		
		ArrayList<Photo> photos = new ArrayList<Photo>();
		ArrayList<Album> albums = new ArrayList<Album>();
		albums = currentUser.getAlbums();
		
		 
		if(lowEndDate.getValue() != null && highEndDate.getValue() == null){
			LocalDate lowDate = lowEndDate.getValue();
			Date date = Date.from(lowDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Calendar lowerDate = Calendar.getInstance();
			lowerDate.setTime(date);
			lowerDate.set(Calendar.MILLISECOND, 0);
			
			for(int i = 0; i < albums.size(); i++){
				//For each album belonging to user
				for(int x = 0; x < albums.get(i).getPhotos().size(); x++){
					//For each photo in the album
					if(albums.get(i).getPhotos().get(x).getTimeOfCapture().after(lowerDate)){
						photos.add(albums.get(i).getPhotos().get(x));
					}
				}
			}	
			
		}
		else if(lowEndDate.getValue() == null && highEndDate.getValue() != null){
			LocalDate highDate = highEndDate.getValue();
			Date date = Date.from(highDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Calendar higherDate = Calendar.getInstance();
			higherDate.setTime(date);
			higherDate.set(Calendar.MILLISECOND, 0);
			for(int i = 0; i < albums.size(); i++){
				//For each album belonging to user
				for(int x = 0; x < albums.get(i).getPhotos().size(); x++){
					//For each photo in the album
					if(albums.get(i).getPhotos().get(x).getTimeOfCapture().before(higherDate)){
						photos.add(albums.get(i).getPhotos().get(x));
					}
				}
			}
			
		}
		else if(lowEndDate.getValue() != null && highEndDate.getValue() != null){
			LocalDate lowDate = lowEndDate.getValue();
			Date dateLow = Date.from(lowDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Calendar lowerDate = Calendar.getInstance();
			lowerDate.setTime(dateLow);
			lowerDate.set(Calendar.MILLISECOND, 0);
			
			LocalDate highDate = highEndDate.getValue();
			Date dateHigh = Date.from(highDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Calendar higherDate = Calendar.getInstance();
			higherDate.setTime(dateHigh);
			higherDate.set(Calendar.MILLISECOND, 0);
			
			for(int i = 0; i < albums.size(); i++){
				//For each album belonging to user
				for(int x = 0; x < albums.get(i).getPhotos().size(); x++){
					//For each photo in the album
					if(albums.get(i).getPhotos().get(x).getTimeOfCapture().before(higherDate) && albums.get(i).getPhotos().get(x).getTimeOfCapture().after(lowerDate)){
						photos.add(albums.get(i).getPhotos().get(x));
					}
				}
			}
			
		}
		else if(lowEndDate.getValue() == null && highEndDate.getValue() == null){
			for(int i = 0; i < albums.size(); i++){
				//For each album belonging to user
				for(int x = 0; x < albums.get(i).getPhotos().size(); x++){
					//For each photo in the album
					photos.add(albums.get(i).getPhotos().get(x));
					
				}
			}
		}
		if(tagsForSearchArr.isEmpty()){
			searchComplete = true;
		}
		else {
			//refine the search based on the tags written
			
			
		}
		if(searchComplete){
			//Open new window with search results
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("userSearchResults.fxml"));
				AnchorPane rootLayout = (AnchorPane) loader.load();
				UserSearchResultsController userSearchResultsController = loader.getController();
				userSearchResultsController.start(photos);
				Scene scene = new Scene(rootLayout);
				scene.getStylesheets().add("/view/application.css");
				stage.setScene(scene);
				((Node)e.getSource()).getScene().getWindow().hide();
				stage.show();
				
			}
			catch(Exception z){
				z.printStackTrace();
			}
			
		}
	}
	public void searchBackButtonClicked(ActionEvent e){
		if(lowEndDate.isEditable() || highEndDate.isEditable()){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Are you sure you don't want to finish your search?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.OK){
				searchAnchor.setVisible(false);
				createAlbumAnchor.setVisible(false);
				albums.setVisible(true);
				createButton.setDisable(false);
			}
			else{
				return;
			}
		}
	}
	
	public void editNameClicked(ActionEvent e){
		
		if (albumListView.getSelectionModel().getSelectedIndex() < 0){
			
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Select an album.");
        	alert.setContentText("Please select an album you wish to rename.");

        	alert.showAndWait();
			
		}
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Rename");
		dialog.setHeaderText("Editing Album Name");
		dialog.setContentText("Please enter your desired album name:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			ArrayList<String> albumNames = new ArrayList<String>();
			ArrayList<Album> albums = LoginController.currentUser.getAlbums();
			for (int x = 0; x < albums.size(); x++){
				albumNames.add(albums.get(x).getName());
			}
			int indexRename = albumListView.getSelectionModel().getSelectedIndex();
			
			if(result.get().length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error");
	        	alert.setHeaderText("Invalid album name.");
	        	alert.setContentText("Please enter a name with at least 1 character.");

	        	alert.showAndWait();
			} else {
				String desiredName = result.get();
				
				for (int x = 0; x < albumNames.size(); x++){
					if (desiredName.equals(albumNames.get(x)) && x != indexRename){
						Alert alert = new Alert(AlertType.ERROR);
			        	alert.setTitle("Error");
			        	alert.setHeaderText("Invalid album name.");
			        	alert.setContentText("Name conflicts with another album's name!");

			        	alert.showAndWait();
			        	return;
					}
				}
				LoginController.currentUser.getAlbums().get(indexRename).setName(desiredName);	
				start();
			}
			
		} else {
			return;
		}
		
	}
	
	public void addButtonClicked(ActionEvent e){
		ArrayList<String> tagChoices = new ArrayList<String>();
		tagChoices.add("location");
		tagChoices.add("person");
		tagChoices.add("mood");
		tagChoices.add("occasion");
		tagChoices.add("activity");
		tagChoices.add("genre");
		tagChoices.add("filter");
		tagChoices.add("quality");
		tagChoices.add("other");
		
		Dialog<String[]> dialog = new Dialog<String[]>();
		dialog.setTitle("Add a tag");
		dialog.setHeaderText("Select a tag type and input tag.");
		Label tagType = new Label("Tag type: ");
		Label tag = new Label("Tag: ");
		
		ChoiceBox<String> cb = new ChoiceBox<String>();
		TextField tagText = new TextField();
		
		cb.getItems().addAll(tagChoices);
		GridPane grid = new GridPane();
		grid.add(tagType, 1, 1);
		grid.add(cb, 2, 1);
		grid.add(tag, 1, 2);
		grid.add(tagText, 2, 2);
		
		dialog.getDialogPane().setContent(grid);
		
		ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		dialog.getDialogPane().getButtonTypes().add(okButton);
		dialog.getDialogPane().getButtonTypes().add(cancelButton);
		
		
		dialog.setResultConverter(new Callback<ButtonType, String[]>(){
			public String[] call(ButtonType button) {
				if (button == okButton){
					String[] tagTypeAndValue = new String[2];
					tagTypeAndValue[0] = cb.getValue();
					tagTypeAndValue[1] = tagText.getText();
					for (int i = 0; i < tagsForSearchArr.size(); i++){
						if (tagsForSearchArr.get(i)[0].equals(tagTypeAndValue[0]) &&
								tagsForSearchArr.get(i)[1].equals(tagTypeAndValue[1])){
							return null;
						}
					}
					tagsForSearchArr.add(tagTypeAndValue);
					return null;
				} else {
					return null;
				}
			}
		});
	
		tagsObsList = FXCollections.observableArrayList();
		Optional<String[]> result = dialog.showAndWait();
		tagsObsList.clear();
		
		for (int i = 0; i < tagsForSearchArr.size(); i++) {
			if (tagsForSearch == null) continue;
		
			tagsObsList.add((tagsForSearchArr.get(i))[0] + ": "+(tagsForSearchArr.get(i))[1]);
		}
		tagsForSearch.setItems(tagsObsList);
		tagsForSearch.getSelectionModel().clearAndSelect(0);

	}
	
	public void deleteTagButtonClicked(ActionEvent e){ 

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Tag");
		alert.setHeaderText("Are you sure you want to delete?");
		Optional<ButtonType> result = alert.showAndWait();
		int j;
		
		if(result.get() == ButtonType.OK){
			j = tagsForSearch.getSelectionModel().getSelectedIndex();
			if (j == -1) return;
			tagsForSearchArr.remove(j);
		} else {
			return;
		}
		
		tagsObsList.clear();
		for(int i = 0; i < tagsForSearchArr.size(); i++) {
			if (tagsForSearchArr == null) continue;
			if (tagsForSearchArr.get(i) == null) continue;
			tagsObsList.add((tagsForSearchArr.get(i))[0]+ ": "+(tagsForSearchArr.get(i))[1]);
		}
		tagsForSearch.setItems(tagsObsList);
		if (tagsForSearch.getItems().size() > 0) tagsForSearch.getSelectionModel().clearAndSelect(0);
	}
	
}
