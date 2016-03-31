package app;

import java.util.ArrayList;

public class User {
	//holds all information associated with a user
	String name = "";
	ArrayList<Album> albums = new ArrayList<Album>();
	
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
