package ezdollazbet.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ezdollazbet.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerDAO {
	
	public static ObservableList<Player> getPlayersByTeamName(String teamName) throws SQLException {
		ObservableList<Player> playersList = FXCollections.observableArrayList();
		String statement = 	 "SELECT * FROM ezdollazbet.players\r\n"
				+ "Where\r\n"
				+ "TeamID = (Select TeamID From teams Where TeamName = ? ) ";
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1,teamName);
		
		ResultSet set = DBUtil.safeSelectQuery(statement, arguments);
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
