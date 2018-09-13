package networking.rmi.api;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WeekTimetable implements Externalizable{
	private final LongProperty weekTimetableID = new SimpleLongProperty();
	private final StringProperty weekTimetableFrom = new SimpleStringProperty();
	private final StringProperty weekTimetableTo = new SimpleStringProperty();
	private final StringProperty weekTimetableMonday = new SimpleStringProperty();
	private final StringProperty weekTimetableTeusday = new SimpleStringProperty();
	private final StringProperty weekTimetableWednesday = new SimpleStringProperty();
	private final StringProperty weekTimetableThursday = new SimpleStringProperty();
	private final StringProperty weekTimetableFriday = new SimpleStringProperty();

	//setter and getter for 'weekTimetableID'
	public long getId() {
		return weekTimetableID.get();
	}

	public void setId(long value) {
		weekTimetableID.set(value);
	}

	public LongProperty idProperty() {
		return weekTimetableID;
	}

	//setter and getter for 'weekTimetableFrom'
	public String getFrom() {
		return weekTimetableFrom.get();
	}

	public void setFrom(String value) {
		weekTimetableFrom.set(value);
	}

	public StringProperty fromProperty() {
		return weekTimetableFrom;
	}

	//setter and getter for 'weekTimetableTo'
	public String getTo() {
		return weekTimetableTo.get();
	}

	public void setTo(String value) {
		weekTimetableTo.set(value);
	}

	public StringProperty toProperty() {
		return weekTimetableTo;
	}

	//setter and getter for 'weekTimetableMonday'
	public String getMonday() {
		return weekTimetableMonday.get();
	}

	public void setMonday(String value) {
		weekTimetableMonday.set(value);
	}

	public StringProperty mondayProperty() {
		return weekTimetableMonday;
	}

	//setter and getter for 'weekTimetableTeusday'
	public String getTeusday() {
		return weekTimetableTeusday.get();
	}

	public void setTeusday(String value) {
		weekTimetableTeusday.set(value);
	}

	public StringProperty teusdayProperty() {
		return weekTimetableTeusday;
	}

	//setter and getter for 'weekTimetableWednesday'
	public String getWednesday() {
		return weekTimetableWednesday.get();
	}

	public void setWednesday(String value) {
		weekTimetableWednesday.set(value);
	}

	public StringProperty wednesdayProperty() {
		return weekTimetableWednesday;
	}

	//setter and getter for 'weekTimetableThursday'
	public String getThursday() {
		return weekTimetableThursday.get();
	}

	public void setThursday(String value) {
		weekTimetableThursday.set(value);
	}

	public StringProperty thursdayProperty() {
		return weekTimetableThursday;
	}

	//setter and getter for 'weekTimetableFriday'
	public String getFriday() {
		return weekTimetableFriday.get();
	}

	public void setFriday(String value) {
		weekTimetableFriday.set(value);
	}

	public StringProperty fridayProperty() {
		return weekTimetableFriday;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setFrom((String) in.readObject());
		setTo((String) in.readObject());
		setMonday((String) in.readObject());
		setTeusday((String) in.readObject());
		setWednesday((String) in.readObject());
		setThursday((String) in.readObject());
		setFriday((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getFrom());
		out.writeObject(getTo());
		out.writeObject(getMonday());
		out.writeObject(getTeusday());
		out.writeObject(getWednesday());
		out.writeObject(getThursday());
		out.writeObject(getFriday());
	}
}
