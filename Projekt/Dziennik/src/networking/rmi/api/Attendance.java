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

public class Attendance implements Externalizable{
	private final LongProperty attendanceID = new SimpleLongProperty();
	private final StringProperty attendanceStudentName = new SimpleStringProperty();
	private final StringProperty attendanceCourseName = new SimpleStringProperty();
	private final ObjectProperty<LocalDate> attendanceDate = new SimpleObjectProperty<>();
	private final StringProperty attendanceAttendance = new SimpleStringProperty();
	private final StringProperty attendancePreparation = new SimpleStringProperty();
	private final StringProperty attendanceTeacher = new SimpleStringProperty();

	//setter and getter for 'attendanceID'
	public long getId() {
		return attendanceID.get();
	}

	public void setId(long value) {
		attendanceID.set(value);
	}

	public LongProperty idProperty() {
		return attendanceID;
	}

	//setter and getter for 'attendanceStudentName'
	public String getStudentName() {
		return attendanceStudentName.get();
	}

	public void setStudentName(String value) {
		attendanceStudentName.set(value);
	}

	public StringProperty studentNameProperty() {
		return attendanceStudentName;
	}

	//setter and getter for 'attendanceCourseName'
	public String getCourseName() {
		return attendanceCourseName.get();
	}

	public void setCourseName(String value) {
		attendanceCourseName.set(value);
	}

	public StringProperty courseNameProperty() {
		return attendanceCourseName;
	}

	//setter and getter for 'attendanceDate'
	public LocalDate getDate() {
		return attendanceDate.get();
	}

	public void setDate(LocalDate value) {
		attendanceDate.set(value);
	}

	public ObjectProperty dateProperty() {
		return attendanceDate;
	}

	//setter and getter for 'attendanceAttendance'
	public String getAttendance() {
		return attendanceAttendance.get();
	}

	public void setAttendance(String value) {
		attendanceAttendance.set(value);
	}

	public StringProperty attendanceProperty() {
		return attendanceAttendance;
	}

	//setter and getter for 'attendancePreparation'
	public String getPreparation() {
		return attendancePreparation.get();
	}

	public void setPreparation(String value) {
		attendancePreparation.set(value);
	}

	public StringProperty preparationProperty() {
		return attendancePreparation;
	}

	//setter and getter for 'attendanceTeacher'
	public String getTeacher() {
		return attendanceTeacher.get();
	}

	public void setTeacher(String value) {
		attendanceTeacher.set(value);
	}

	public StringProperty teacherProperty() {
		return attendanceTeacher;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setStudentName((String) in.readObject());
		setCourseName((String) in.readObject());
		setDate((LocalDate) in.readObject());
		setAttendance((String) in.readObject());
		setPreparation((String) in.readObject());
		setTeacher((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getStudentName());
		out.writeObject(getCourseName());
		out.writeObject(getDate());
		out.writeObject(getAttendance());
		out.writeObject(getPreparation());
		out.writeObject(getTeacher());
	}
}
