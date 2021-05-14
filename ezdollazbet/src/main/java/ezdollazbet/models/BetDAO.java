package ezdollazbet.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BetDAO {
	
	public static ResultSet getBetsSetByGameId(int id) throws SQLException {
		String statement = "SELECT * FROM bets WHERE GameID = '" + id +"';" ;
		return DBUtil.selectQuery(statement);
	}
	
	public static ObservableList<Bet> getBetsByGame(Game game) throws SQLException {
		int id = game.getId().intValue();
		ResultSet set = BetDAO.getBetsSetByGameId(id);
		ObservableList<Bet> betList =  FXCollections.observableArrayList();
		
		while (set.next()) {
			Bet bet = new Bet();
			bet.setId(set.getInt("BetID"));
			bet.setGameId(set.getInt("GameID"));
			bet.setBetType(set.getInt("BetType"));
			bet.setBettingOdd(set.getDouble("BettingOdd"));
			betList.add(bet);
		}
		return betList;
	}
}
