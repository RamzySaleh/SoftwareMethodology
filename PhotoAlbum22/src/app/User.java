package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class User implements Serializable {
	//holds all information associated with a user
	String name = "";
	public ArrayList<Album> albums = new ArrayList<Album>();
	public ArrayList<Hashtable> arrayHashTable = new ArrayList<Hashtable>();
	
	public ArrayList<Hashtable> getTags() {
		return arrayHashTable;
	}

	public void setTags(ArrayList<Hashtable> arrayHashTable) {
		this.arrayHashTable = arrayHashTable;
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
