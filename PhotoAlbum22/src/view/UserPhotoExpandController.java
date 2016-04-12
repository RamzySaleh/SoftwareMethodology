package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import app.Album;
import app.Photo;
import view.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class UserPhotoExpandController {
	
	@FXML BorderPane borderPane;
	@FXML Button back;
	@FXML Button deleteTagButton;
	@FXML Button addTagButton;
	@FXML Button editCaptionButton;
	@FXML Button acceptCaption;
	@FXML Button cancelButton;
	@FXML Label timeOfCapture;
	@FXML Label username;
	@FXML ListView<String> tagListView;
	@FXML TextArea caption;
	@FXML Button logOut;
	Album currentAlbum;
	Photo currentPhoto;
	String[][] tags;
	ObservableList<String> obsList;
	MainController main = new MainController();
	
	public void init(MainController mainControl){
		main = mainControl;
	}
	public void backButtonClicked(){
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("userPhoto.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			UserPhotoController userPhotoController = loader.getController();
			userPhotoController.start(currentAlbum);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.show();
			borderPane.getScene().getWindow().hide();
		}
		catch(Exception r){
			r.printStackTrace();
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
	public void startPhotoExpand(ImageView image, Album album, int index){
		
		obsList = FXCollections.observableArrayList();
		
		username.setText(LoginController.currentUser.getName());
		currentAlbum = album;
		currentPhoto = album.getPhotos().get(index);
		caption.setText(currentPhoto.getCaption());
		caption.getStyleClass().clear(); 
		caption.getStyleClass().addAll("text-field", "text-input", "declineInput");
		image.setFitWidth(225);
		image.setFitHeight(225);
		image.setSmooth(true);
		image.setCache(true);
		image.setDisable(true);
		borderPane.setCenter(image);
		
		tags = currentPhoto.getTagsWithKeyValues();
		
		if (tags != null) {
		for (int i = 0; i < tags[0].length; i++) {
			if (tags == null) continue;
			if (tags[0][i] == null){
				continue;
			}
			obsList.add(tags[0][i] + ": "+tags[1][i]);
		}
		}
		tagListView.setItems(obsList);
		Calendar cal = currentPhoto.getTimeOfCapture();
		if (cal != null){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		timeOfCapture.setText(sdf.format(cal.getTime()));
		}
		caption.setText(currentPhoto.getCaption());
		
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
					if (tagTypeAndValue[0] == null || tagTypeAndValue[1] == null){
						return null;
					}
					if (tagTypeAndValue[0].length() == 0 || tagTypeAndValue[1].length() == 0){
						return null;
					}
					currentPhoto.addTag(cb.getValue(), tagText.getText());
					return tagTypeAndValue;
				} else {
					return null;
				}
			}
		});
	
		Optional<String[]> result = dialog.showAndWait();
		obsList.clear();
		tags = currentPhoto.getTagsWithKeyValues();
		for (int i = 0; i < tags[0].length; i++) {
			if (tags == null) continue;
			if (tags[0][i] == null) {
				continue;
			}
			obsList.add(tags[0][i] + ": "+tags[1][i]);
		}
		tagListView.setItems(obsList);
		tagListView.getSelectionModel().clearAndSelect(0);

	}
	
	public void deleteButtonClicked(ActionEvent e){ 

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Tag");
		alert.setHeaderText("Are you sure you want to delete?");
		Optional<ButtonType> result = alert.showAndWait();
		int j;
		
		if(result.get() == ButtonType.OK){
			j = tagListView.getSelectionModel().getSelectedIndex();
			if (j == -1) return;
			currentPhoto.removeTag(tags[0][j], tags[1][j]);
		} else {
			return;
		}
		
		tags = currentPhoto.getTagsWithKeyValues();
		obsList.clear();
		for(int i = 0; i < tags[0].length; i++) {
			if (tags == null) continue;
			if (tags[0][i] == null) continue;
			obsList.add(tags[0][i] + ": "+tags[1][i]);
		}
		tagListView.setItems(obsList);
		if (tagListView.getItems().size() > 0) tagListView.getSelectionModel().clearAndSelect(0);
	}
	
	public void editCaptionButton(ActionEvent e){
		
		caption.setEditable(true);
		caption.getStyleClass().clear(); 
		caption.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		
		editCaptionButton.setDisable(true);
		editCaptionButton.setVisible(false);
		
		acceptCaption.setDisable(false);
		acceptCaption.setVisible(true);
		
		cancelButton.setDisable(false);
		cancelButton.setVisible(true);
		
	}
	
	public void acceptCaptionButton(ActionEvent e){
		
		currentPhoto.setCaption(caption.getText());
		
		caption.setEditable(false);
		caption.getStyleClass().clear(); 
		caption.getStyleClass().addAll("text-field", "text-input", "declineInput");
		
		editCaptionButton.setDisable(false);
		editCaptionButton.setVisible(true);
		
		acceptCaption.setDisable(true);
		acceptCaption.setVisible(false);
		
		cancelButton.setDisable(true);
		cancelButton.setVisible(false);
		
		
	}
	
	public void cancelCaptionButton(ActionEvent e){
		
		
		caption.setText(currentPhoto.getCaption());
		
		caption.setEditable(false);
		caption.getStyleClass().clear(); 
		caption.getStyleClass().addAll("text-field", "text-input", "declineInput");

		
		editCaptionButton.setDisable(false);
		editCaptionButton.setVisible(true);
		
		acceptCaption.setDisable(true);
		acceptCaption.setVisible(false);
		
		cancelButton.setDisable(true);
		cancelButton.setVisible(false);
		
	}
	
}

