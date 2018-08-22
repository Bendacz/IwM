package projekt;

public class Alarm extends Packet{

	public int channelNr;
	public int threshold;
	public int n;
	public String direction;
	
	public Alarm(String device, String description, long date, int channelNr, int threshold, int n) {
		super(device, description, date);
		this.channelNr = channelNr;
		this.threshold = threshold;
		
		switch(n) {
			case -1:
				this.direction = "Down";
				break;
			case 0:
				this.direction = "Any";
				break;
			case 1:
				this.direction = "Up";
				break;
			default:
				this.direction = "Wrong parameter";
		}
	}
	
	
	
	public String toString() {
		return(super.toString() + '\n' + "Channel: " + channelNr + '\n' + "Threshold: " + threshold + '\n' + "Direction: " + direction);
	}
}
