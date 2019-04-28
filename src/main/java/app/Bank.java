package app;

/********************************
 * 
 * IMPORTS
 * 
 */

import java.util.Scanner;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import app.Customer;
import app.Employee;

/*
 * 
 * PARENT CLASS - BANK APP
 * 
 */

public class Bank extends Object{

	static String EmployeeID = null;
	static String EmployeeFirstName;
	static String EmployeeLastName;
	static String EmployeeType;
	static String restart = "no";
	public static int bankID;
	static int userID;
	
	static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	static String username = "postgres";
	static String password = "Safety48@@";
	static String sql;
	
	/************************************/
	
	// Main
	
	/***********************************/
	
	
	public static void main(String[] args) {
		
		String flow = "Go";
		
		while (flow.equalsIgnoreCase("Go")) {
			
			// Restart Functionality
			
			restartProgram();
	
			// Print Welcome Message
			
			int x= 0;
			
			printWelcome();
		
			// Implement Method to Decipher Between Employee and Customer
			
			while (x == 0) {	
				
				x = bankDecider(x);
		
			}
			
			// Initiate Employee Interface
			if(x==1) {
				employeeInterface();
			}
			
			if(x==2) {
				Scanner sc = new Scanner(System.in);
				int option1 = sc.nextInt();
				
				if(option1 == 1) {
					userID = Customer.loginCustomer();
					
					// Options
					System.out.println("Please Select From The Following Options");
					System.out.println("1. Display all currently active accounts");
					System.out.println("2. Apply for a new account");
					System.out.println("3. Initiate a Transfer");
					
					Scanner customerDecider = new Scanner(System.in);
					int cDecider = customerDecider.nextInt();
					
					if(cDecider == 1) {
						displayCustomerCurrentAccounts();
						
					}
			
				}
				else
				{
					Customer.registerCustomer();
					
				}
			}
			
		}
		
		
	}

	private static void displayCustomerCurrentAccounts() {
		int currentAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "select * from accounts where userID = "+userID+"";
		
			boolean isResultSet = statement.execute(sql);
		
			if (isResultSet) {
				clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				System.out.print("Account"+"\t");
				System.out.print("Type"+"\t");
				System.out.print("\t"+"Balance"+"\t");
				System.out.print("\t"+"Status"+"\t");
				System.out.println();
				System.out.println();
			
				while (resultSet.next()) {
					int accountnumber= resultSet.getInt(1);
					String accounttype = resultSet.getString(2);
					BigDecimal accountbalance = resultSet.getBigDecimal(3);
					String accountStatus = resultSet.getString(4);
					System.out.print(accountnumber);
					System.out.print("\t"+accounttype);
					System.out.print("\t"+accountbalance+"\t");
					System.out.print("\t"+accountStatus);
					
					System.out.println();
					System.out.println("--------------------------------------------------------------------------------");
					currentAccounts = resultSet.getRow();
										}
					resultSet.close();
					
							} 
		
				statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
		System.out.println("\nYou currently have " + currentAccounts + " active accounts");
	}

	private static void employeeInterface() {		
			// Base Employee Permissions
			
			if(EmployeeType.equalsIgnoreCase("Employee")) {
				employeeActions();
			}
			
			// Administrative Permissions
			
			else if(EmployeeType.equalsIgnoreCase("Admin")){
				adminActions();
				
			}
	}

	private static void employeeActions() {
		String empcont = "yes";
		Scanner empScan = new Scanner(System.in);
		
		while( empcont.equalsIgnoreCase("yes")) {
			System.out.println();
			System.out.println();
			System.out.println("Allowed Actions:");
			System.out.println();
			System.out.println("1. View Active Accounts");
			System.out.println("2. View Pending Accounts");
			System.out.println("3. View Denied Accounts");
	
			Scanner scanner = new Scanner(System.in);
			int AllowedAction = scanner.nextInt();
			
			
			if (AllowedAction == 1) {
				int input=0;
				displayActiveAccounts();
				while(input==0) {
				System.out.println("Would you like to continue?");
				empcont = empScan.nextLine();
				if(empcont.equalsIgnoreCase("yes") || empcont.equalsIgnoreCase("no")) {
					empcont = empcont;
					input = 1;
				}
				else
					System.out.println("Invalid Input");
				}
				
			}
			
			else if (AllowedAction == 2) {
				
				managePendingAccounts();
				System.out.println("Would you like to continue?");
				empcont = empScan.nextLine();
				
			}
			
			else if (AllowedAction == 3) {
				
				displayDeniedAccounts();
				System.out.println("Would you like to continue?");
				empcont = empScan.nextLine();
				
			}
			else {
				System.out.println("Invalid Input");
				System.out.println("Would you like to continue?");
				empcont = empScan.nextLine();
			}
		}
	}	

