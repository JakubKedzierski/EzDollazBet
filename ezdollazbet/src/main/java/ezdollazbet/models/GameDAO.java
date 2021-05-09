package ezdollazbet.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameDAO {
	public static ObservableList<Game> getAllAwaitingGames() throws SQLException {
		ObservableList<Game> gamesList = FXCollections.observableArrayList();

		String statement = "SELECT * FROM games;";

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
}
