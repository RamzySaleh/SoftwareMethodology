package app;
	
import java.io.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.AdminController;
import view.LoginController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class PhotoAlbum extends Application implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private LoginController loginController;
	public ArrayList<User> users;
	public static final String storeDir = "dat";
	public static final String storeFile = "users.dat";
	@Override
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/login.fxml"));
			rootLayout = (AnchorPane) loader.load();
			loginController = loader.getController();
			Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Sign in");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PhotoAlbum pa = new PhotoAlbum();
		if (loadFromDisk() != null) {
			pa = loadFromDisk();
			AdminController.users = pa.users;
		}
		else {
			System.out.println("Didn't find anything");
		}
		launch(args);
		pa.users = AdminController.getUsers();
		saveToDisk(pa);
	}
	
	public static PhotoAlbum loadFromDisk(){
		ObjectInputStream ois = null;
		PhotoAlbum pa = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
			pa = (PhotoAlbum)ois.readObject();
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			try { ois.close(); } catch (IOException e1) {}
			return null;
		}
		try { ois.close(); } catch (IOException e1) {}
		return pa;
	}
	
	public static void saveToDisk(PhotoAlbum pa){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
			oos.writeObject(pa);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
}
