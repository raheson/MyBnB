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
		JDBCconnection newConnection = new JDBCconnection();
		String[] arguments = new String[]{"123"};
		newConnection.main(arguments);
		

		//User Interface
		System.out.println("");
		System.out.println("			*****************************************************			");
		System.out.println("			*---------------------------------------------------*			");
		System.out.println("			*--------------- WELCOME TO MyBnB ------------------*			");
		System.out.println("			*---------------------------------------------------*			");
		System.out.println("			*****************************************************			");
		System.out.println("");
		System.out.println("Rent unique accommodations from local hosts all over the world!");
		System.out.println("Feel at home anywhere you go in the world with MyBnB.");
		userInterface(10);
	}
	public static void userInterface(int i) throws ClassNotFoundException{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		if( i == 10){
			System.out.println("");
			System.out.println("~~~~~~~~~~~~~");
			System.out.println(" loading ... ");
			System.out.println("~~~~~~~~~~~~~");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Choose the following options to continue => (1)Login (2)Create/Update User (3)Delete User (4)Reports (5)Exit : ");
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
				System.out.println("Welcome to our Reports page!");
				System.out.println("We see that you want to check out reports about our database");
				System.out.println("Select the following to inquire about: ");
				System.out.println("(a) Total number of bookings in a specific date range by city");
				System.out.println("(b) Total number of bookings by zipcode within a city");
				System.out.println("(c) Total number of listings per country");
				System.out.println("(d) Total number of listings per country and city");
				System.out.println("(e) Total number of listings per country, city, and postal code");
				System.out.println("(f) Rank the hosts by total number of listings they have overall per country");
				System.out.println("(g) Rank the hosts by total number of listings they have overall per city");
				System.out.println("(h) Provide the hosts that have a number of listings that is more than "
						+ "10% of the number of listings in that city and country");
				System.out.println("(i) Rank the renters by the number of bookings in a specific time periodic");
				System.out.println("(j) Rank the renters by number of bookings in a specific time period per city");
				System.out.println("(k) The hosts with the largest number of cancelations within a year");
				System.out.println("(l) The renters with the largest number of cancelations within a year");
				System.out.print("==> ");
				String reportOp = reader.next();
				reportInquiry(reportOp);
			}
			else if (n == 5){
				System.out.println("Have a great day!");
				System.exit(0);
			}
		}
		// Renter
		else if (i == 20){
			System.out.println("");
			System.out.println("~~~~~~~~~~~~~");
			System.out.println(" loading ... ");
			System.out.println("~~~~~~~~~~~~~");
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
				search("RR");
			}
			else if (r == 6){
				System.out.println("To cancel a booking, please start off by entering the sin and latitude, longitude of the location: ");
				System.out.println("SIN (9 digits): ");
				int hsin = reader.nextInt();
				System.out.println("Latitude: ");
				float hlat = reader.nextFloat();
				System.out.println("Longitude: ");
				float hlong = reader.nextFloat();
				cancelBooking(hsin, hlat, hlong, "RR");
			}
			else {
				userInterface(10);
			}
		}
		// Hosts
		else if (i == 30){
			System.out.println("");
			System.out.println("~~~~~~~~~~~~~");
			System.out.println(" loading ... ");
			System.out.println("~~~~~~~~~~~~~");
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
				System.out.println("To verify, please enter the following: ");
				System.out.print("SIN: ");
				int listsin = reader.nextInt();
				System.out.print("Latitude: ");
				Float listlat = reader.nextFloat();
				System.out.print("Longitude: ");
				Float listlong = reader.nextFloat();
				listingListed(listlat, listlong, listsin);

			}
			else if (h == 6){
				System.out.print("To delete a listing, please start off by entering the latitude and longitude of the location: ");
				System.out.print("Latitude: ");
				float hlat = reader.nextFloat();
				System.out.print("Longitude: ");
				float hlong = reader.nextFloat();
				deleteListing(hlat, hlong);
			}
			else if (h == 7){
				search("HT");
			}
			else if (h == 8){
				System.out.println("To cancel a booking, please start off by entering the sin and latitude, longitude of the location: ");
				System.out.println("SIN (9 digits): ");
				int hsin = reader.nextInt();
				System.out.println("Latitude: ");
				float hlat = reader.nextFloat();
				System.out.println("Longitude: ");
				float hlong = reader.nextFloat();
				cancelBooking(hsin, hlat, hlong,"HT");
			}
			else {
				userInterface(10);
			}
		}

	}

	public static void reportInquiry(String reportOp) throws ClassNotFoundException{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		if (reportOp.equals("a")){
			System.out.println("NOT IMPLEMENTED");
		}
		else if (reportOp.equals("b")){
			reportB();
			userInterface(10);
		}
		else if (reportOp.equals("c")){
			reportC();
			userInterface(10);
		}
		else if (reportOp.equals("d")){
			reportD();
			userInterface(10);
		}
		else if (reportOp.equals("e")){
			reportE();
			userInterface(10);

		}
		else if (reportOp.equals("f")){
			reportF();
			userInterface(10);
		}
		else if (reportOp.equals("g")){
			System.out.println("NOT IMPLEMENTED");
			userInterface(10);
		}	
		else if (reportOp.equals("h")){
			System.out.println("NOT IMPLEMENTED");
			userInterface(10);
		}
		else if (reportOp.equals("i")){
			System.out.println("NOT IMPLEMENTED");
			userInterface(10);
		}
		else if (reportOp.equals("j")){
			System.out.println("NOT IMPLEMENTED");
			userInterface(10);
		}
		else if (reportOp.equals("k")){
			System.out.println("NOT IMPLEMENTED");
			userInterface(10);
		}
		else if (reportOp.equals("l")){
			System.out.println("NOT IMPLEMENTED");
			userInterface(10);
		}
	}

	public static void search(String who) throws ClassNotFoundException{
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter your username again to verify: ");
		String username = reader.next();
		System.out.println("Let's start booking!");
		System.out.println("Search Filters, choose the following to search by: ");
		System.out.println("(1)Location (2)Price (3)Postal Code (4)Address (5)Exit");
		int s = reader.nextInt();

		if (s == 1){
			System.out.print("Latitude: ");
			float blat = reader.nextFloat();
			System.out.print("Longitude: ");
			float blong = reader.nextFloat();
			searchbyLocation(blat, blong, username, who);
		}
		else if (s == 2){
			System.out.print("Price (listings given with range of $50): $");
			double price = reader.nextDouble();
			searchbyPrice(price, username, who);
		}
		else if (s == 3){
			System.out.print("Postal Code (e.g. M1R2V4): ");
			String pcode = reader.next();
			searchbyPostalCode(pcode, username, who);
		}
		else if (s == 4){
			System.out.println("Address (e.g. 1265 Military Trail): ");
			String addr = reader.next();
			searchbyAddress(addr, username, who);
		}
		else if(s == 5){
			userInterface(10);
		}
		else {
			System.out.println("Please try again.");
			search(who);
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
		float latitude;
		float longitude;
		String listing_address;
		String listing_city;
		String listing_country;
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
				System.out.print("Enter the listing city (e.g. Toronto): ");
				listing_city = br.readLine();
				listing.setListing_city(listing_city);
				System.out.print("Enter the listing country (e.g. Canada): ");
				listing_country = br.readLine();
				listing.setListing_country(listing_country);
				System.out.print("Enter the latitude: ");
				latitude = Float.parseFloat(br.readLine());
				listing.setLatitude(latitude);
				System.out.print("Enter the longitude: ");
				longitude = Float.parseFloat(br.readLine());
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
						listing.getListing_city() + "' , '" + listing.getListing_country() + "' , '" +
						listing.getPostal_code() + "' , '" + listing.getAmenities() + "' ," + listing.getRental_price() + ")";
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

	public static void listingListed(float latitude, float longitude, int sin) throws ClassNotFoundException{
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
				String sql = "INSERT INTO LISTNGS_LISTED SELECT * FROM LISTINGS, HOSTS " +
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
	}

	public static void deleteListing(float latitude, float longitude) throws ClassNotFoundException{
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

	public static void bookListing(String username, float latitude, float longitude, String who) throws ClassNotFoundException{
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
				String sql = "INSERT INTO LISTNGS_RENTED SELECT * FROM LISTINGS, USERS " +
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
	public static void cancelBooking(int sin, float latitude, float longitude, String who) throws ClassNotFoundException{
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
	 * @param username 
	 * @param who 
	 */
	public static void searchbyLocation(float latitude, float longitude, String username, String who) throws ClassNotFoundException{
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
					latitude = rs.getFloat("latitude");
					longitude = rs.getFloat("longitude");
					String listing_address = rs.getString("listing_address");
					String listing_city = rs.getString("listing_city");
					String listing_country = rs.getString("listing_country");
					String postal_code = rs.getString("postal_code");
					String amenities = rs.getString("amenities");
					int rental_price = rs.getInt("rental_price");

					System.out.println("");
					System.out.print("("+ count + ")   " + "type: " + type);
					System.out.print(", latitude: " + latitude);
					System.out.print(", longitude: " + longitude);
					System.out.print(", listing_address: " + listing_address);
					System.out.print(", listing_city: " + listing_city);
					System.out.print(", listing_country: " + listing_country);
					System.out.print(", postal_code: " + postal_code);
					System.out.print(", amenities: " + amenities);
					System.out.println(", rental_price: " + rental_price);
					System.out.println("");
					count ++;
				}
				rs.close();
				System.out.println("");
				System.out.println("Alright! Take a close look  and indicate the location(latitude, longitude) of the place you want to rent!");
				System.out.print("Latitude: ");
				float lat = reader.nextFloat();
				System.out.print("Longitude: ");
				float longt = reader.nextFloat();
				System.out.println("Do you still want to rent this one? (Y/N)");
				String exitstat = reader.next();
				if (exitstat.equals("Y")){
					bookListing(username, lat, longt, who);
				}
				else{
					search(who);
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

	public static void searchbyPrice(double rental_price, String username, String who) throws ClassNotFoundException{
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
						"WHERE rental_price BETWEEN " + (rental_price - 50) + " AND " + (rental_price + 50)
						+ "ORDER BY rental_price";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					String type = rs.getString("type");
					double latitude = rs.getDouble("latitude");
					double longitude = rs.getDouble("longitude");
					String listing_address = rs.getString("listing_address");
					String listing_city = rs.getString("listing_city");
					String listing_country = rs.getString("listing_country");
					String postal_code = rs.getString("postal_code");
					String amenities = rs.getString("amenities");
					rental_price = rs.getDouble("rental_price");

					System.out.println("");
					System.out.print("("+ count + ")   " + "type: " + type);
					System.out.print(", latitude: " + latitude);
					System.out.print(", longitude: " + longitude);
					System.out.print(", listing_address: " + listing_address);
					System.out.print(", listing_city: " + listing_city);
					System.out.print(", listing_country: " + listing_country);
					System.out.print(", postal_code: " + postal_code);
					System.out.print(", amenities: " + amenities);
					System.out.println(", rental_price: " + rental_price);
					System.out.println("");
					count ++;
				}
				rs.close();
				System.out.println("");
				System.out.println("Alright! Take a close look  and indicate the location(latitude, longitude) of the place you want to rent!");
				System.out.print("Latitude: ");
				float lat = reader.nextFloat();
				System.out.print("Longitude: ");
				float longt = reader.nextFloat();
				System.out.println("Do you still want to rent this one? (Y/N)");
				String exitstat = reader.next();
				if (exitstat.equals("Y")){
					bookListing(username, lat, longt, who);
				}
				else{
					search(who);
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

	public static void searchbyPostalCode(String postal_code, String username, String who) throws ClassNotFoundException{
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
						"WHERE (postal_code = '" + postal_code + "')";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					String type = rs.getString("type");
					double latitude = rs.getDouble("latitude");
					double longitude = rs.getDouble("longitude");
					String listing_address = rs.getString("listing_address");
					String listing_city = rs.getString("listing_city");
					String listing_country = rs.getString("listing_country");
					postal_code = rs.getString("postal_code");
					String amenities = rs.getString("amenities");
					double rental_price = rs.getDouble("rental_price");

					System.out.println("");
					System.out.print("("+ count + ")   " + "type: " + type);
					System.out.print(", latitude: " + latitude);
					System.out.print(", longitude: " + longitude);
					System.out.print(", listing_address: " + listing_address);
					System.out.print(", listing_city: " + listing_city);
					System.out.print(", listing_country: " + listing_country);
					System.out.print(", postal_code: " + postal_code);
					System.out.print(", amenities: " + amenities);
					System.out.println(", rental_price: " + rental_price);
					System.out.println("");
					count ++;
				}
				rs.close();
				System.out.println("");
				System.out.println("Alright! Take a close look  and indicate the location(latitude, longitude) of the place you want to rent!");
				System.out.print("Latitude: ");
				float lat = reader.nextFloat();
				System.out.print("Longitude: ");
				float longt = reader.nextFloat();
				System.out.println("Do you still want to rent this one? (Y/N)");
				String exitstat = reader.next();
				if (exitstat.equals("Y")){
					bookListing(username, lat, longt, who);
				}
				else{
					search(who);
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

	public static void searchbyAddress(String address, String username, String who) throws ClassNotFoundException{
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
						"WHERE (listing_address = '" + address + "' LIKE '% %')";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					String type = rs.getString("type");
					double latitude = rs.getDouble("latitude");
					double longitude = rs.getDouble("longitude");
					String listing_address = rs.getString("listing_address");
					String listing_city = rs.getString("listing_city");
					String listing_country = rs.getString("listing_country");
					String postal_code = rs.getString("postal_code");
					String amenities = rs.getString("amenities");
					double rental_price = rs.getDouble("rental_price");

					System.out.println("");
					System.out.print("("+ count + ")   " + "type: " + type);
					System.out.print(", latitude: " + latitude);
					System.out.print(", longitude: " + longitude);
					System.out.print(", listing_address: " + listing_address);
					System.out.print(", listing_city: " + listing_city);
					System.out.print(", listing_country: " + listing_country);
					System.out.print(", postal_code: " + postal_code);
					System.out.print(", amenities: " + amenities);
					System.out.println(", rental_price: " + rental_price);
					System.out.println("");
					count ++;
				}
				if (rs.next()){
					System.out.println("");
					System.out.println("Alright! Take a close look  and indicate the location(latitude, longitude) of the place you want to rent!");
					System.out.print("Latitude: ");
					float lat = reader.nextFloat();
					System.out.print("Longitude: ");
					float longt = reader.nextFloat();
					System.out.println("Do you still want to rent this one? (Y/N)");
					String exitstat = reader.next();
					if (exitstat.equals("Y")){
						bookListing(username, lat, longt, who);
					}
					else{
						search(who);
					}
				}
				else{
					System.out.println("Sorry, we found no matches");
					userInterface(10);
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

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * REPORT QUERIES
	 */
	public static void reportB() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);

			try {
				stmt = conn.createStatement();

				String sql = "SELECT postal_code, COUNT(*) as rowcount FROM LISTNGS_RENTED " +
						"GROUP BY postal_code";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					int count = rs.getInt("rowcount");
					System.out.println("There are " + count +" bookings within " + rs.getString("postal_code"));
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
	public static void reportC() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);

			try {
				stmt = conn.createStatement();

				String sql = "SELECT listing_country, COUNT(*) as rowcount FROM LISTINGS " +
						"GROUP BY listing_country";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					int count = rs.getInt("rowcount");
					System.out.println("There are " + count +" listings in " + rs.getString("listing_country"));
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
	public static void reportD() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);

			try {
				stmt = conn.createStatement();

				String sql = "SELECT listing_city, listing_country, COUNT(*) as rowcount FROM LISTINGS " +
						"GROUP BY listing_city, listing_country";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					int count = rs.getInt("rowcount");
					System.out.println("There are " + count +" listings in " + rs.getString("listing_city") + " and " + rs.getString("listing_country"));
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
	public static void reportE() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);

			try {
				stmt = conn.createStatement();

				String sql = "SELECT listing_city, listing_country, postal_code, COUNT(*) as rowcount FROM LISTINGS " +
						"GROUP BY listing_city,listing_country,postal_code";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					int count = rs.getInt("rowcount");
					System.out.println("There are " + count +" listings in " + 
							rs.getString("listing_city") + ", " + rs.getString("listing_country") + ",and " + rs.getString("postalcode"));
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
	public static void reportF() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "password";

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);

			try {
				stmt = conn.createStatement();

				String sql = "SELECT listing_country, name, COUNT(*) as rowcount FROM LISTINGS_LISTED " +
						"GROUP BY listing_country ORDER BY rowcount";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					int count = rs.getInt("rowcount");
					System.out.println(rs.getString("name") +" has " + count +" overall in " + 
							rs.getString("listing_country"));
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


