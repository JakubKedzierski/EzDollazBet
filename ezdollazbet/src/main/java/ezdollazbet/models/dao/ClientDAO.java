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
				+ "SET Balance = ?, "
				+ "Age = ?, "
				+ "Surname = ?, " 
				+ "FirstName = ?, " 
				+ "Login = ? "
				+ "WHERE (ClientID = ?);";
		
		Map<Integer,String> arguments = new HashMap<Integer,String>();
		arguments.put(1, Double.toString(client.getBalance().get()));
		arguments.put(2, Integer.toString(client.getAge().get()));
		arguments.put(3, client.getLastName().get());
		arguments.put(4,  client.getFirstName().get() );
		arguments.put(5,  client.getLogin().get());
		arguments.put(6,  Integer.toString(client.getClientID().get()));
		DBUtil.safeUpdateQuery(statement, arguments);
	}
	

	
	
}
