package app;
import java.sql.Timestamp;
import java.util.Calendar;

public class Photo {
	
	String caption;
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
