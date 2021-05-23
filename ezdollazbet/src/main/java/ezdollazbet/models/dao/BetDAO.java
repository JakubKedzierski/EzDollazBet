package ezdollazbet.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ezdollazbet.models.Bet;
import ezdollazbet.models.Client;
import ezdollazbet.models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BetDAO {
	
	public static ResultSet getBetsSetByGameId(int id) throws SQLException {
		String statement = "SELECT * FROM bets WHERE GameID = ? ;" ;
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, Integer.toString(id));
		
		return DBUtil.safeSelectQuery(statement, arguments);
	}
	
	public static Bet getBetByBetId(int betId) throws SQLException {
		String statement = "SELECT * FROM bets WHERE BetID = ? ;" ;
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, Integer.toString(betId));
		
		ResultSet set = DBUtil.safeSelectQuery(statement, arguments);
		Bet bet = new Bet();
		while(set.next()) {
			bet.setId(set.getInt("BetID"));
			bet.setGameId(set.getInt("GameID"));
			bet.setBetType(set.getInt("BetType"));
			bet.setBettingOdd(set.getDouble("BettingOdd"));
		}
		return bet;
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
	
	public static void bookBetByBetIdAndClientId(int betId, int clientId, int stake) throws SQLException{
		String statement = "INSERT INTO bookedbets (ClientID,BetID,Stake) VALUES (?,?,?);"; 
		
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, Integer.toString(clientId));
		arguments.put(2, Integer.toString(betId));
		arguments.put(3, Integer.toString(stake));
		
		DBUtil.safeUpdateQuery(statement, arguments);
	}
	
	public static void bookBet(Bet bet, Client client, int stake) throws SQLException {
		int betId = bet.getBetId().get();
		int clientId = client.getClientID().get();
		BetDAO.bookBetByBetIdAndClientId(betId, clientId, stake);
	}
}
