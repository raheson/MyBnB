package mybnb;

import java.sql.*;

public class JDBCconnection {

	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost/mybnb";
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			
			System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE TABLE USERS " +
	                   "(occupation VARCHAR(255), " + 
	                   " address VARCHAR(255), " + 
	                   " name VARCHAR(255), " +
	                   " sin INT not null, " +
	                   " username VARCHAR(255), " +
	                   " password VARCHAR(255), " +
	                   " credit_card_number INT not null, " +
	                   " UNIQUE ( username ), " +
	                   " PRIMARY KEY ( sin ))";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE LOGIN " +
	    		  	   "(SELECT username, password FROM USERS)";
		      stmt.executeUpdate(sql);
		     
		      sql = "CREATE TABLE RENTERS " + 
	                   " (SELECT * FROM USERS " +
	                   ")";
		      stmt.executeUpdate(sql);
			
		      sql = "CREATE TABLE HOSTS " +
		    		  " SELECT * FROM USERS";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE LISTINGS " +
	                   "(type VARCHAR(255), " + 
	                   " latitude FLOAT not null, " +
	                   " longitude FLOAT not null, " +
	                   " listing_address VARCHAR(255), " +
	                   " postal_code VARCHAR(255), " +
	                   " amenities VARCHAR(255), " +
	                   " rental_price DOUBLE not null, " +
	                   " PRIMARY KEY ( latitude, longitude ))";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE LISTINGS_LISTED " +
		    		  " SELECT * FROM LISTINGS, HOSTS";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE LISTNGS_RENTED " +
		    		  " SELECT * FROM LISTINGS, RENTERS";
		      stmt.executeUpdate(sql);
		      
		      sql = "CREATE TABLE CALENDAR " +
		    		  "(" +
		    		  " days_available INT)" +
		    		  " SELECT * FROM LISTINGS";
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
