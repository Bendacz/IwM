package projekt;

public class TimeHistory<T> extends Sequence<T>{

	//private static final long serialVersionUID = 1L;
	public double sensivity;

	public TimeHistory(String device, String description, long date, int channelNr, String unit, double resolution, T[] buffer, double sensivity){
		super(device, description, date, channelNr, unit, resolution, buffer);
		this.sensivity = sensivity;
	}

	public String toString(){
		return(super.toString() + "\nSensivity: " + sensivity);
	}	
}