package ezdollazbet.models;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

public class BookedBet {
	private SimpleStringProperty host = new SimpleStringProperty();

	private SimpleStringProperty guest = new SimpleStringProperty();

	private SimpleObjectProperty<Date> matchDay = new  SimpleObjectProperty<>();

	private SimpleStringProperty matchStatus = new SimpleStringProperty();

	private SimpleDoubleProperty stake = new SimpleDoubleProperty();

	private SimpleIntegerProperty betType = new SimpleIntegerProperty();

	private SimpleDoubleProperty bettingOdd = new SimpleDoubleProperty();

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
		if(!(bettingOdd.get() <= 0)) {
			setPossibleWinStake(stake*bettingOdd.get());
		}
	}
	public void setPossibleWinStake(double possibleWinStake) {
		this.possibleWinStake.set(possibleWinStake);
	}
	
	public String getHost() {
		return this.host.get();
	}
	public String getGuest() {
		return this.guest.get();
	}

    public Date getMatchDay(){
    	return this.matchDay.get();
    }
    public String getMatchStatus() {
    	return this.matchStatus.get();
    }
    
	public int getBetType() {
		return this.betType.get();
	}
	public double getBettingOdd() {
		return this.bettingOdd.get();
	}
	public double getStake() {
		return this.stake.get();
	}
	public double getPossibleWinStake() {
		return this.possibleWinStake.get();
	}


}
