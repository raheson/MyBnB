package myBNB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class reports {

	public static void numbookingsPostCode(String postal_code) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "vethush";
		System.out.println("Connecting to database...");
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			try {
				stmt = conn.createStatement();
				
				String sql = "SELECT COUNT(*) as rowcount FROM LISTINGS_RENTED " +
		                   "WHERE (postal_code = " + postal_code + ")";
				ResultSet rs = stmt.executeQuery(sql);
	            rs.next();
	            int count = rs.getInt("rowcount");
	            System.out.println("There are " + count +" bookings within this postal code.");
	            rs.close();
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }       
			
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
	
	public static void numlistingsPostCode(String postal_code) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "vethush";
		System.out.println("Connecting to database...");
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			try {
				stmt = conn.createStatement();
				
				String sql = "SELECT COUNT(*) as rowcount FROM LISTINGS " +
		                   "WHERE (postal_code = " + postal_code + ")";
				ResultSet rs = stmt.executeQuery(sql);
	            rs.next();
	            int count = rs.getInt("rowcount");
	            System.out.println("There are " + count +" listings within this postal code.");
	            rs.close();
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }       
			
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
