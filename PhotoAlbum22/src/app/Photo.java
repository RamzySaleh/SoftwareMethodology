package app;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Photo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient Image image;
	String caption;
	public Hashtable<String, String> tagsHashTable = new Hashtable<String, String>();
	
	public String[] getTagsAsString(){
		
		String[] tagsAsSingleString = new String[tagsHashTable.size()];
		
		tagsAsSingleString = (String[]) tagsHashTable.values().toArray();
		
		return tagsAsSingleString;
	}
	
	public String[][] getTagsWithKeyValues(){
		String[][] tagsWithKey = new String[2][tagsHashTable.size()];
		int j = 0;
		Enumeration e = tagsHashTable.keys();
		
	    while (e.hasMoreElements()) {
	      String key = (String) e.nextElement();
	      tagsWithKey[0][j] = key;
	      tagsWithKey[1][j] = tagsHashTable.get(key);
	      j++;
	    }
	    if (j == 0) return null;
	    return tagsWithKey;
	}
	
	public void addTag(String key, String value){
		this.tagsHashTable.put(key, value);
	}
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
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
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        image = SwingFXUtils.toFXImage(ImageIO.read(ois), null);
    }
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
	     oos.defaultWriteObject();
	     if(image != null){
	       	ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", oos);
	     }
	}
}
