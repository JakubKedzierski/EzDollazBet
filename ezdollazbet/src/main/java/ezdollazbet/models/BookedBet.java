package ezdollazbet.models;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

public class BookedBet {
	private SimpleStringProperty host = new SimpleStringProperty();
	@Getter
	private SimpleStringProperty guest = new SimpleStringProperty();
	@Getter
	private SimpleObjectProperty<Date> matchDay = new  SimpleObjectProperty<>();
	@Getter
	private SimpleStringProperty matchStatus = new SimpleStringProperty();
	
	@Getter
	private SimpleDoubleProperty stake = new SimpleDoubleProperty();

	@Getter
	private SimpleIntegerProperty betType = new SimpleIntegerProperty();
	
	@Getter
	private SimpleDoubleProperty bettingOdd = new SimpleDoubleProperty();
	
	@Getter
	private SimpleDoubleProperty possibleWinStake = new SimpleDoubleProperty();
	

	public void setHost(String host) {
		this.host.set(host);
	}
	public void setGuest(String guest) {
		this.guest.set(guest);
	}

    public void setMatchDate(Date matchDay){
        this.matchDay.set(matchDay);
    }
    public void setMatchStatus(String status) {
    	this.matchStatus.set(status);
    }
    
	public void setBetType(int betType) {
		this.betType.set(betType);
	}
	public void setBettingOdd(double bettingOdd) {
		this.bettingOdd.set(bettingOdd);
	}
	public void setStake(double stake) {
		this.stake.set(stake);
	}
	public void setPossibleWinStake(double possibleWinStake) {
		this.possibleWinStake.set(possibleWinStake);
	}

}
