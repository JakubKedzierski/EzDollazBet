package ezdollazbet.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.Getter;

public class DBUtil {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/ezdollazbet";
	@Getter
	private static Connection connection = null;

	public static void connectDb() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException error) {
			System.out.println("JDBC Driver not found");
			error.printStackTrace();
			throw new SQLException("JDBC Driver not found");
		}

		String username = "root";
		String password = "test";

		try {
			connection = DriverManager.getConnection(DB_CONNECTION_URL, username, password);
		} catch (SQLException error) {
			System.out.println("Wrong connection url");
			error.printStackTrace();
			throw error;
		}

	}

	public static void disconnectDb() throws SQLException {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException error) {
			error.printStackTrace();
			throw error;
		}
	}
	
	public static void updateQuery(String queryStatement) throws SQLException {
	    if(connection == null || connection.isClosed()) connectDb();
		
	    Statement statement = connection.createStatement();  
	    statement.executeUpdate(queryStatement);
	    
	}
	
	public static ResultSet selectQuery(String queryStatement) throws SQLException{
	    if(connection == null || connection.isClosed()) connectDb();
	    
	    Statement statement = connection.createStatement();  
	    ResultSet result = statement.executeQuery(queryStatement);
	    
	    return result;
	}

}
