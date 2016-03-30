package app;

import java.util.ArrayList;

public class User {
	//holds all information associated with a user
	String name = "";
	ArrayList<Album> albums;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String name){
		this.name = name;
	}
}
