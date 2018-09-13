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

public class Subjects implements Externalizable{
	private final LongProperty subjectsID = new SimpleLongProperty();
	private final ObjectProperty<LocalDate> subjectsDate = new SimpleObjectProperty<>();
	private final LongProperty subjectsNumber = new SimpleLongProperty();
	private final StringProperty subjectsCourseName = new SimpleStringProperty();
	private final StringProperty subjectsSubject = new SimpleStringProperty();
	private final LongProperty subjectsPresent = new SimpleLongProperty();
	private final LongProperty subjectsAbsent = new SimpleLongProperty();
	private final StringProperty subjectsTeacher = new SimpleStringProperty();

	//setter and getter for 'personalDataID'
	public long getId() {
		return subjectsID.get();
	}

	public void setId(long value) {
		subjectsID.set(value);
	}

	public LongProperty idProperty() {
		return subjectsID;
	}

	//setter and getter for 'subjectsDate'
	public LocalDate getDate() {
		return subjectsDate.get();
	}

	public void setDate(LocalDate value) {
		subjectsDate.set(value);
	}

	public ObjectProperty dateProperty() {
		return subjectsDate;
	}

	//setter and getter for 'subjectsNumber'
	public long getNumber() {
		return subjectsNumber.get();
	}

	public void setNumber(long value) {
		subjectsNumber.set(value);
	}

	public LongProperty numberProperty() {
		return subjectsNumber;
	}

	//setter and getter for 'subjectsCourseName'
	public String getCourseName() {
		return subjectsCourseName.get();
	}

	public void setCourseName(String value) {
		subjectsCourseName.set(value);
	}

	public StringProperty courseNameProperty() {
		return subjectsCourseName;
	}

	//setter and getter for 'subjectsSubject'
	public String getSubject() {
		return subjectsSubject.get();
	}

	public void setSubject(String value) {
		subjectsSubject.set(value);
	}

	public StringProperty subjectProperty() {
		return subjectsSubject;
	}

	//setter and getter for 'subjectsPresent'
	public long getPresent() {
		return subjectsPresent.get();
	}

	public void setPresent(long value) {
		subjectsPresent.set(value);
	}

	public LongProperty presentProperty() {
		return subjectsPresent;
	}

	//setter and getter for 'subjectsAbsent'
	public long getAbsent() {
		return subjectsAbsent.get();
	}

	public void setAbsent(long value) {
		subjectsAbsent.set(value);
	}

	public LongProperty absentProperty() {
		return subjectsAbsent;
	}

	//setter and getter for 'subjectsTeacher'
	public String getTeacher() {
		return subjectsTeacher.get();
	}

	public void setTeacher(String value) {
		subjectsTeacher.set(value);
	}

	public StringProperty teacherProperty() {
		return subjectsTeacher;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setDate((LocalDate) in.readObject());
		setNumber(in.readInt());
		setCourseName((String) in.readObject());
		setSubject((String) in.readObject());
		setPresent(in.readLong());
		setAbsent(in.readLong());
		setTeacher((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getDate());
		out.writeObject(getNumber());
		out.writeObject(getCourseName());
		out.writeObject(getSubject());
		out.writeObject(getPresent());
		out.writeObject(getAbsent());
		out.writeObject(getTeacher());
	}
}
