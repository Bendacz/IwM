package projekt;

import java.io.Serializable;

public abstract class Packet implements Serializable{

	protected String device;
	protected String description;
	protected long date;

	public Packet(String device, String description, long date) {
		this.device = device;
		this.description = description;
		this.date = date;
	}

	public Packet() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return("Device Name: " + device + "\nDescription: " + description + "\nDate: " + date);
	}

	public String getFileName() {
		return(device);
	}
}