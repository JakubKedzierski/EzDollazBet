package ezdollazbet.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AcountDAO {
	public static void insertAccount(String login, String password) throws SQLException {
		String insertAccountStatement = "INSERT INTO Accounts (Login,Password)"
				+ " VALUES (?,?);"; 
		
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, login);
		arguments.put(2, password);
		DBUtil.safeUpdateQuery(insertAccountStatement, arguments);
	}
	
	public static boolean areCredentialsGood(String login, String password) throws SQLException {
		
		String checkCredentials = "SELECT * From accounts Where " +
		"login = ? and Password = ? ;";
		
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, login);
		arguments.put(2, password);
		
		ResultSet result = DBUtil.safeSelectQuery(checkCredentials, arguments);
		if(result.next()) {
			return true;
		}
		
		return false;
	}
	
}
