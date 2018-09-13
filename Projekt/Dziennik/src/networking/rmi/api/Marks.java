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

public class Marks implements Externalizable{
	private final LongProperty marksID = new SimpleLongProperty();
	private final StringProperty marksStudentName = new SimpleStringProperty();
	private final StringProperty marksCourseName = new SimpleStringProperty();
	private final StringProperty marksTest1 = new SimpleStringProperty();
	private final StringProperty marksTest2 = new SimpleStringProperty();
	private final StringProperty marksQuiz = new SimpleStringProperty();
	private final StringProperty marksOral = new SimpleStringProperty();
	private final StringProperty marksPaper = new SimpleStringProperty();
	private final StringProperty marksActivity = new SimpleStringProperty();

	//setter and getter for 'marksID'
	public long getId() {
		return marksID.get();
	}

	public void setId(long value) {
		marksID.set(value);
	}

	public LongProperty idProperty() {
		return marksID;
	}

	//setter and getter for 'marksStudentName'
	public String getStudentName() {
		return marksStudentName.get();
	}

	public void setStudentName(String value) {
		marksStudentName.set(value);
	}

	public StringProperty studentNameProperty() {
		return marksStudentName;
	}

	//setter and getter for 'marksCourseName'
	public String getCourseName() {
		return marksCourseName.get();
	}

	public void setCourseName(String value) {
		marksCourseName.set(value);
	}

	public StringProperty courseNameProperty() {
		return marksCourseName;
	}

	//setter and getter for 'marksTest1'
	public String getTest1() {
		return marksTest1.get();
	}

	public void setTest1(String value) {
		marksTest1.set(value);
	}

	public StringProperty test1Property() {
		return marksTest1;
	}

	//setter and getter for 'marksTest2'
	public String getTest2() {
		return marksTest2.get();
	}

	public void setTest2(String value) {
		marksTest2.set(value);
	}

	public StringProperty test2Property() {
		return marksTest2;
	}

	//setter and getter for 'marksQuiz'
	public String getQuiz() {
		return marksQuiz.get();
	}

	public void setQuiz(String value) {
		marksQuiz.set(value);
	}

	public StringProperty quizProperty() {
		return marksQuiz;
	}

	//setter and getter for 'marksOral'
	public String getOral() {
		return marksOral.get();
	}

	public void setOral(String value) {
		marksOral.set(value);
	}

	public StringProperty oralProperty() {
		return marksOral;
	}

	//setter and getter for 'marksPaper'
	public String getPaper() {
		return marksPaper.get();
	}

	public void setPaper(String value) {
		marksPaper.set(value);
	}

	public StringProperty paperProperty() {
		return marksPaper;
	}

	//setter and getter for 'marksActivity'
	public String getActivity() {
		return marksActivity.get();
	}

	public void setActivity(String value) {
		marksActivity.set(value);
	}

	public StringProperty activityProperty() {
		return marksActivity;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setStudentName((String) in.readObject());
		setCourseName((String) in.readObject());
		setTest1((String) in.readObject());
		setTest2((String) in.readObject());
		setQuiz((String) in.readObject());
		setOral((String) in.readObject());
		setPaper((String) in.readObject());
		setActivity((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getStudentName());
		out.writeObject(getCourseName());
		out.writeObject(getTest1());
		out.writeObject(getTest2());
		out.writeObject(getQuiz());
		out.writeObject(getOral());
		out.writeObject(getPaper());
		out.writeObject(getActivity());
	}
}
