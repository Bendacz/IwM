package networking.rmi.api;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Accounts implements Externalizable{
	private final LongProperty accountsID = new SimpleLongProperty();
	private final StringProperty accountsLogin = new SimpleStringProperty();
	private final StringProperty accountsPassword = new SimpleStringProperty();
	private final StringProperty accountsRole = new SimpleStringProperty();

	//setter and getter for 'accountsID'
	public long getId() {
		return accountsID.get();
	}

	public void setId(long value) {
		accountsID.set(value);
	}

	public LongProperty idProperty() {
		return accountsID;
	}

	//setter and getter for 'accountsLogin'
	public String getLogin() {
		return accountsLogin.get();
	}

	public void setLogin(String value) {
		accountsLogin.set(value);
	}

	public StringProperty loginProperty() {
		return accountsLogin;
	}

	//setter and getter for 'accountsPassword'
	public String getPassword() {
		return accountsPassword.get();
	}

	public void setPassword(String value) {
		accountsPassword.set(value);
	}

	public StringProperty passwordProperty() {
		return accountsPassword;
	}

	//setter and getter for 'accountsRole'
	public String getRole() {
		return accountsRole.get();
	}

	public void setRole(String value) {
		accountsRole.set(value);
	}

	public StringProperty roleProperty() {
		return accountsRole;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setLogin((String) in.readObject());
		setPassword((String) in.readObject());
		setRole((String) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getLogin());
		out.writeObject(getPassword());
		out.writeObject(getRole());
	}
}
