package app;

import java.util.Comparator;

/**
 * 
 * @author Ramzy Saleh
 * @author Sara Zayed
 * 	
 *
 */

public class Song {
	
	String song ="";
	String artist = "";
	String year = "";
	String album = "";
	boolean newestSong = false;
		
	public Song(String song, String artist, String year, String album){
		this.song = song;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public boolean equals(String secondSong, String secondArtist){
		if (this.song.equalsIgnoreCase(secondSong) && this.artist.equalsIgnoreCase(secondArtist)){
			return true;
		} else {
			return false;
		}
	}
	
	
	public void editSong(String songName) {
		this.song = songName;
	}
	
	public void editArtist(String artistName) {
		this.artist = artistName;
	}
	
	public void editAlbum(String albumName) {
		this.album = albumName;
	}
	
	public void editYear(String editedYear) {
		this.year = editedYear;
	}
	
	public void setNewestSong(boolean b){
		this.newestSong = b;
	}
	
	public String getSongName() {
		return this.song;
	}
	
	public String getArtistName() {
		return this.artist;
	}
	
	public String getAlbumName() {
		return this.album;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public boolean getNewestSong(){
		return this.newestSong;
	}

	public static class CustomComparator implements Comparator<Song> {
	    @Override
	    public int compare(Song o1, Song o2) {
	        return o1.getSongName().compareToIgnoreCase(o2.getSongName());
	    }
	}
}
