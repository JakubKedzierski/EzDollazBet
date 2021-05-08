package ezdollazbet.models;

import java.sql.SQLException;

public class ClientDAO {
	public static void insertClient(String login,String name, String lastName, String password, int age) throws SQLException {
		AcountDAO.insertAccount(login, password);
		
		String insertClientStatement = "INSERT INTO Clients (Login,FirstName,Surname,Age,Balance) VALUES ("+
				"'" +login + "', "+ "'" +name + "', "+ "'" +lastName + "', "+   "'" +age + "', "+
				 "'0');"; 
		
		DBUtil.updateQuery(insertClientStatement);
	}
	

	
	
}
