package app;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import javafx.scene.image.ImageView;

public class Photo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageView image;
	String caption;
	public Hashtable<String, String> tagsHashTable = new Hashtable<String, String>();
	
	public String[] tagsAsString(){
		String[] tagsAsSingleString = new String[tagsHashTable.size()];
		
		tagsAsSingleString = (String[]) tagsHashTable.values().toArray();
		
		return tagsAsSingleString;
	}
	
	public void addTag(String key, String value){
		this.tagsHashTable.put(key, value);
	}
	
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public Calendar getTimeOfCapture() {
		return timeOfCapture;
	}
	public void setTimeOfCapture(File fp) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		sdf.format(fp.lastModified());
		timeOfCapture = sdf.getCalendar();
		timeOfCapture.set(Calendar.MILLISECOND,0);
	}
	Calendar timeOfCapture;

}
