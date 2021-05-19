package ezdollazbet.models;

import java.util.Date;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;

public class Client {
	@Getter 
	private IntegerProperty clientID;
	@Getter 
	private StringProperty login;
	@Getter 
	private StringProperty firstName;
	@Getter 
	private StringProperty lastName;
	@Getter 
	private IntegerProperty age;
	@Getter 
	private DoubleProperty balance;
	
	public Client() {
		this.clientID = new SimpleIntegerProperty();
		this.login = new SimpleStringProperty();
		this.firstName  = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.age = new SimpleIntegerProperty();
		this.balance = new SimpleDoubleProperty();
	}
	
	public void setId(int id) {
		this.clientID.set(id);
	}
	public void setLogin(String login) {
		this.login.set(login);
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public void setAge(int age) {
		this.age.set(age);
	}
    public void setBalance(double balance){
        this.balance.set(balance);
    }
    public void decrementBalance(double value) {
    	this.balance.set(balance.get()-value);

    	if(this.balance.get() < 0) throw new IllegalArgumentException("Balance below 0");
    }
    
    public void increacseBalance(double value) {
    	this.balance.set(balance.get()+value);

    	if(this.balance.get() < 0) throw new IllegalArgumentException("Balance below 0");
    }

}
