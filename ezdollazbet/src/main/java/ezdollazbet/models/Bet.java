package ezdollazbet.models;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

public class Bet {
	@Getter
	private SimpleIntegerProperty betId = new SimpleIntegerProperty();
	@Getter
	private SimpleIntegerProperty gameId = new SimpleIntegerProperty();
	@Getter
	private SimpleIntegerProperty betType = new SimpleIntegerProperty();
	@Getter
	private SimpleDoubleProperty bettingOdd = new SimpleDoubleProperty();
	
	
	public void setId(int id) {
		this.betId.set(id);
	}
	public void setGameId(int id) {
		this.gameId.set(id);
	}
	public void setBetType(int betType) {
		this.betType.set(betType);
	}
	public void setBettingOdd(double bettingOdd) {
		this.bettingOdd.set(bettingOdd);
	}

    
}
