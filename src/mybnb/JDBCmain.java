package myBNB;

import java.sql.*;
import myBNB.Users;
import java.io.*;
public class JDBCmain {

	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost/mybnb";
	
	public static void main(String[] args) throws ClassNotFoundException{
		deleteUser();
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
	            System.out.println("Inserting records into the table...");
	            
	            stmt = conn.createStatement();
	            String sql = "INSERT INTO USERS " +
	                         "VALUES ( '" + user.getOccupation() + "' , '" + user.getAddress() + "' , '" + user.getName() 
	                         + "' ," + user.getSin() + ")";
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
	
	public static void deleteUser() throws ClassNotFoundException{
		Class.forName(dbClassName);
		final String USER = "root";
		final String PASS = "vethush";
		System.out.println("Connecting to database...");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = null;
		Statement stmt = null;
		int sin;
		
		try {
			conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			try {
				System.out.println("Enter the sin of the user you would like to delete: ");
				sin = Integer.parseInt(br.readLine());
				
				stmt = conn.createStatement();
			    String sql = "DELETE FROM USERS " +
			                   "WHERE sin = " + sin;
			    stmt.executeUpdate(sql);
	            
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
	}
}
