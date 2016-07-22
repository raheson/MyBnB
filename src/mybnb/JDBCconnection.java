package myBNB;

import java.sql.*;

public class JDBCconnection {

	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost/mybnb";
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "vethush";
		System.out.println("Connecting to database...");
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			
			System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      String sql = "CREATE TABLE LOGIN " +
	    		  	   "(username VARCHAR(255) not null, " +
	    		       " password VARCHAR(255) not null, " +
	    		  	   " PRIMARY KEY (username))";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE USERS " +
		                   "(occupation VARCHAR(255), " + 
		                   " address VARCHAR(255), " + 
		                   " name VARCHAR(255), " +
		                   " sin INT not null, " +
		                   " PRIMARY KEY ( sin ))"; 

		      stmt.executeUpdate(sql);

		      sql = "CREATE TABLE RENTERS " + 
	                   " (credit_card_number INT not null AUTO_INCREMENT, " +
	                   " PRIMARY KEY ( credit_card_number ))" +
	                   " SELECT * FROM USERS ";
		      stmt.executeUpdate(sql);

		      sql = "CREATE TABLE HOSTS " +
		    		  " SELECT * FROM USERS";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE LISTINGS " +
	                   "(type VARCHAR(255), " + 
	                   " location INT not null, " +
	                   " address VARCHAR(255), " +
	                   " amenities VARCHAR(255), " +
	                   " PRIMARY KEY ( location ))";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE LISTINGS_LISTED " +
		    		  " SELECT address FROM LISTINGS";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE LISTINGS_RENTED " +
		    		  " SELECT address FROM LISTINGS";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE CALENDAR " +
		    		  "(rental_price INT, " +
		    		  " days_available VARCHAR(255))";
		      stmt.executeUpdate(sql);
		      
		      System.out.println("Created tables in given database...");
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   System.out.println("Goodbye!");
	}
}
