package app;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javafx.scene.image.Image;

public class Photo implements Serializable {
	
	Image image;
	String caption;
	

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
	public void setTimeOfCapture(Calendar timeOfCapture) {
		this.timeOfCapture = timeOfCapture;
	}
	Calendar timeOfCapture;

}
