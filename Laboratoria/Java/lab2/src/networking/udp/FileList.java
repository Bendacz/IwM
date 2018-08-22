package networking.udp;

import projekt.Packet;

public class FileList extends Packet{
	
	private static final long serialVersionUID = 1L;
	public String requestedDeviceName;
	public String requestedChannelNr;
	public String list;
	
	public FileList(String requestedDeviceName, String requestedChannelNr, String list) {
		this.requestedDeviceName = requestedDeviceName;
		this.requestedChannelNr = requestedChannelNr;
		this.list = list;
	}
	
	public String toString() {
		return(list);
	}
	
	public String getRequestedDeviceName() {
		return requestedDeviceName;
	}
	
	public String getRequestedChannelNr() {
		return requestedChannelNr;
	}
	
	public String getFileName(){
		return("Device Name: " + requestedDeviceName + "\nChannel Number: " + requestedChannelNr);
	}
}
