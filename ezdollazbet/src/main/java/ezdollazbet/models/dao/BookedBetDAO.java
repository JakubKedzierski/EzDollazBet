package ezdollazbet.models.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import ezdollazbet.models.Bet;
import ezdollazbet.models.BookedBet;
import ezdollazbet.models.Client;
import ezdollazbet.models.Game;
import javafx.collections.ObservableList;

public class BookedBetDAO {
	
	public static ObservableList<BookedBet> getBookedBetsByUserLogin(String userLogin) throws SQLException{
		Client client = ClientDAO.getClientByLogin(userLogin);
		String statement = "SELECT * FROM ezdollazbet.bookedbets \r\n"
				+ "WHERE ClientID = " + client.getClientID().get();
		ResultSet set = DBUtil.selectQuery(statement);
		while(set.next()) {
			Bet bet = BetDAO.getBetByBetId(set.getInt("BetID"));
			Game game = GameDAO.getGameByGameId(bet.getBetId().get());
			
			
		}
		
		return null;
	}
}
