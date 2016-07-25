package myBNB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class queries {

	public static void searchbyLocation(double latitude, double longitude) throws ClassNotFoundException{
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
			    String sql = "SELECT * FROM LISTINGS " +
			                   "WHERE (latitude = " + latitude + ") AND (longitude = " + longitude + ")";
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()){
			    	String type = rs.getString("type");
			    	latitude = rs.getDouble("latitude");
			    	longitude = rs.getDouble("longitude");
			    	String listing_address = rs.getString("listing_address");
			    	String postal_code = rs.getString("postal_code");
			    	String amenities = rs.getString("amenities");
			    	int rental_price = rs.getInt("rental_price");
			    	
			    	System.out.print("type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			    }
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
	
	public static void searchbyPrice(int rental_price) throws ClassNotFoundException{
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
			    String sql = "SELECT * FROM LISTINGS " +
			                   "WHERE (rental_price = " + rental_price + ") ORDER BY rental_price";
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()){
			    	String type = rs.getString("type");
			    	double latitude = rs.getDouble("latitude");
			    	double longitude = rs.getDouble("longitude");
			    	String listing_address = rs.getString("listing_address");
			    	String postal_code = rs.getString("postal_code");
			    	String amenities = rs.getString("amenities");
			    	rental_price = rs.getInt("rental_price");
			    	
			    	System.out.print("type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			    }
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
	
	public static void searchbyPostalCode(String postal_code) throws ClassNotFoundException{
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
			    String sql = "SELECT * FROM LISTINGS " +
			                   "WHERE (postal_code = '" + postal_code + "')";
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()){
			    	String type = rs.getString("type");
			    	double latitude = rs.getDouble("latitude");
			    	double longitude = rs.getDouble("longitude");
			    	String listing_address = rs.getString("listing_address");
			    	postal_code = rs.getString("postal_code");
			    	String amenities = rs.getString("amenities");
			    	int rental_price = rs.getInt("rental_price");
			    	
			    	System.out.print("type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			    }
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
	
	public static void searchbyAddress(String address) throws ClassNotFoundException{
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
			    String sql = "SELECT * FROM LISTINGS " +
			                   "WHERE (listing_address = '" + address + "')";
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()){
			    	String type = rs.getString("type");
			    	double latitude = rs.getDouble("latitude");
			    	double longitude = rs.getDouble("longitude");
			    	String listing_address = rs.getString("listing_address");
			    	String postal_code = rs.getString("postal_code");
			    	String amenities = rs.getString("amenities");
			    	int rental_price = rs.getInt("rental_price");
			    	
			    	System.out.print("type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			    }
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



}
