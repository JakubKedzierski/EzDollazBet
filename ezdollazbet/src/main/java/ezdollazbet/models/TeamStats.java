package ezdollazbet.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class TeamStats {

	private Team team;

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
	
	public void setTeam(Team team) {
		this.team = team;
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
