package ezdollazbet.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

public class Player {
	@Getter @Setter
	private int playerId = 0;
	@Getter @Setter
	private int teamId = 0;
	

	private StringProperty position;

	private StringProperty firstName;

	private StringProperty lastName;

	private IntegerProperty marketValue;

	private IntegerProperty overall;

	private IntegerProperty age;
	
	public Player() {
		this.position = new SimpleStringProperty();
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.marketValue = new SimpleIntegerProperty();
		this.overall = new SimpleIntegerProperty();
		this.age = new SimpleIntegerProperty();
	}
	public void setPosition(String position) {
		this.position.set(position);
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
	public void setOverall(int overall) {
		this.overall.set(overall);
	}
	public void setMarketValue(int marketValue) {
		this.marketValue.set(marketValue);
	}
	public String getPosition() {
		return this.position.get();
	}
	public String getFirstName() {
		return this.firstName.get();
	}
	public String getLastName() {
		return this.lastName.get();
	}
	public int getMarketValue() {
		return this.marketValue.get();
	}
	public int getOverall() {
		return this.overall.get();
	}
	public int getAge() {
		return this.age.get();
	}


}
