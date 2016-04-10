package app;

import java.util.ArrayList;
import java.io.Serializable;
import java.sql.Timestamp;

public class Album implements Serializable {
	
	String name;
	int numOfPhotos;
	ArrayList<Photo> photos = new ArrayList<Photo>();
	Timestamp oldestPhoto;
	Timestamp newestPhoto;
	
	public Album(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumOfPhotos() {
		return numOfPhotos;
	}
	public void setNumOfPhotos(int numOfPhotos) {
		this.numOfPhotos = numOfPhotos;
	}
	public Timestamp getOldestPhoto() {
		return oldestPhoto;
	}
	public void setOldestPhoto(Timestamp oldestPhoto) {
		this.oldestPhoto = oldestPhoto;
	}
	public Timestamp getNewestPhoto() {
		return newestPhoto;
	}
	public void setNewestPhoto(Timestamp newestPhoto) {
		this.newestPhoto = newestPhoto;
	}
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}

}
