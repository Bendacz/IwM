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

public class Events implements Externalizable{
	private final LongProperty eventsID = new SimpleLongProperty();
	private final ObjectProperty<LocalDate> eventsDate = new SimpleObjectProperty<>();
	private final StringProperty eventsInfo = new SimpleStringProperty();

	//setter and getter for 'eventsID'
	public long getId() {
		return eventsID.get();
	}

	public void setId(long value) {
		eventsID.set(value);
	}

	public LongProperty idProperty() {
		return eventsID;
	}

	//setter and getter for 'eventsDate'
	public LocalDate getDate() {
		return eventsDate.get();
	}

	public void setDate(LocalDate value) {
		eventsDate.set(value);
	}

	public ObjectProperty dateProperty() {
		return eventsDate;
	}

	//setter and getter for 'eventsInfo'
	public String getInfo() {
		return eventsInfo.get();
	}

	public void setInfo(String value) {
		eventsInfo.set(value);
	}

	public StringProperty infoProperty() {
		return eventsInfo;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setDate((LocalDate) in.readObject());
		setInfo((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getDate());
		out.writeObject(getInfo());
	}
}
