package ezdollazbet.models;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;


public class Game {
	@Getter
	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	@Getter
	private SimpleStringProperty host = new SimpleStringProperty();
	@Getter
	private SimpleStringProperty guest = new SimpleStringProperty();
	@Getter
	private SimpleIntegerProperty hostGoals = new SimpleIntegerProperty();
	@Getter
	private SimpleIntegerProperty guestGoals = new SimpleIntegerProperty();
	@Getter
	private SimpleObjectProperty<Date> matchDay = new  SimpleObjectProperty<>();
	@Getter
	private SimpleStringProperty matchStatus = new SimpleStringProperty();
	
	public void setId(int id) {
		this.id.set(id);
	}
	public void setHost(String host) {
		this.host.set(host);
	}
	public void setGuest(String guest) {
		this.guest.set(guest);
	}
	public void setHostGoals(int hoastGoals) {
		this.hostGoals.set(hoastGoals);
	}
	public void setGuestGoals(int guestGoals) {
		this.guestGoals.set(guestGoals);
	}
	
    public void setMatchDate(Date matchDay){
        this.matchDay.set(matchDay);
    }
    public void setMatchStatus(String status) {
    	this.matchStatus.set(status);
    }
    

	

}
