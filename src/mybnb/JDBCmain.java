package mybnb;

import java.sql.*;
import java.util.Scanner;

import mybnb.Users;

import java.io.*;

import mybnb.Listings;
public class JDBCmain {

	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost/mybnb";

	public static void main(String[] args) throws ClassNotFoundException{
		//User Interface
		System.out.println("");
		System.out.println("*****************************************************");
		System.out.println("*---------------------------------------------------*");
		System.out.println("*--------------- WELCOME TO MyBnB ------------------*");
		System.out.println("*---------------------------------------------------*");
		System.out.println("*****************************************************");
		System.out.println("");
		System.out.println("Rent unique accommodations from local hosts all over the world!");
		System.out.println("Feel at home anywhere you go in the world with MyBnB.");
		userInterface(10);
	}
	public static void userInterface(int i) throws ClassNotFoundException{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		if( i == 10){
			System.out.println("");
			System.out.println("-----------------------------------------------------------");
			System.out.println("                        Loading ...                        ");
			System.out.println("-----------------------------------------------------------");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Choose the following options to continue => (1)Login (2)Create/Update User (3)Delete User (4)Exit : ");
			int n = reader.nextInt();
			if (n == 1){
				System.out.println("Welcome back! Enter the following: ");
				System.out.print("Are you a RR (renter) or HT (host): ");
				String who = reader.next();
				System.out.print("Username: ");
				String username = reader.next();
				System.out.print("Password: ");
				String password = reader.next();
				login(username, password, who);
			}
			else if (n == 2){
				createUser();
			}
			else if (n == 3){
				System.out.println("To delete a user, please indicate:");
				System.out.print("Username: ");
				String user = reader.next();
				System.out.print("Social Insurance Number (9 digits): ");
				int sin = reader.nextInt();
				deleteUser(sin, user);

			}
			else if (n == 4){
				System.out.println("Have a great day!");
				System.exit(0);
			}
		}
		// Renter
		else if (i == 20){
			System.out.println("");
			System.out.println("-----------------------------------------------------------");
			System.out.println("                        Loading ...                        ");
			System.out.println("-----------------------------------------------------------");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Welcome, choose the following options to continue:");
			System.out.println("(5)Book Listing (6)Cancel Booking (7)Home Page");
			int r = reader.nextInt();
			if (r == 5){
				search();
			}
			else if (r == 6){
				System.out.println("To cancel a booking, please start off by entering the sin and latitude, longitude of the location: ");
				System.out.println("SIN (9 digits): ");
				int hsin = reader.nextInt();
				System.out.println("Latitude: ");
				double hlat = reader.nextDouble();
				System.out.println("Longitude: ");
				double hlong = reader.nextDouble();
				cancelBooking(hsin, hlat, hlong, "RR");
			}
			else {
				userInterface(10);
			}
		}
		// Hosts
		else if (i == 30){
			System.out.println("");
			System.out.println("-----------------------------------------------------------");
			System.out.println("                        Loading ...                        ");
			System.out.println("-----------------------------------------------------------");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Welcome, choose the following options to continue:");
			System.out.println("(5)Create Listing (6)Delete Listing (7)Book Listing (8)Cancel Booking (9)Home Page");
			int h = reader.nextInt();
			if (h == 5){
				System.out.print("To create a listing, please start off by entering your sin (9 digits): ");
				int hsin = reader.nextInt();
				createListing(hsin);

			}
			else if (h == 6){
				System.out.print("To delete a listing, please start off by entering the latitude and longitude of the location: ");
				System.out.print("Latitude: ");
				double hlat = reader.nextDouble();
				System.out.print("Longitude: ");
				double hlong = reader.nextDouble();
				deleteListing(hlat, hlong);
			}
			else if (h == 7){
				search();
			}
			else if (h == 8){
				System.out.println("To cancel a booking, please start off by entering the sin and latitude, longitude of the location: ");
				System.out.println("SIN (9 digits): ");
				int hsin = reader.nextInt();
				System.out.println("Latitude: ");
				double hlat = reader.nextDouble();
				System.out.println("Longitude: ");
				double hlong = reader.nextDouble();
				cancelBooking(hsin, hlat, hlong,"HT");
			}
			else {
				userInterface(10);
			}
		}

	}
	
	public static void search() throws ClassNotFoundException{
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter your SIN to verify: ");
		int sin = reader.nextInt();
		System.out.println("Let's start booking!");
		System.out.println("Search Filters, choose the following to search by: ");
		System.out.println("(1)Location (2)Price (3)Postal Code (4)Address");
		int s = reader.nextInt();
		
		if (s == 1){
			System.out.print("Latitude: ");
			double blat = reader.nextDouble();
			System.out.print("Longitude: ");
			double blong = reader.nextDouble();
			searchbyLocation(blat, blong, sin);
		}
		else if (s == 2){
			System.out.print("Price (listings given with range of $50): $");
			double price = reader.nextDouble();
			searchbyPrice(price, sin);
		}
		else if (s == 3){
			System.out.print("Postal Code (e.g. M1R2V4): ");
			String pcode = reader.next();
			searchbyPostalCode(pcode, sin);
		}
		else if (s == 4){
			System.out.println("Address (e.g. 1265 Military Trail): ");
			String addr = reader.next();
			searchbyAddress(addr, sin);
		}
		else {
			System.out.println("Please try again.");
			search();
		}
	}
	
