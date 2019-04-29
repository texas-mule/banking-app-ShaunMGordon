package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Customer extends Bank{
	private String firstName;
	private String lastName;
	static String email;
	static String registeredPassword;
	static String status;
	static int userID;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static void printWelcome() {

		System.out.println("Please Select From The Following Options:");
		System.out.println("1: Returning User" + "\n2: New User");
	}

	public static void registerCustomer() {
// Initialize Registration Scanner
		Scanner infoScanner = new Scanner(System.in);
		System.out.println("Lets Get You Started");
		
// Gather First Name
		System.out.println("First Name: ");
		String firstName = infoScanner.nextLine();
		// Gather Last Name
		System.out.println("Last Name: ");
		String lastName = infoScanner.nextLine();

// Gather Birth Date
		System.out.println("Birth Date (mmddyy): ");
		String birthDay = infoScanner.nextLine();
		
// Gather SSN
		System.out.println("Social Security Number (xxxxxxxxx): ");
		String socialNumber = infoScanner.nextLine();
		
		
//		Scanner logInfoScanner = new Scanner(System.in);
		
// Gather Email
		int a = 0;
		String email = null;
		while (a == 0) {
		System.out.println("Email: ");
		email = infoScanner.nextLine();
		
		System.out.println("Confirm Email: ");
		String emailConfirm = infoScanner.nextLine();
		if(email.equalsIgnoreCase(emailConfirm)) {
			email = email;
			a = 1;
		}
		else
			System.out.println("Emails Do Not Match");
		}
		
// Gather Password
		int b = 0;
		String p4ssword;
		
		while (b == 0) {
			System.out.println("Password: ");
			p4ssword = infoScanner.nextLine();
			
			System.out.println("Confirm Password: ");
			String passwordConfirm = infoScanner.nextLine();
			if(p4ssword.equalsIgnoreCase(passwordConfirm)) {
				p4ssword = p4ssword;
				b = 1;
			}
			else
				System.out.println("Passwords Do Not Match");
		
		//}
		Bank.clearScreen();
		
		String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
		String username = "postgres";
		String password = "Safety48@@";
		String sql;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Scanner scanner1 = new Scanner(System.in);
			
			//while(true) {
				Statement statement = connection.createStatement();
				
		String customer;
		
		customer= "INSERT INTO Customers (fname,lname,birthdate,social,email,password,status)" + 
				"VALUES ('"+firstName+"','"+lastName+"','"+birthDay+"','"+socialNumber+"','"+email+"','"+p4ssword+"','Pending');";
		
		
		
		if (customer.equalsIgnoreCase("quit"))
			break;
		
		
		int isResultSet = statement.executeUpdate(customer);
		
		}

 catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		//statement.close();
		
	//}
		
		System.out.println("Thank You, Please Allow 24 Hours For Approval Processing");
}
	}//catch (SQLException ex) {

	public int loginCustomer() {
		String name;
		int loginLooper=0;
		int attemptCounter=0;
		
		clearScreen();
		Scanner loginScanner = new Scanner(System.in);
		
		System.out.println("Welcome Back");
		
		while (loginLooper==0) {
			
			if(attemptCounter>0) {
				clearScreen();
				System.out.println("That Email Was Not Found");
			}
			
		System.out.println("Email:");

			String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
			String username = "postgres";
			String password = "Safety48@@";
			String sql;
			String statusL;
			
			try (Connection connection = DriverManager.getConnection(url, username, password)){
				Scanner scanner = new Scanner(System.in);
				
				//while(true) {
					Statement statement = connection.createStatement();
					//System.out.print("sql> ");
					sql = "select * from customers where email =('" + scanner.nextLine()+"')";
					//statusL = "select status from customers where status =('" + scanner.nextLine()+"')";
					
					//select * from customers where email =('Shaun_Gordon@baylor.edu')
					if (sql.equalsIgnoreCase("quit"))
						break;
					
					boolean isResultSet = statement.execute(sql);
					ResultSet resultSet = statement.getResultSet();
					ResultSetMetaData rsmd = resultSet.getMetaData();
					
					if (isResultSet) {			
					
						while (resultSet.next()) {
							
							this.firstName= resultSet.getString(1);
							this.lastName = resultSet.getString(2);
							email = resultSet.getString(4);
							registeredPassword = resultSet.getString(6);
							status = resultSet.getString(7);
							userID = resultSet.getInt(8);
							
							if (status.equalsIgnoreCase("Pending"))
							{
								System.out.println("Your Account is Currently Pending Activation");
								System.out.println();
								System.out.println(" Please Try Again Later");
								restart = "yes";
								loginLooper++;
								break;
							}
							
							if (status.equalsIgnoreCase("Active"))
							{
								System.out.println("Your Account is Currently Active");
								System.out.println();
								System.out.println(" Please Enter Your Password");
								
										String enteredPassword = scanner.nextLine();
										//statusL = "select status from customers where status =('" + scanner.nextLine()+"')";

											if (enteredPassword.equalsIgnoreCase(registeredPassword)) {
												clearScreen();
												System.out.println("Welcome Back " + firstName + " " + lastName + " "+ status);
											}
											else
												System.out.println("Incorrect");								
												}					
								loginLooper++;
								
							}
							
							System.out.println();
							

						}

					} catch (SQLException e) {
							e.printStackTrace();
					} 
					
						}
				return userID;
				
}
}
			
	
