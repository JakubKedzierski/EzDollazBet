package ezdollazbet.models;

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

	public static void connectDb() throws ClassNotFoundException, SQLException {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException error) {
			System.out.println("JDBC Driver not found");
			error.printStackTrace();
			throw error;
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
	
	public static void updateQuery(String queryStatement) {
		
	}

	public static void main(String args[]) {
		try {
			DBUtil.connectDb();

			Statement stmt;
			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("select * from players");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			
			DBUtil.disconnectDb();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