	public static void login(String username, String password, String who) throws ClassNotFoundException{
		
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");

		Connection conn = null;
		Statement stmt = null;
		Scanner reader = new Scanner(System.in);

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");

			try {
				stmt = conn.createStatement();
				if (who.equals("RR")){
					String sql = "SELECT * FROM RENTERS " +
						"WHERE username = '" + username + "' AND password = '" + password + "'" ;
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()){
						System.out.println("Hey, " + rs.getString("name") + "!");
						userInterface(20);
					}
					System.out.println("Please try again");
					userInterface(10);
				}
				else if (who.equals("HT")){
					String sql = "SELECT * FROM HOSTS " +
						"WHERE username = '" + username + "' AND password = '" + password + "'" ;
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()){
						System.out.println("Hey, " + rs.getString("name") + "!");
						userInterface(30);
					}
					System.out.println("Please try again");
					userInterface(10);
				}
				else {
					System.out.println("Please try again!");
					userInterface(10);
				}

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
	}

	public static Users createUser() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
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
		float credit_card_number;
		String rhbool;

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");

			try {
				System.out.print("Enter your occupation: ");
				occupation = br.readLine();
				user.setOccupation(occupation);
				System.out.print("Enter your address (e.g. 1265 Military Trail): ");
				address = br.readLine();
				user.setAddress(address);
				System.out.print("Enter your name: ");
				name = br.readLine();
				user.setName(name);
				System.out.print("Enter your sin (9 digits): ");
				sin = Integer.parseInt(br.readLine());
				user.setSin(sin);
				System.out.print("Enter your username: ");
				username = br.readLine();
				user.setUsername(username);
				System.out.print("Enter your password: ");
				password = br.readLine();
				user.setPassword(password);
				System.out.print("Enter your credit card number: ");
				credit_card_number = Float.parseFloat(br.readLine());
				user.setCredit_card_number(credit_card_number);
				System.out.println("Inserting records into the table...");
				stmt = conn.createStatement();
				String sql = "INSERT INTO USERS " +
						"VALUES ( '" + user.getOccupation() + "' , '" + user.getAddress() + "' , '" + user.getName() 
						+ "' ," + user.getSin() + ", '" + user.getUsername() + "' , '" + user.getPassword() +
						"' ," + user.getCredit_card_number() + ")";
				stmt.executeUpdate(sql);
				System.out.print("Are you a RR (renter) or a HT (host)? ");
				rhbool = br.readLine();
				if(rhbool.equals("RR")){
					sql = "INSERT INTO RENTERS " +
							"VALUES ( '" + user.getOccupation() + "' , '" + user.getAddress() + "' , '" + user.getName() 
							+ "' ," + user.getSin() + ", '" + user.getUsername() + "' , '" + user.getPassword() +
							"' ," + user.getCredit_card_number() + ")";
					stmt.executeUpdate(sql);
				}
				else if(rhbool.equals("HT")){
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
				System.out.println("Perfect! Now, let's get you started!");
				if (rhbool.equals("RR")){
					userInterface(20);
				}
				else if (rhbool.equals("HT")){
					userInterface(30);
				}

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
		return user;
	}

	public static void deleteUser(int sin, String username) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");

		Connection conn = null;
		Statement stmt = null;
		Scanner reader = new Scanner(System.in);

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
		System.out.println("Yay! You've deleted " + username);
		System.out.println("Choose the following options: (10)Home page (30)Go back");
		int hcexit = reader.nextInt();
		userInterface(hcexit);
	}

	public static Listings createListing(int sin) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner reader = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		Listings listing = new Listings();
		String type;
		double latitude;
		double longitude;
		String listing_address;
		String amenities;
		String postal_code;
		double rental_price;

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");

			try {
				System.out.print("Please enter the type of listing: ");
				type = br.readLine();
				listing.setType(type);
				System.out.print("Enter the listing address (e.g. 1265 Military Trail): ");
				listing_address = br.readLine();
				listing.setListing_address(listing_address);
				System.out.print("Enter the latitude: ");
				latitude = Double.parseDouble(br.readLine());
				listing.setLatitude(latitude);
				System.out.print("Enter the longitude: ");
				longitude = Double.parseDouble(br.readLine());
				listing.setLongitude(longitude);
				System.out.print("Enter the amenities: ");
				amenities = br.readLine();
				listing.setAmenities(amenities);
				System.out.print("Enter the postal code (e.g. M1R2V4) : ");
				postal_code = br.readLine();
				listing.setPostal_code(postal_code);
				System.out.print("Enter the rental price: $");
				rental_price = Double.parseDouble(br.readLine());
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
		System.out.println("Yay! You've created a listing!");
		System.out.println("Choose the following options: (10)Home page (30)Go back");
		int hcexit = reader.nextInt();
		userInterface(hcexit);
		return null;
	}

	public static void deleteListing(double latitude, double longitude) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");

		Connection conn = null;
		Statement stmt = null;
		Scanner reader = new Scanner(System.in);

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");

			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM LISTINGS " +
						"WHERE (latitude = " + latitude + ") AND (longitude = " + longitude + ")";
				stmt.executeUpdate(sql);
				sql = "DELETE FROM LISTNGS_RENTED " +
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
		System.out.println("Yay! You've deleted a listing.");
		System.out.println("Choose the following options: (10)Home page (30)Go back");
		int hdexit = reader.nextInt();
		userInterface(hdexit);
	}
	
	public static void bookListing(int sin, double latitude, double longitude, String who) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");

		Connection conn = null;
		Statement stmt = null;
		Scanner reader = new Scanner(System.in);

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");

			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO LISTNGS_RENTED SELECT * FROM USERS, LISTINGS " +
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
		System.out.println("Yay! You've booked a listing.");
		System.out.println("Choose the following options: (10)Home page (30)Go back");
		int hbexit = reader.nextInt();
		if (hbexit == 30){
			if (who.equals("RR")){
				userInterface(20);
			}
			else if (who.equals("HT")){
				userInterface(30);
			}
		}
		else {
			userInterface(10);
		}
	}
	public static void cancelBooking(int sin, double latitude, double longitude, String who) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");

		Connection conn = null;
		Statement stmt = null;
		Scanner reader = new Scanner(System.in);

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");

			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM LISTNGS_RENTED " +
						"WHERE (latitude = " + String.valueOf(latitude) + ") AND (longitude = " + String.valueOf(longitude) + ") AND (sin = " +
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
		System.out.println("Yay! You've cancelled a booking.");
		System.out.println("Choose the following options: (10)Home page (30)Go back");
		int hbexit = reader.nextInt();
		if (hbexit == 30){
			if (who.equals("RR")){
				userInterface(20);
			}
			else if (who.equals("HT")){
				userInterface(30);
			}
		}
		else {
			userInterface(10);
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * SEARCH QUERIES
	 * By LOCATION, PRICE, POSTAL CODE, and ADDRESS
	 */
	public static void searchbyLocation(double latitude, double longitude, int sin) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");
		
		Connection conn = null;
		Statement stmt = null;
		Scanner reader = new Scanner(System.in);
		int count = 1;
		
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
			    	
			    	System.out.println("");
			    	System.out.print("("+ count + ")   " + "type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			        System.out.println("");
			        count ++;
			    }
	            rs.close();
	            System.out.print("Choose what you want to book! :");
	            int booked = reader.nextInt();
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
	}
	
	public static void searchbyPrice(double rental_price, int sin) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");
		
		Connection conn = null;
		Statement stmt = null;
		int count = 1;
		
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			try {
				stmt = conn.createStatement();
			    String sql = "SELECT * FROM LISTINGS " +
		                   "WHERE rental_price BETWEEN " + (rental_price - 50) + " AND " + (rental_price + 50)
		                   + "ORDER BY rental_price";
			    ResultSet rs = stmt.executeQuery(sql);
			    while(rs.next()){
			    	String type = rs.getString("type");
			    	double latitude = rs.getDouble("latitude");
			    	double longitude = rs.getDouble("longitude");
			    	String listing_address = rs.getString("listing_address");
			    	String postal_code = rs.getString("postal_code");
			    	String amenities = rs.getString("amenities");
			    	rental_price = rs.getDouble("rental_price");
			    	
			    	System.out.println("");
			    	System.out.print("("+ count + ")   " + "type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			        System.out.println("");
			        count ++;
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
		   
	}
	
	public static void searchbyPostalCode(String postal_code, int sin) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
		System.out.println("Connecting to database...");
		
		Connection conn = null;
		Statement stmt = null;
		int count = 1;
		
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
			    	double rental_price = rs.getDouble("rental_price");
			    	
			    	System.out.println("");
			    	System.out.print("("+ count + ")   " + "type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			        System.out.println("");
			        count ++;
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
	
	public static void searchbyAddress(String address, int sin) throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";
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
			    	double rental_price = rs.getDouble("rental_price");
			    	
			    	System.out.println("");
			    	System.out.print("type: " + type);
			        System.out.print(", latitude: " + latitude);
			        System.out.print(", longitude: " + longitude);
			        System.out.print(", listing_address: " + listing_address);
			        System.out.print(", postal_code: " + postal_code);
			        System.out.print(", amenities: " + amenities);
			        System.out.println(", rental_price: " + rental_price);
			        System.out.println("");
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
	}
}


