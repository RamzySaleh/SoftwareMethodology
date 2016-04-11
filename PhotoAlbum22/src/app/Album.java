package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;

public class Album implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	int numOfPhotos;
	ArrayList<Photo> photos = new ArrayList<Photo>();
	Calendar oldestPhoto;
	Calendar newestPhoto;
	
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
	public Calendar getOldestPhoto() {
		return oldestPhoto;
	}
	public void setOldestPhoto(Calendar oldestPhoto) {
		this.oldestPhoto = oldestPhoto;
	}
	public Calendar getNewestPhoto() {
		return newestPhoto;
	}
	public void setNewestPhoto(Calendar newestPhoto) {
		this.newestPhoto = newestPhoto;
	}
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}
	public void addOnePhoto(Photo photo){
		photos.add(photo);
	}

}
