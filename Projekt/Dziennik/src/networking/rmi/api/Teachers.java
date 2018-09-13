package networking.rmi.api;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teachers implements Externalizable{
	private final LongProperty teachersID = new SimpleLongProperty();
	private final StringProperty teachersTeacherName = new SimpleStringProperty();
	private final StringProperty teachersCourseName = new SimpleStringProperty();

	//setter and getter for 'teachersID'
	public long getId() {
		return teachersID.get();
	}

	public void setId(long value) {
		teachersID.set(value);
	}

	public LongProperty idProperty() {
		return teachersID;
	}

	//setter and getter for 'teachersTeacherName'
	public String getTeacherName() {
		return teachersTeacherName.get();
	}

	public void setTeacherName(String value) {
		teachersTeacherName.set(value);
	}

	public StringProperty teacherNameProperty() {
		return teachersTeacherName;
	}

	//setter and getter for 'teachersCourseName'
	public String getCourseName() {
		return teachersCourseName.get();
	}

	public void setCourseName(String value) {
		teachersCourseName.set(value);
	}

	public StringProperty courseNameProperty() {
		return teachersCourseName;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setTeacherName((String) in.readObject());
		setCourseName((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getTeacherName());
		out.writeObject(getCourseName());
	}
}
