package ezdollazbet.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class Team {
	@Getter 
	private IntegerProperty teamId;
	@Getter 
	private StringProperty teamName;
	@Getter 
	private StringProperty city;
	
	public void setTeamId(int teamId) {
		this.teamId.set(teamId);
	}
	
	public void setTeamName(String teamName) {
		this.teamName.set(teamName);
	}
	
	public void setCity(String city) {
		this.city.set(city);
	}
}
