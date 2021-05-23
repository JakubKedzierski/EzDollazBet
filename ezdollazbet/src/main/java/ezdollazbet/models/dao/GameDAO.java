package ezdollazbet.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ezdollazbet.models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameDAO {
	public static ObservableList<Game> getAllAwaitingGames() throws SQLException {
		ObservableList<Game> gamesList = FXCollections.observableArrayList();

		String statement = "select GameID, t.TeamName as 'Host', t2.TeamName as 'Guest', HostGoals, GuestGoals, MatchDay, MatchStatus\r\n"
				+ "from games  \r\n"
				+ "inner join teams t on t.TeamID = games.Host\r\n"
				+ "inner join teams t2 on t2.TeamID = games.Guest";
		
		ResultSet games = DBUtil.selectQuery(statement);
		
		while (games.next()) {
			Game game = new Game();
			game.setId(games.getInt("GameID"));
			
			game.setHost(games.getString("Host"));
			game.setGuest(games.getString("Guest"));
			
			game.setHostGoals(games.getInt("HostGoals"));
			game.setGuestGoals(games.getInt("GuestGoals"));
			game.setMatchDate(games.getDate("MatchDay"));
			game.setMatchStatus(games.getString("MatchStatus"));
			gamesList.add(game);
		}

		return gamesList;
	}
	
	public static Game getGameByGameId(int gameId) throws SQLException {
		String statement = "SELECT * FROM games Where GameID = ?";
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, Integer.toString(gameId));
		ResultSet games = DBUtil.safeSelectQuery(statement, arguments);
		Game game = new Game();
		while (games.next()) {
			
			game.setId(games.getInt("GameID"));
			
			game.setHost(games.getString("Host"));
			game.setGuest(games.getString("Guest"));
			
			game.setHostGoals(games.getInt("HostGoals"));
			game.setGuestGoals(games.getInt("GuestGoals"));
			game.setMatchDate(games.getDate("MatchDay"));
			game.setMatchStatus(games.getString("MatchStatus"));

		}
		return game;
	}
}
