package mybnb;

import java.sql.*;

public class Initialize {
	
	private static Initialize instance = new Initialize();
	public static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String USER = "user1";
	public static final String PASSWORD = "password";
	
	
	
	public Initialize(){
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	private Connection createConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR");
		}
	}
	
}