	private static void adminActions() {
		System.out.println();
		System.out.println();
		System.out.println("Allowed Actions:");
		System.out.println();
		System.out.println("1. View Active Accounts");
		System.out.println("2. View Pending Accounts");
		System.out.println("3. View Denied Accounts");
		System.out.println("4. View Cancelled Acounts");
		
		Scanner scanner = new Scanner(System.in);
		int AllowedAction = scanner.nextInt();
		
		
		if (AllowedAction == 1) {
			
			displayActiveAccounts();
			
		}
		
		if (AllowedAction == 2) {
			
			managePendingAccounts();
			
		}
		
		if (AllowedAction == 3) {
			
			displayDeniedAccounts();
			
		}
		if (AllowedAction == 4) {
			
			displayCancelledAccounts();
			
		}
	}

	private static void managePendingAccounts() {
		String p2Decider = null;
		
		// Display Accounts with Pending Status
		
		displayPendingAccounts();
		
		System.out.println("\n Would You Like to Update the Status of These Accounts?");

		Scanner PendingDecider = new Scanner(System.in);
		String pDecider = PendingDecider.nextLine();


		if(pDecider.equalsIgnoreCase("yes")){
			
			System.out.println("Whose Account Would you like to update");
			p2Decider = PendingDecider.nextLine();
			
			selectPendingAccount(p2Decider);
			
											

		System.out.println("Would you like to approve or deny this application");
		Scanner empDScanner = new Scanner(System.in);
		String empDecision = empDScanner.nextLine();
		
		// Approve Functionality
		
		if(empDecision.equalsIgnoreCase("approve")) {
			
			approvePending(p2Decider);
			
			// Set Up Customer Bank Account
			try (Connection connection = DriverManager.getConnection(url, username, password)){
				Scanner scanner1 = new Scanner(System.in);
				
				//while(true) {
					Statement statement = connection.createStatement();
					
			String customer;
			
			customer= "INSERT INTO accounts("
					+"type, balance, status, userid)"
					+ "VALUES ('Checking', 0.00,'Active',"+bankID+")";
			
			
			
			int isResultSet = statement.executeUpdate(customer);
			
			}

	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
													}
		// Deny Functionality
		
		if(empDecision.equalsIgnoreCase("deny")) {		
			denyPending(p2Decider);
												}
		
											}
		else
			employeeInterface();
	}

	private static void displayActiveAccounts() {
		int activeAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "select * from customers where status = " + "'Active'";
		
			boolean isResultSet = statement.execute(sql);
		
			if (isResultSet) {
				clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String customerEmail = resultSet.getString(5);
					String customerStatus = resultSet.getString(7);
					int flength= firstName.length();
					if (flength < 10) {
						for(int i =0;i<=(10-flength);i++)
							firstName= firstName + " ";
					}
					
					System.out.print(firstName);
					System.out.print(lastName);
					System.out.print("\t"+"\t"+customerEmail);
					System.out.print("\t"+"\t"+customerStatus);
					
					System.out.println();
					System.out.println("--------------------------------------------------------------------------------");
					activeAccounts = resultSet.getRow();
										}
					resultSet.close();
					
							} 
		
				statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
		System.out.println("\nThere are currently " + activeAccounts + " activated accounts");
	}

	private static void denyPending(String p2Decider) {
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "UPDATE customers\n" + "SET status = 'Denied'\n" + "WHERE\n" + " fname = '"+p2Decider+"';";
		
			statement.execute(sql);
			statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
	}

	private static void approvePending(String p2Decider) {
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "UPDATE customers\n" + "SET status = 'Active'\n" + "WHERE\n" + " fname = '"+p2Decider+"';";
		
			statement.execute(sql);
			statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
	}

	private static int selectPendingAccount(String p2Decider) {
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "select * from customers where fname = " + "'"+p2Decider+"'";
		
			boolean isResultSet = statement.execute(sql);
		
			if (isResultSet) {
				clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String customerEmail = resultSet.getString(5);
					String customerStatus = resultSet.getString(7);
					bankID= resultSet.getInt(8);
					
					int flength= firstName.length();
					if (flength < 10) {
						for(int i =0;i<=(10-flength);i++)
							firstName= firstName + " ";
					}
					
					System.out.print(firstName);
					System.out.print(lastName);
					System.out.print("\t"+"\t"+customerEmail);
					System.out.print("\t"+"\t"+customerStatus);
					
					System.out.println();
					System.out.println("--------------------------------------------------------------------------------");
										}
					resultSet.close();
					
							} 
		
				statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
		return bankID;
	}

	private static void displayPendingAccounts() {
		int pendingAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "select * from customers where status = " + "'Pending'";
		
			boolean isResultSet = statement.execute(sql);
		
			if (isResultSet) {
				clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String customerEmail = resultSet.getString(5);
					String customerStatus = resultSet.getString(7);
					int flength= firstName.length();
					if (flength < 10) {
						for(int i =0;i<=(10-flength);i++)
							firstName= firstName + " ";
					}
					
					System.out.print(firstName);
					System.out.print(lastName);
					System.out.print("\t"+"\t"+customerEmail);
					System.out.print("\t"+"\t"+customerStatus);
					
					System.out.println();
					System.out.println("--------------------------------------------------------------------------------");
					pendingAccounts = resultSet.getRow();
										}
					resultSet.close();
					
							} 
		
				statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
		System.out.println("\nThere are currently " + pendingAccounts + " pending accounts");
	}

	private static void restartProgram() {
		if(restart.equalsIgnoreCase("yes")) {
		try {
			System.out.println("\nRestarting Program in 3");
		    Thread.sleep(1000); 
		    System.out.println("\n                      2");
		    Thread.sleep(1000); 
		    System.out.println("\n                      1");
		    Thread.sleep(1000); 
		    clearScreen();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		}
	} 

	public static void printWelcome() {
		System.out.println();
		System.out.println("--------------------------------\n");
		System.out.println("| Hello, Welcome To SG Banking |\n");
		System.out.println("--------------------------------\n");
		System.out.println("Are You A SGBanking Employee?");
	}

	public static int bankDecider(int x) {
		Scanner bankDecider = new Scanner(System.in);
		String decider = bankDecider.nextLine();
		
		String employeeDecider = "yes";
		String customerDecider = "no";
		
			if(decider.equalsIgnoreCase(employeeDecider)) {
// If yes, connect to Employee Version
				employeeWelcome();
				Employee employee = new Employee();
				x = 1;
			}
			else if(decider.equalsIgnoreCase(customerDecider)){
// If no, connect to Customer Version
				customerWelcome();
				Customer customer = new Customer();
				x = 2;
			}
			else {
// Print Error 
				clearScreen();
				System.out.println("Invalid Response\n Please Try Again\n\n ");
				System.out.println("Are You A SGBanking Employee?");
			}
		return x;
	}

	private static void employeeWelcome() {
		clearScreen();
		Employee.employeeWelcome();
		
	}

	public static void customerWelcome() {
		clearScreen();
		Customer.printWelcome();
		
	}
	
	public static void clearScreen() {  
	    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  
		}
	
	private static void displayDeniedAccounts() {
		int deniedAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "select * from customers where status = " + "'Denied'";
		
			boolean isResultSet = statement.execute(sql);
		
			if (isResultSet) {
				clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String customerEmail = resultSet.getString(5);
					String customerStatus = resultSet.getString(7);
					int flength= firstName.length();
					if (flength < 10) {
						for(int i =0;i<=(10-flength);i++)
							firstName= firstName + " ";
					}
					
					System.out.print(firstName);
					System.out.print(lastName);
					System.out.print("\t"+"\t"+customerEmail);
					System.out.print("\t"+"\t"+customerStatus);
					
					System.out.println();
					System.out.println("--------------------------------------------------------------------------------");
					deniedAccounts = resultSet.getRow();
										}
					resultSet.close();
					
							} 
		
				statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
		System.out.println("\nThere are currently " + deniedAccounts + " accounts that have been denied");
	}

	private static void displayCancelledAccounts() {
		int cancelledAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			sql = "select * from customers where status = " + "'Cancelled'";
		
			boolean isResultSet = statement.execute(sql);
		
			if (isResultSet) {
				clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String customerEmail = resultSet.getString(5);
					String customerStatus = resultSet.getString(7);
					int flength= firstName.length();
					if (flength < 10) {
						for(int i =0;i<=(10-flength);i++)
							firstName= firstName + " ";
					}
					
					System.out.print(firstName);
					System.out.print(lastName);
					System.out.print("\t"+"\t"+customerEmail);
					System.out.print("\t"+"\t"+customerStatus);
					
					System.out.println();
					System.out.println("--------------------------------------------------------------------------------");
					cancelledAccounts = resultSet.getRow();
										}
					resultSet.close();
					
							} 
		
				statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
		System.out.println("\nThere are currently " + cancelledAccounts + " accounts that have been cancelled");
	}
}

