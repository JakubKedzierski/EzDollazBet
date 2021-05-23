package ezdollazbet.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ezdollazbet.models.Client;

public class ClientDAO {
	public static void insertClient(String login,String name, String lastName, String password, int age) throws SQLException {
		AcountDAO.insertAccount(login, password);
		
		String insertClientStatement = "INSERT INTO Clients (Login,FirstName,Surname,Age,Balance) VALUES ("+
				"?,?,?,?,'0');"; 
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, login);
		arguments.put(2, name);
		arguments.put(3, lastName);
		arguments.put(4, Integer.toString(age));
		
		DBUtil.safeUpdateQuery(insertClientStatement, arguments);
	}
	
	public static ResultSet getClientSetByLogin(String login) throws SQLException {
		String clientStatement = "SELECT * FROM clients WHERE Login= ? ;";
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, login);
		
		return DBUtil.safeSelectQuery(clientStatement, arguments);
	}
	
	public static Client getClientByLogin(String login) throws SQLException {
		ResultSet set = ClientDAO.getClientSetByLogin(login);
		set.next();
		Client client = new Client();
		client.setAge(set.getInt("Age"));
		client.setBalance(set.getDouble("Balance"));
		client.setFirstName(set.getString("FirstName"));
		client.setLastName(set.getString("Surname"));
		client.setLogin(set.getString("Login"));
		client.setId(set.getInt("ClientID"));
		
		return client;
	}
	
	public static void updateClient(Client client) throws SQLException{
		String statement = "UPDATE clients "
				+ "SET Balance = '"+client.getBalance().get() +"' , "
				+ "Age = '" + client.getAge().get() + "' , "
				+ "Surname = '" + client.getLastName().get() + "' , " 
				+ "FirstName = '" + client.getFirstName().get() + "' , " 
				+ "Login = '" + client.getLogin().get() + "' "
				+ "WHERE (ClientID = '" + client.getClientID().get() + "');";
		DBUtil.updateQuery(statement);
	}
	

	
	
}
