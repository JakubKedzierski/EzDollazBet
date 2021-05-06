package ezdollazbet.models;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;

public class Client {
	@Getter @Setter
	private IntegerProperty clientID;
	@Getter @Setter
	private StringProperty login;
	@Getter @Setter
	private StringProperty firstName;
	@Getter @Setter
	private StringProperty lastName;
	@Getter @Setter
	private IntegerProperty age;
	@Getter @Setter
	private IntegerProperty balance;
	
	public Client() {
		this.clientID = new SimpleIntegerProperty();
		this.login = new SimpleStringProperty();
		this.firstName  = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.age = new SimpleIntegerProperty();
		this.balance = new SimpleIntegerProperty();
	}
}
