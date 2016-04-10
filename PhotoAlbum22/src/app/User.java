package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class User implements Serializable {
	//holds all information associated with a user
	String name = "";
	public ArrayList<Album> albums = new ArrayList<Album>();
	public Hashtable<String,Photo> tags = new Hashtable<String,Photo>();
	
	public Hashtable<String, Photo> getTags() {
		return tags;
	}

	public void setTags(Hashtable<String, Photo> tags) {
		this.tags = tags;
	}

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
