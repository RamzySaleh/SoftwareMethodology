package app;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//holds all information associated with a user
	String name = "";
	public ArrayList<Album> albums = new ArrayList<Album>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Album> getAlbums(){
		return albums;
	}
	
	public User(String name){
		this.name = name;
	}
}
