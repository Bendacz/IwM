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

public class Person implements Externalizable{

	private final LongProperty id = new SimpleLongProperty();
	private final StringProperty firstName = new SimpleStringProperty();
	private final StringProperty lastName = new SimpleStringProperty();
	private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();

	public LocalDate getBirthDate() {
		return birthDate.get();
	}

	public void setBirthDate(LocalDate value) {
		birthDate.set(value);
	}

	public ObjectProperty birthDateProperty() {
		return birthDate;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String value) {
		lastName.set(value);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String value) {
		firstName.set(value);;
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public long getId() {
		return id.get();
	}

	public void setId(long value) {
		id.set(value);
	}

	public LongProperty idProperty() {
		return id;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setFirstName((String) in.readObject());
		setLastName((String) in.readObject());
		setBirthDate((LocalDate) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getFirstName());
		out.writeObject(getLastName());
		out.writeObject(getBirthDate());
	}

}
