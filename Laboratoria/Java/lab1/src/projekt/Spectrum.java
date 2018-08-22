package projekt;

public class Spectrum<T> extends Sequence<T>{

	public String scaling;

	public Spectrum(String device, String description, long date, int channelNr, String unit, double resolution, T[] buffer, String scaling) {
		super(device, description, date, channelNr, unit, resolution, buffer);
		
		switch(scaling.toLowerCase()) {
		case "linear":
			this.scaling = "Linear";
			break;
		case "logarythmic" :
			this.scaling = "Logarythmic";
			break;
		default:
			this.scaling = "Wrong parameter";
		}
	}

	public String toString() {
		return(super.toString() + "\nScalnig: " + scaling);
	}
}