package app;
import java.sql.Timestamp;

public class Photo {
	
	String caption;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public Timestamp getTimeOfCapture() {
		return timeOfCapture;
	}
	public void setTimeOfCapture(Timestamp timeOfCapture) {
		this.timeOfCapture = timeOfCapture;
	}
	Timestamp timeOfCapture;

}
