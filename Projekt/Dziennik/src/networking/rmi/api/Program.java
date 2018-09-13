package networking.rmi.api;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Program implements Externalizable{
	private final LongProperty programID = new SimpleLongProperty();
	private final StringProperty programCourseName = new SimpleStringProperty();
	private final StringProperty programCourseTitle = new SimpleStringProperty();
	private final StringProperty programTeacher = new SimpleStringProperty();
	private final StringProperty programComments = new SimpleStringProperty();

	//setter and getter for 'programID'
	public long getId() {
		return programID.get();
	}

	public void setId(long value) {
		programID.set(value);
	}

	public LongProperty idProperty() {
		return programID;
	}

	//setter and getter for 'programCourseName'
	public String getCourseName() {
		return programCourseName.get();
	}

	public void setCourseName(String value) {
		programCourseName.set(value);
	}

	public StringProperty courseNameProperty() {
		return programCourseName;
	}

	//setter and getter for 'programCourseTitle'
	public String getCourseTitle() {
		return programCourseTitle.get();
	}

	public void setCourseTitle(String value) {
		programCourseTitle.set(value);
	}

	public StringProperty courseTitleProperty() {
		return programCourseTitle;
	}

	//setter and getter for 'programTeacher'
	public String getTeacher() {
		return programTeacher.get();
	}

	public void setTeacher(String value) {
		programTeacher.set(value);
	}

	public StringProperty teacherProperty() {
		return programTeacher;
	}

	//setter and getter for 'programComments'
	public String getComments() {
		return programComments.get();
	}

	public void setComments(String value) {
		programComments.set(value);
	}

	public StringProperty commentsProperty() {
		return programComments;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setCourseName((String) in.readObject());
		setCourseTitle((String) in.readObject());
		setTeacher((String) in.readObject());
		setComments((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getCourseName());
		out.writeObject(getCourseTitle());
		out.writeObject(getTeacher());
		out.writeObject(getComments());
	}
}
