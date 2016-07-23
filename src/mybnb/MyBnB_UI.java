package mybnb;
import javax.swing.*;

import com.mysql.jdbc.StatementImpl;

import javax.swing.JButton;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyBnB_UI extends JFrame
implements ActionListener{
	
	JButton loginB = new JButton("Login");
	JButton registerB = new JButton("Register");
	JButton nextB = new JButton("Next");
	JButton clearB = new JButton("Clear");
	private static final long serialVersionUID = 1L;
	private Label 	username;
	private Label 	userpassword;
	private TextField usernameField;
	private TextField passwordField;
	private Label	instruction ;
	private Label	company;
	private TextField companyfield;
	private Label	Name;
	private TextField namefield;
	private Label	Address;
	private TextField addressfield;
	private Label	City;
	private TextField cityfield;
	private Label	State;
	private TextField statefield;
	private Label   Zipcode;
	private TextField zipcodefield;
	private Label	Phone;
	private TextField phonefield;
	private Label	Fax;
	private TextField faxfield;
	private Label	Email;
	private TextField emailfield;
	private MenuBar	menubar;
	private Menu	fileMenu, editMenu, helpMenu;
	private MenuItem openMI, uploadMI, exitMI;
	private MenuItem deleteMI;
	private MenuItem helpMI;
	private boolean myButtonPressed = false;
	Connection conn;
	
	
	MyBnB_UI() throws SQLException
	{
		super();
		setTitle("Welcome to MyBnB");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200,100);
		setBackground(java.awt.Color.DARK_GRAY);
		String userName = "root"; 
		String password = "password"; 
		String url = "jdbc:mysql://localhost/mybnb";
		conn = DriverManager.getConnection(url, userName, password);
		
		username 	= new Label("User name:");
		username.setForeground(Color.white);
		usernameField = new TextField(10);
		userpassword	= new Label("Password:");
		userpassword.setForeground(Color.white);
		passwordField = new TextField(10);
		
		instruction = new Label("Welcome to MyBnB, let's get you a place to stay!");
		instruction.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		instruction.setForeground(Color.ORANGE);

		menubar		= new MenuBar();
		helpMenu	= new Menu("Help");
		menubar.add(helpMenu);
		nextB.addActionListener(this);
		
		helpMI		= new MenuItem("Help");
		helpMenu.add(helpMI);
		
		
		setLayout(new GridLayout(16,16));
		getContentPane().setBackground(Color.DARK_GRAY.brighter());
		setMenuBar(menubar);
		setVisible(true);
		add(new Label(" "));
		add(new Label(" "));
		add(instruction);
		add(new Label(" "));
		add(new Label(" "));
		add(new Label(""));
		add(username);
		add(usernameField);
		add(userpassword);
		add(passwordField);
		add(new Label(""));
		add(nextB);
		add(new Label(""));
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) throws SQLException

	{
		MyBnB_UI test = new MyBnB_UI();

	}

	public void actionPerformed(ActionEvent e)
	{  
		JFrame frame = new JFrame("Search Window" );
    	frame.setSize( 500,120 );
    	frame.setLocationRelativeTo( null );
    	frame.setVisible( true );   
	}



		/*
		public void myButtonactionPerformed(ActionEvent e)
		  {
			String Company=companyfield.getText();
			String Name=namefield.getText();
			String Address=addressfield.getText();
			String City=cityfield.getText();
			String State=statefield.getText();
			String Zipcode=zipcodefield.getText();
			String Phone=phonefield.getText();
			String Fax=faxfield.getText();
			String Email=emailfield.getText();
			
			
			String insertStr="";
			
			
			
			try{
				
				insertStr="insert into customers (company, owner, address, city, state, zipcode, phone, fax, email) values("
						+quotate1(companyfield)+","
						+quotate2(namefield)+","
						+quotate3(addressfield)+","
						+quotate4(cityfield)+","
						+quotate5(statefield)+","
						+quotate6(zipcodefield)+","
						+quotate7(phonefield)+","
						+quotate8(faxfield)+","
						+quotate9(emailfield)
						+";)";
				
				
			StatementImpl stmt;
			int done=executeUpdate(insertStr);
								  
			}
			catch(Exception e1){
				e1.printStackTrace();
			}}
		*/
			private int executeUpdate(String insertStr) {
			// TODO Auto-generated method stub
				return 1;
			}


			public String quotate1(TextField companyfield)
			{
				return ","+companyfield+",";
			}
			public String quotate2(TextField namefield){

				return "'"+namefield+"'";
			}
			public String quotate3(TextField addressfield)
			{
				return ","+addressfield+",";
			}
			public String quotate4(TextField cityfield)
			{
				return ","+cityfield+",";
			}
			public String quotate5(TextField statefield)
			{
				return ","+statefield+",";
			}
			public String quotate6(TextField zipcodefield)
			{
				return ","+zipcodefield+",";
			}
			public String quotate7(TextField phonefield)
			{
				return ","+phonefield+",";
			}
			public String quotate8(TextField faxfield)
			{
				return ","+faxfield+",";
			}
			public String quotate9(TextField emailfield)
			{
				return ","+emailfield+",";
			}

			{

			}
			{}

		}