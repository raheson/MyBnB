package myBNB;

import java.sql.*;
import myBNB.Users;
import java.io.*;
import myBNB.Listings;
public class JDBCmain {

	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost/mybnb";
	
	public static void main(String[] args) throws ClassNotFoundException{
		createListing(123456789);
	}
	
	public static Users createUser() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "vethush";
		System.out.println("Connecting to database...");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = null;
		Statement stmt = null;
		Users user = new Users();
		String occupation;
		String address;
		String name;
		int sin;
		String username;
		String password;
		int credit_card_number;
		String rhbool;
		
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			try {
				System.out.println("Enter your occupation: ");
	            occupation = br.readLine();
	            user.setOccupation(occupation);
	            System.out.println("Enter your address: ");
	            address = br.readLine();
	            user.setAddress(address);
	            System.out.println("Enter your name: ");
	            name = br.readLine();
	            user.setName(name);
	            System.out.println("Enter your sin: ");
	            sin = Integer.parseInt(br.readLine());
	            user.setSin(sin);
	            System.out.println("Enter your username: ");
	            username = br.readLine();
	            user.setUsername(username);
	            System.out.println("Enter your password: ");
	            password = br.readLine();
	            user.setPassword(password);
	            System.out.println("Enter your credit card number: ");
	            credit_card_number = Integer.parseInt(br.readLine());
	            user.setCredit_card_number(credit_card_number);
	            System.out.println("Inserting records into the table...");
	            stmt = conn.createStatement();
	            String sql = "INSERT INTO USERS " +
	                         "VALUES ( '" + user.getOccupation() + "' , '" + user.getAddress() + "' , '" + user.getName() 
	                         + "' ," + user.getSin() + ", '" + user.getUsername() + "' , '" + user.getPassword() +
	                         "' ," + user.getCredit_card_number() + ")";
	            stmt.executeUpdate(sql);
	            System.out.println("Are you a renter or a host?");
	            rhbool = br.readLine();
	            if(rhbool.equals("renter")){
	            	sql = "INSERT INTO RENTERS " +
	                         "VALUES ( '" + user.getOccupation() + "' , '" + user.getAddress() + "' , '" + user.getName() 
	                         + "' ," + user.getSin() + ", '" + user.getUsername() + "' , '" + user.getPassword() +
	                         "' ," + user.getCredit_card_number() + ")";
	            	stmt.executeUpdate(sql);
	            }
	            else if(rhbool.equals("host")){
	            	sql = "INSERT INTO HOSTS " +
	                         "VALUES ( '" + user.getOccupation() + "' , '" + user.getAddress() + "' , '" + user.getName() 
	                         + "' ," + user.getSin() + ", '" + user.getUsername() + "' , '" + user.getPassword() +
	                         "' ," + user.getCredit_card_number() + ")";
	            	stmt.executeUpdate(sql);
	            }
	            sql = "INSERT INTO LOGIN " +
	            	  "VALUES ( '" + user.getUsername() + "' , '" + user.getPassword() + "')";
	            stmt.executeUpdate(sql);
	            System.out.println("Inserted records into the table...");
	            
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
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
		   return user;
	}
	
	public static void deleteUser(int sin, String username) throws ClassNotFoundException{
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
			    String sql = "DELETE FROM USERS " +
			                   "WHERE sin = " + sin;
			    stmt.executeUpdate(sql);
			    sql = "DELETE FROM RENTERS WHERE sin = " + sin;
			    stmt.executeUpdate(sql);
			    sql = "DELETE FROM HOSTS WHERE sin = " + sin;
			    stmt.executeUpdate(sql);
			    sql = "DELETE FROM LOGIN WHERE username = '" + username + "'";
	            stmt.executeUpdate(sql);
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
	
	public static Listings createListing(int sin) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "vethush";
		System.out.println("Connecting to database...");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = null;
		Statement stmt = null;
		Listings listing = new Listings();
		String type;
		int latitude;
		int longitude;
		String listing_address;
		String amenities;
		String postal_code;
		int rental_price;
		
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			try {
				System.out.println("Enter the type of listing: ");
	            type = br.readLine();
	            listing.setType(type);
	            System.out.println("Enter the listing address: ");
	            listing_address = br.readLine();
	            listing.setListing_address(listing_address);
	            System.out.println("Enter the latitude: ");
	            latitude = Integer.parseInt(br.readLine());
	            listing.setLatitude(latitude);
	            System.out.println("Enter the longitude: ");
	            longitude = Integer.parseInt(br.readLine());
	            listing.setLongitude(longitude);
	            System.out.println("Enter the amenities: ");
	            amenities = br.readLine();
	            listing.setAmenities(amenities);
	            System.out.println("Enter the postal code: ");
	            postal_code = br.readLine();
	            listing.setPostal_code(postal_code);
	            System.out.println("Enter the rental price: ");
	            rental_price = Integer.parseInt(br.readLine());
	            listing.setRental_price(rental_price);
	            System.out.println("Inserting records into the table...");
	            
	            stmt = conn.createStatement();
	            String sql = "INSERT INTO LISTINGS " +
	                         "VALUES ( '" + listing.getType() + "' , "+ listing.getLatitude() + " , " +
	                         listing.getLongitude() + " , '" + listing.getListing_address() + "' , '" + 
	                         listing.getPostal_code() + "' , '" + listing.getAmenities() + "' ," + listing.getRental_price() + ")";
	            stmt.executeUpdate(sql);
	            sql = "INSERT INTO LISTINGS_LISTED SELECT * FROM USERS, LISTINGS " +
		                   "WHERE (latitude = " + listing.getLatitude() + ") AND (longitude = " + listing.getLatitude() + ") AND (sin = " +
		                   sin + ")";
	            stmt.executeUpdate(sql);
	            System.out.println("Inserted records into the table...");
	            
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
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
		return null;
	}
	
	public static void deleteListing(int latitude, int longitude) throws ClassNotFoundException{
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
			    String sql = "DELETE FROM LISTINGS " +
			                   "WHERE (latitude = " + latitude + ") AND (longitude = " + longitude + ")";
			    stmt.executeUpdate(sql);
			    sql = "DELETE FROM LISTINGS_RENTED " +
		                   "WHERE (latitude = " + latitude + ") AND (longitude = " + longitude + ")";
			    stmt.executeUpdate(sql);
			    sql = "DELETE FROM LISTINGS_LISTED " +
	                   "WHERE (latitude = " + latitude + ") AND (longitude = " + longitude + ")";
			    stmt.executeUpdate(sql);
	            
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
	
	public static void bookListing(int sin, int latitude, int longitude) throws ClassNotFoundException{
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
			    String sql = "INSERT INTO LISTINGS_RENTED SELECT * FROM USERS, LISTINGS " +
			                   "WHERE (latitude = " + latitude + ") AND (longitude = " + longitude + ") AND (sin = " +
			                   sin + ")";
			    stmt.executeUpdate(sql);
	            
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
	public static void cancelBooking(int sin, int latitude, int longitude) throws ClassNotFoundException{
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
			    String sql = "DELETE FROM LISTINGS_RENTED " +
			                   "WHERE (latitude = " + latitude + ") AND (longitude = " + longitude + ") AND (sin = " +
			                   sin + ")";
			    stmt.executeUpdate(sql);
	            
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

