package ezdollazbet.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class TeamStats {

	private StringProperty teamName;

	private IntegerProperty teamStatsId;

	private IntegerProperty games;

	private IntegerProperty wins;

	private IntegerProperty loses;

	private IntegerProperty draws;

	private IntegerProperty points;
	
	private IntegerProperty place;

	public TeamStats() {
		this.teamName = new SimpleStringProperty();
		this.teamStatsId = new SimpleIntegerProperty();
		this.games = new SimpleIntegerProperty();
		this.wins = new SimpleIntegerProperty();
		this.loses = new SimpleIntegerProperty();
		this.draws = new SimpleIntegerProperty();
		this.points = new SimpleIntegerProperty();
		this.place = new SimpleIntegerProperty();
	}

	public String getTeamName() {
		return this.teamName.get();
	}

	public int getGames() {
		return this.games.get();
	}

	public int getWins() {
		return this.wins.get();
	}

	public int getLoses() {
		return this.loses.get();
	}

	public int getDraws() {
		return this.draws.get();
	}

	public int getPoints() {
		return this.points.get();
	}
	
	public int getPlace() {
		return this.place.get();
	}

	public void setTeam(Team team) {
		this.teamName.set(team.getTeamName().get());
	}

	public void setTeamName(String teamName) {
		this.teamName.set(teamName);
	}
	
	public void setPlace(int place) {
		this.place.set(place);
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
