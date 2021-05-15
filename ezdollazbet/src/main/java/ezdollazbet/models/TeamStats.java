package ezdollazbet.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class TeamStats {

	@Getter
	private StringProperty teamName;

	@Getter
	private IntegerProperty teamStatsId;

	@Getter
	private IntegerProperty games;

	@Getter
	private IntegerProperty wins;

	@Getter
	private IntegerProperty loses;
	
	@Getter 
	private IntegerProperty draws;
	
	@Getter 
	private IntegerProperty points;
	
	public TeamStats() {
		this.teamName = new SimpleStringProperty();
		this.teamStatsId = new SimpleIntegerProperty();
		this.games = new SimpleIntegerProperty();
		this.wins = new SimpleIntegerProperty();
		this.loses = new SimpleIntegerProperty();
		this.draws = new SimpleIntegerProperty();
		this.points = new SimpleIntegerProperty();
	}
	
	public void setTeam(Team team) {
		this.teamName.set(team.getTeamName().get());;
	}

	public void setTeamStatsId(int teamStatsId) {
		this.teamStatsId.set(teamStatsId);
	}
	
	public void setGames(int games) {
		this.games.set(games);
	}
	
	public void setWins(int wins) {
		this.wins.set(wins);
	}
	
	public void setLoses(int loses) {
		this.loses.set(loses);
	}
	
	public void setDraws(int draws) {
		this.draws.set(draws);
	}
	
	public void setPoints(int points) {
		this.points.set(points);
	}
}
