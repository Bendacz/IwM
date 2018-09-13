package networking.rmi.api;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trips implements Externalizable{
	private final LongProperty tripsID = new SimpleLongProperty();
	private final ObjectProperty<LocalDate> tripsBegginingDate = new SimpleObjectProperty<>();
	private final ObjectProperty<LocalDate> tripsEndDate = new SimpleObjectProperty<>();
	private final LongProperty tripsDuration = new SimpleLongProperty();
	private final LongProperty tripsParticipants = new SimpleLongProperty();
	private final StringProperty tripsDestination = new SimpleStringProperty();
	private final StringProperty tripsTeacher = new SimpleStringProperty();

	//setter and getter for 'tripsID'
	public long getId() {
		return tripsID.get();
	}

	public void setId(long value) {
		tripsID.set(value);
	}

	public LongProperty idProperty() {
		return tripsID;
	}

	//setter and getter for 'tripsBegginingDate'
	public LocalDate getBegginingDate() {
		return tripsBegginingDate.get();
	}

	public void setBegginingDate(LocalDate value) {
		tripsBegginingDate.set(value);
	}

	public ObjectProperty begginingDateProperty() {
		return tripsBegginingDate;
	}

	//setter and getter for 'tripsEndDate'
	public LocalDate getEndDate() {
		return tripsEndDate.get();
	}

	public void setEndDate(LocalDate value) {
		tripsEndDate.set(value);
	}

	public ObjectProperty endDateProperty() {
		return tripsEndDate;
	}

	//setter and getter for 'tripsDuration'
	public long getDuration() {
		return tripsDuration.get();
	}

	public void setDuration(long value) {
		tripsDuration.set(value);
	}

	public LongProperty durationProperty() {
		return tripsDuration;
	}

	//setter and getter for 'tripsParticipants'
	public long getParticipants() {
		return tripsParticipants.get();
	}

	public void setParticipants(long value) {
		tripsParticipants.set(value);
	}

	public LongProperty participantsProperty() {
		return tripsParticipants;
	}

	//setter and getter for 'tripsDestination'
	public String getDestination() {
		return tripsDestination.get();
	}

	public void setDestination(String value) {
		tripsDestination.set(value);
	}

	public StringProperty destinationProperty() {
		return tripsDestination;
	}

	//setter and getter for 'tripsTeacher'
	public String getTeacher() {
		return tripsTeacher.get();
	}

	public void setTeacher(String value) {
		tripsTeacher.set(value);
	}

	public StringProperty teacherProperty() {
		return tripsTeacher;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setBegginingDate((LocalDate) in.readObject());
		setEndDate((LocalDate) in.readObject());
		setDuration(in.readLong());
		setParticipants(in.readLong());
		setDestination((String) in.readObject());
		setTeacher((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getBegginingDate());
		out.writeObject(getEndDate());
		out.writeObject(getDuration());
		out.writeObject(getParticipants());
		out.writeObject(getDestination());
		out.writeObject(getTeacher());
	}
}
