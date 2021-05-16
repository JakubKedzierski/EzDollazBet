package ezdollazbet.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamStatsDAO {

	public static ObservableList<TeamStats> getAllTeamStats() throws SQLException {
		ObservableList<TeamStats> teamStatsList = FXCollections.observableArrayList();
		String statement = 	 "select TeamStatsID, t.TeamName, Games, Wins, Loses, Draws, Points \n"
				+ "from teamstats  \r\n"
				+ "inner join teams t on t.TeamID = teamstats.TeamID " +
				"\n ORDER BY Points desc;";
		ResultSet set = DBUtil.selectQuery(statement);
		while(set.next()) {
			TeamStats teamStats = new TeamStats();
			teamStats.setDraws(set.getInt("Draws"));
			teamStats.setGames(set.getInt("Games"));
			teamStats.setLoses(set.getInt("Loses"));
			teamStats.setPoints(set.getInt("Points"));
			teamStats.setTeamName(set.getString("TeamName"));
			teamStats.setTeamStatsId(set.getInt("TeamStatsID"));
			teamStats.setWins(set.getInt("Wins"));
			teamStats.setPlace(set.getRow());
			teamStatsList.add(teamStats);
		}
		return teamStatsList;
	}
}
