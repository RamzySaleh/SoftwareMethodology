package app;

import java.util.ArrayList;
import java.sql.Timestamp;

public class Album {
	
	String name;
	int numOfPhotos;
	Timestamp oldestPhoto;
	Timestamp newestPhoto;
	ArrayList<Photo> photos;
}
