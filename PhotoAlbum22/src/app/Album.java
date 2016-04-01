package app;

import java.util.ArrayList;
import java.sql.Timestamp;

public class Album {
	
	String name;
	int numOfPhotos;
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
	ArrayList<Photo> photos;
}
