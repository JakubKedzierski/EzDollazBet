package ezdollazbet.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerDAO {
	
	public static ObservableList<Player> getPlayersByTeamName(String teamName) throws SQLException {
		ObservableList<Player> playersList = FXCollections.observableArrayList();
		String statement = 	 "SELECT * FROM ezdollazbet.players\r\n"
				+ "Where\r\n"
				+ "TeamID = (Select TeamID From teams Where TeamName = '" + teamName +"' ) ";
		
		ResultSet set = DBUtil.selectQuery(statement);
		while(set.next()) {
			Player player = new Player();
			player.setAge(set.getInt("Age"));
			player.setFirstName(set.getString("FirstName"));
			player.setLastName(set.getString("Surname"));
			player.setMarketValue(set.getInt("MarketValue"));
			player.setOverall(set.getInt("Overall"));
			player.setPlayerId(set.getInt("PlayerID"));
			player.setPosition(set.getString("Position"));
			player.setTeamId(set.getInt("TeamID"));
			playersList.add(player);
		}
		return playersList;
	}

}
