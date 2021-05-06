package ezdollazbet.models;

import java.sql.SQLException;

public class AcountDAO {
	public static void insertAccount(String login, String password) throws SQLException {
		String insertAccountStatement = "INSERT INTO Accounts (Login,Password) VALUES ("+
				"'" +login + "', "+ "'" +password + "');"; 
		DBUtil.updateQuery(insertAccountStatement);
	}
}
