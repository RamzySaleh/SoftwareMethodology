package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField; 

public class SongLibController {
	
	@FXML ListView<String> songListView;
	
	@FXML TextField displaySong;
	@FXML TextField displayArtist;
	@FXML TextField displayAlbum;
	@FXML TextField displayYear;
	@FXML Button addButton;
	@FXML Button editButton;
	@FXML Button deleteButton;
	
	@FXML TextField songTextField;
	@FXML TextField artistTextField;
	@FXML TextField albumTextField;
	@FXML TextField yearTextField;
	@FXML Button acceptButton;
	@FXML Button cancelButton;
	
	
	/**
	 * 
	 * addButtonClicked COMPLETED!
	 * 
	 */
	public void addButtonClicked(ActionEvent e){ 
		System.out.println("Add BUTTON CLICKED!");
		
		// Make field editable and apply styling
		songTextField.setEditable(true);
		artistTextField.setEditable(true);
		albumTextField.setEditable(true);
		yearTextField.setEditable(true);
		songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		
	
	}

	/**
	 * 
	 * editButtonClicked COMPLETED!
	 * 
	 */
	public void editButtonClicked(ActionEvent e){ 
		System.out.println("Edit BUTTON CLICKED!");
		
		// Load display information so we can edit it
		songTextField.setText(displaySong.getText());
		artistTextField.setText(displayArtist.getText());
		albumTextField.setText(displayAlbum.getText());
		yearTextField.setText(displayYear.getText());
		
		// Make field editable and apply styling
		songTextField.setEditable(true);
		artistTextField.setEditable(true);
		albumTextField.setEditable(true);
		yearTextField.setEditable(true);
		songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "acceptingInput");
		
		
	}
	
	/**
	 * 
	 * deleteButtonClicked NEEDS WORK, see TODO
	 * 
	 */
	
	public void deleteButtonClicked(ActionEvent e){ 
		System.out.println("Delete BUTTON CLICKED!");
	
		/** TODO
		1. Prompt user if they are sure they want to delete
		2. Delete from ListView AND ArrayList!
		*/
	}
	
	
	
	/**
	 * 
	 * acceptButtonClicked NEEDS WORK, see TODO
	 * 
	 */
	public void acceptButtonClicked(ActionEvent e){ 
		System.out.println("Accept BUTTON CLICKED!");
		
		
		// Check to see if the user hit accept and they were not editing or adding!
		if(songTextField.isEditable() == false){
			return;
		}
		
		// Stop field from being editable and apply styling
		songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		songTextField.setEditable(false);
		artistTextField.setEditable(false);
		albumTextField.setEditable(false);
		yearTextField.setEditable(false);
		
		/** TODO
		1. Prompt user if they are sure they want to add/edit song
		2. Check for conflicts with current song list!
	 	3. Inform user of name conflict (if there is one) in a pop-up dialog
	 	4. Add to ListView AND ArrayList!
		*/
		
		// Clear information after use
		songTextField.clear();
		artistTextField.clear();
		albumTextField.clear();
		yearTextField.clear();
		
	}
	
	/**
	 * 
	 * cancelButtonClicked COMPLETED!
	 * 
	 */
	public void cancelButtonClicked(ActionEvent e){ 
		System.out.println("Cancel BUTTON CLICKED!");
		
		// Clear Information from add/delete, user would like to cancel
		songTextField.clear();
		artistTextField.clear();
		albumTextField.clear();
		yearTextField.clear();
		
		// Stop field from being editable and apply styling
		songTextField.getStyleClass().clear(); songTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		artistTextField.getStyleClass().clear(); artistTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		albumTextField.getStyleClass().clear(); albumTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		yearTextField.getStyleClass().clear(); yearTextField.getStyleClass().addAll("text-field", "text-input", "declineInput");
		songTextField.setEditable(false);
		artistTextField.setEditable(false);
		albumTextField.setEditable(false);
		yearTextField.setEditable(false);
	}
	
	

}
