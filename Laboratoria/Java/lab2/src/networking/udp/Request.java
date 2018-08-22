package networking.udp;

import java.io.Serializable;
import projekt.Packet;

public class Request extends Packet implements Serializable{

	private static final long serialVersionUID = 1L;
	public String requestedDeviceName;
	public String requestedChannelNr;
	public String list;
	
	public Request(String requestedDeviceName, String requestedChannelNr) {
		super();
		this.requestedDeviceName = requestedDeviceName;
		this.requestedChannelNr = requestedChannelNr;
	}
	
	public String getRequestedDeviceName() {
		return requestedDeviceName;
	}
	
	public String getRequestedChannelNr() {
		return requestedChannelNr;
	}
	
	public String getRequestedFileName(){
		return(requestedDeviceName + "_" + requestedChannelNr);
	}
}