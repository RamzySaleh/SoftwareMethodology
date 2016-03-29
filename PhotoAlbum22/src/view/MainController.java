package view;

import javafx.fxml.FXML;

public class MainController {
	@FXML AdminController adminController;
	@FXML LoginController loginController;

	@FXML public void initialize(){
		adminController.init(this);
		loginController.init(this);
	}
	
	
}
