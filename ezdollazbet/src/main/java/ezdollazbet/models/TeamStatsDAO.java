package ezdollazbet.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamStatsDAO {

	public static ObservableList<TeamStats> getAllTeamStats() {
		ObservableList<TeamStats> teamStatsList = FXCollections.observableArrayList();
		String statement = 	 "select TeamStatsID, t.TeamName , n"
				+ "from games  \r\n"
				+ "inner join teams t on t.TeamID = teamstats.Host;";
		teamStatsList.add(new TeamStats());
		return teamStatsList;
	}
}
