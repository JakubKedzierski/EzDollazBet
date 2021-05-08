package ezdollazbet.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AcountDAO {
	public static void insertAccount(String login, String password) throws SQLException {
		String insertAccountStatement = "INSERT INTO Accounts (Login,Password) VALUES ("+
				"'" +login + "', "+ "'" +password + "');"; 
		DBUtil.updateQuery(insertAccountStatement);
	}
	
	public static boolean areCredentialsGood(String login, String password) throws SQLException {
		
		String checkCredentials = "SELECT * From accounts Where " +
		"login='" + login + "' and Password='" + password+"'";
		ResultSet result = DBUtil.selectQuery(checkCredentials);
		if(result.next()) {
			return true;
		}
		
		return false;
	}
}
