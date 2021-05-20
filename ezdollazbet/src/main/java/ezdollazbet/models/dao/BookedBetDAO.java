package ezdollazbet.models.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import ezdollazbet.models.Bet;
import ezdollazbet.models.BookedBet;
import ezdollazbet.models.Client;
import ezdollazbet.models.Game;
import ezdollazbet.models.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookedBetDAO {
	
	public static ObservableList<BookedBet> getBookedBetsByUserLogin(String userLogin) throws SQLException{
		ObservableList<BookedBet> bookedBetsList = FXCollections.observableArrayList();
		Client client = ClientDAO.getClientByLogin(userLogin);
		String statement = "SELECT * FROM ezdollazbet.bookedbets \r\n"
				+ "WHERE ClientID = " + client.getClientID().get();
		ResultSet set = DBUtil.selectQuery(statement);
		while(set.next()) {
			Bet bet = BetDAO.getBetByBetId(set.getInt("BetID"));
			Game game = GameDAO.getGameByGameId(bet.getGameId().get());
			Team host = TeamDAO.getTeamById(Integer.parseInt(game.getHost().get()));
			Team guest = TeamDAO.getTeamById(Integer.parseInt(game.getGuest().get()));

			BookedBet bookedBet = new BookedBet();
			bookedBet.setBettingOdd(bet.getBettingOdd().get());
			bookedBet.setBetType(bet.getBetType().get());
			bookedBet.setGuest(guest.getTeamName().get());
			bookedBet.setHost(host.getTeamName().get());
			bookedBet.setMatchDate(game.getMatchDay().get());
			bookedBet.setMatchStatus(game.getMatchStatus().get());
			bookedBet.setStake(set.getDouble("Stake"));
			bookedBetsList.add(bookedBet);
		}
		
		return bookedBetsList;
	}
}
