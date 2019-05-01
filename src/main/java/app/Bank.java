package app;
/*
 * 
 * IMPORTS
 * 
 */
import java.util.Scanner;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import app.Customer;
import app.Employee;
import java.util.ArrayList;
import java.util.InputMismatchException;
/*
 * 
 * PARENT CLASS - BANK APP
 * 
 */

public class Bank {

	static String EmployeeID = null;
	static String EmployeeFirstName;
	static String EmployeeLastName;
	static String EmployeeType;
	static String restart = "no";
	
	public static int bankID;
	static int userID;
	static String status;
	
/*
 * Database Login Information
 */
	static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	static String username = "postgres";
	static String password = "Safety48@@";
	

	
	// Main

/************************************************************************************************************************************************************************************/

	
	public static void main(String[] args) {
		
		// Run Program
			String runProgramVariable = "Go";
			while (runProgramVariable.equalsIgnoreCase("Go")) {
			
			// Print Welcome Message
				main_printWelcome();
		
			// Implement Method to Decipher Between Employee and Customer
				int x = 0;
				try {
					x=main_bankDecider();
				} catch (Exception e1) {
					main_clearScreen();
					System.out.println("You have committed an action which was deemed unsafe\n\n Goodbye");
				}
				
				int appUserDifferentiator=x;
			
			// Initiate Employee Interface
				if(appUserDifferentiator==1 && EmployeeType!=null) {
					try {
						main_employeeInterface();
					} catch (Exception e) {
						main_clearScreen();
						System.out.println("You have committed an action which was deemed unsafe\n\n Goodbye");
					}
				}
			
			//Initiate Customer Interface
				if(appUserDifferentiator==2) {
					try {
						main_customerInterface();
					} catch (Exception e) {
						main_clearScreen();
						System.out.println("You have committed an action which was deemed unsafe\n\n Goodbye");
					}
				}
			}
		
	}




/************************************************************************************************************************************************************************************/



	private static void main_customerInterface() throws Exception {
		int customerLoop=0;
		
		while(customerLoop==0) {
			Scanner customerScanner = new Scanner(System.in);
			int customerTypeDifferientator = customerScanner .nextInt();
			
			if(customerTypeDifferientator == 1) {
				customerLoop=1;
				customer_ExistingUser();
			}
			
			if(customerTypeDifferientator==2) {
				customerLoop=2;
				Customer.registerCustomer();
			}
			else{
				System.out.println("Invalid Input");	
			}
		}
	}

	private static void customer_ExistingUser() {
		ArrayList<Integer> availableAccounts;
		
		Scanner customerScanner = new Scanner(System.in);
		// Instantiate Customer Object
		Customer person = new Customer();
		userID = person.loginCustomer();
		status = person.status;
		String firstName= person.getFirstName();
		String lastName= person.getLastName();
		
		// Implement Main Menu Loop
		int customerMenuLooper=0;
		
		// Differentiate between cycles
		int rotation=0;
		
		//Restarts Program If User Account Not Activated
		if(status.equalsIgnoreCase("Pending")|| status.equalsIgnoreCase("Denied")) {
			main_restartProgram();
		}
		
		// Initiates Active Customer Function
		while(customerMenuLooper==0 && status.equalsIgnoreCase("Active")) {
			
			// Print Customer Name if reset to main menu
			if(rotation>0) {
				main_clearScreen();
				System.out.println("Customer: "+firstName+""+" "+" "+lastName+"\n");
			}
			
			// Menu Options
			System.out.println("Please Select From The Following Options\n");
			System.out.println("1. Display all currently active accounts");
			System.out.println("2. Apply for a new account");
			System.out.println("3. Initiate a Transfer");
			
			// Take Customer Input
			int customerMenuDecider = customerScanner.nextInt();
			
			// Display Active Accounts
			if(customerMenuDecider == 1) {	
				customer_currentActiveAccounts(rotation);
			}
			
			// Apply For New Account
			if(customerMenuDecider == 2) {
				customer_AccountApplication();
			}
			
			// Initiate A Transfer
			if(customerMenuDecider == 3) {
				customer_TransferFunction();
			}
			else {
				System.out.println("Invalid Input");
			}
		}
	}

	private static void customer_TransferFunction() {
	Scanner customerScanner = new Scanner(System.in);
	ArrayList<Integer> availableAccounts;
	int transferLoop=0;
	while(transferLoop==0) {
		Scanner transferScanner = new Scanner(System.in);
		String continueTransfer;
		int validTransferFromAccount = 0;
		//Display Currently Active Accounts
		availableAccounts=displayCustomerCurrentAccounts();
		
		// Select "Transfer From" Account
		int transferFromLoop=0;
		while(transferFromLoop ==0) {
			System.out.println("Select which account you would like to transfer from: ");
			int transferFromAccount=customerScanner.nextInt();
			if(availableAccounts.contains(transferFromAccount)) {
				validTransferFromAccount=transferFromAccount;
				transferFromLoop=1;
			}
			else {
				System.out.println("That is not a valid account");
				System.out.println("Would you like to continue with the transfer?");
				continueTransfer=transferScanner.nextLine();
				if(continueTransfer.equalsIgnoreCase("yes")) {
					main_clearScreen();
					System.out.println(availableAccounts);
					transferFromLoop=0;
				}
				else
					transferFromLoop=1;
				
			}
		}
		
		// Enter "Transfer To" Account
		int transferToLoop=0;
		int transferToAccount;
		int validatedTransferToAccount=0;
		while(transferToLoop ==0 && validTransferFromAccount!= 0) {
			System.out.println("Do you know the account number into which you would like to transfer?");
			// Take User Input
			continueTransfer=transferScanner.nextLine();
			
			if(continueTransfer.equalsIgnoreCase("yes")) {
				System.out.println("Enter the account number you would like to transfer to: ");
				
				// Take User Input
				transferToAccount=customerScanner.nextInt();
				
				// Validate Transfer To Account
				ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
				
				try (Connection connection = DriverManager.getConnection(url, username, password)){
					Statement statement = connection.createStatement();
					String search = "select accountid from accounts";
					
					boolean isResultSet = statement.execute(search);
				
					if (isResultSet) {
						ResultSet resultSet = statement.getResultSet();
						ResultSetMetaData rsmd = resultSet.getMetaData();
				
						while (resultSet.next()) {
							
							int accountnum = resultSet.getInt(1);
						
							accountNumbers.add(accountnum);
						}
							resultSet.close();
					} 
				
						statement.close();
																									} 
				catch (SQLException ex) {
						ex.printStackTrace();
										}
				
				if(accountNumbers.contains(transferToAccount)) {
					validatedTransferToAccount=transferToAccount;
					transferToLoop=1;
				}
			
				else {
					System.out.println("That is not a valid account");
				}
			}
		else {
			main_clearScreen();
			System.out.println("Please contact your institution before proceeding");
			System.out.println("Would you like to continue with the transfer?");
			continueTransfer=transferScanner.nextLine();
			if(continueTransfer.equalsIgnoreCase("yes")) {
				main_clearScreen();
				System.out.println("Your available accounts: "+ availableAccounts);
				transferToLoop=0;
			}
			else {
				transferToLoop=1;
				transferLoop=1;
				main_clearScreen();
			}
		}
			
		}
		// Enter Amount to Transfer
		System.out.println("Enter the amount you would like to transfer");
		BigDecimal transferAmount = customerScanner.nextBigDecimal();
		
		// Validate amount is available
		BigDecimal accountbalance = null;
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			String search = "select balance from accounts where accountid = "+validTransferFromAccount+"";

			boolean isResultSet = statement.execute(search);
		
			if (isResultSet) {
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
				int i=0;
				
				while (resultSet.next()) {
					accountbalance = resultSet.getBigDecimal(1);
					System.out.print("\t"+accountbalance+"\t");
										}
					resultSet.close();
							} 
				statement.close();
		} 
		catch (SQLException ex) {
				ex.printStackTrace();
		}
		
		// Transfer
		if(accountbalance.compareTo(transferAmount)>=0 && validatedTransferToAccount!=0) {
			// Add to Account
			try (Connection connection = DriverManager.getConnection(url, username, password)){
				Statement statement = connection.createStatement();
				String transfer = "UPDATE accounts SET balance = balance + "+transferAmount+"where accountid =  "+validatedTransferToAccount+"";
				statement.executeUpdate(transfer);
			
					statement.close();
																								} 
			catch (SQLException ex) {
				
					ex.printStackTrace();
					
									}
			// Subtract from Account
			try (Connection connection = DriverManager.getConnection(url, username, password)){
				Statement statement = connection.createStatement();
				String transfer = "UPDATE accounts SET balance = balance - "+transferAmount+"where accountid = "+validTransferFromAccount+"";
				statement.executeUpdate(transfer);
				
				String log = "INSERT INTO transactions(time, type, amount, account, userid)"
						+"VALUES (NOW(),'Transfer',"+transferAmount+","+validTransferFromAccount+", '{"+userID+"}')";
		
				statement.execute(log);
				statement.close();
																								} 
			catch (SQLException ex) {
				
					ex.printStackTrace();
					
									}
			
		}
		
		//Display Updated Accounts
		displayCustomerCurrentAccounts();
		transferLoop=1;
}
}


/************************************************************************************************************************************************************************************/
	
/*
 * MAIN functions
 */
	public static int main_bankDecider() throws Exception{
		int x=0;
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
				main_clearScreen();
				System.out.println("Invalid Response\n Please Try Again\n\n ");
				System.out.println("Are You A SGBanking Employee?");
			}
		return x;
	}
	private static void main_employeeInterface() throws Exception {		
		// Base Employee Permissions
		
		if(EmployeeType.equalsIgnoreCase("Employee")) {
			employeeActions();
		}
		
		// Administrative Permissions
		
		else if(EmployeeType.equalsIgnoreCase("Admin")){
			adminActions();
			
		}
}
	private static void main_restartProgram() {
		if(restart.equalsIgnoreCase("yes")) {
		try {
			System.out.println("\nRestarting Program in 3");
		    Thread.sleep(1000); 
		    System.out.println("\n                      2");
		    Thread.sleep(1000); 
		    System.out.println("\n                      1");
		    Thread.sleep(1000); 
		    main_clearScreen();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		}
	} 
	private static void main_printWelcome() {
		System.out.println();
		System.out.println("--------------------------------\n");
		System.out.println("~ Hello, Welcome To SG Banking ~\n");
		System.out.println("--------------------------------\n");
		System.out.println("Are You A SGBanking Employee?");
		
	}
	public static void main_clearScreen() {  
	    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  
		}
	


/*
 * EMPLOYEE functions	
 */
	private static void employeeActions() {
		String display;
		String empcont = "yes";
		Scanner empScan = new Scanner(System.in);
		
		while( empcont.equalsIgnoreCase("yes")) {
			main_clearScreen();
			System.out.println();
			System.out.println();
			Employee.displayEmployeeWelcomeMessage();
			System.out.println();
			System.out.println("Allowed Actions:");
			System.out.println();
			System.out.println("1. View Active Accounts");
			System.out.println("2. View Pending User Accounts");
			System.out.println("3. View Denied Accounts");
	
			Scanner scanner = new Scanner(System.in);
			int AllowedAction = scanner.nextInt();
			
			
			if (AllowedAction == 1)
				employee_ViewActiveAccounts(empScan, scanner);
			
			else if (AllowedAction == 2) {
				managePendingAccounts();
				System.out.println("\nWould you like to return to the main menu?");
				empcont = empScan.nextLine();
				
			}
			
			else if (AllowedAction == 3) {
				
				displayDeniedAccounts();
				System.out.println("Would you like to return to the main menu?");
				empcont = empScan.nextLine();
				
			}
			else {
				System.out.println("Invalid Input");
				System.out.println("Would you like to return to the main menu?");
				empcont = empScan.nextLine();
			}
		}
	
			main_clearScreen();
			System.out.println("Goodbye");
	}	
	private static void employeeWelcome() {
		main_clearScreen();
		Employee.employeeWelcome();
		
	}
	
	
	
	
	private static void employee_ViewActiveAccounts(Scanner empScan, Scanner scanner) {
		String display;
		{
			
			String viewMoreAccounts="yes";
		
			while(viewMoreAccounts.equalsIgnoreCase("yes")){
				
			
			displayActiveUserAccounts();
			System.out.println("\nAllowed Actions: ");
			System.out.println("1. View Account Detailed Information");
			System.out.println("2. Return to Main Menu");
			
			int allowedActiveActions = empScan.nextInt();
			if(allowedActiveActions==1) {
				
				System.out.println("Which User Account would you like to select");
				int activeUserID = scanner.nextInt() ;
				
				int currentAccounts= 0;
				ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
				
				try (Connection connection = DriverManager.getConnection(url, username, password)){
					Statement statement = connection.createStatement();
					display = "select * from accounts where "+activeUserID+" = ANY (userid) AND status = 'Active'";
				
					//SELECT * FROM sal_emp WHERE 10000 = ANY (pay_by_quarter);
					
					boolean isResultSet = statement.execute(display);
					main_clearScreen();
					if (isResultSet) {
						ResultSet resultSet = statement.getResultSet();
						ResultSetMetaData rsmd = resultSet.getMetaData();
						System.out.print("Account"+"\t");
						System.out.print("Type"+"\t");
						System.out.print("\t"+"Balance"+"\t");
						System.out.print("\t"+"Status"+"\t");
						System.out.println();
						System.out.println();
						
						int i=0;
						
						while (resultSet.next()) {
							
							int accountNumber = resultSet.getInt(1);
							String accountType = resultSet.getString(2);
							BigDecimal accountBalance = resultSet.getBigDecimal(3);
							String accountStatus = resultSet.getString(4);
							// Print Account Details
							if(accountType.equalsIgnoreCase("Savings")) {
							System.out.print(accountNumber);
							System.out.print("\t"+accountType+"\t");
							System.out.print("\t"+accountBalance+"\t");
							System.out.print("\t"+accountStatus);
							System.out.println();
							System.out.println("--------------------------------------------------------------------------------");
							}
							if(accountType.equalsIgnoreCase("Checking")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType);
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
							if(accountType.equalsIgnoreCase("Joint")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType+"\t");
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
												}
							resultSet.close();
									}
					display = "select * from customers where bankID = "+activeUserID+"";
					
					
					statement.execute(display);
					
					ResultSet resultSet = statement.getResultSet();
					ResultSetMetaData rsmd = resultSet.getMetaData();
					
					while (resultSet.next()) {
						String firstName = resultSet.getString(1);
						String lastName = resultSet.getString(2);
						int userID= resultSet.getInt(8);
						int flength= firstName.length();
						if (flength < 10) {
							for(int i =0;i<=(10-flength);i++)
								firstName= firstName + " ";
						}

						System.out.print("\nCurrently Viewing Accounts For: \t");
						System.out.print(userID+"\t");
						System.out.print(firstName+" ");
						System.out.print(lastName);
						
											}
					
				
																									} 
				catch (SQLException ex) {
					
						ex.printStackTrace();
						
										}
				System.out.println("\n\nWould you like view more active accounts?");
				Scanner activeScan = new Scanner(System.in);
				viewMoreAccounts = activeScan.nextLine();
			}
			
			
			if(allowedActiveActions==2) {
				viewMoreAccounts="no";
			}
			}
		}
	}	
	
	
	
	
	/*
 * ADMIN functions	
 */
		
	private static void adminActions() {
		String display;
		String empcont = "yes";
		Scanner empScan = new Scanner(System.in);
		
		while( empcont.equalsIgnoreCase("yes")) {
			main_clearScreen();
			System.out.println();
			System.out.println();
			Employee.displayEmployeeWelcomeMessage();
			System.out.println();
			System.out.println("Allowed Actions:");
			System.out.println();
			System.out.println("1. View Active Accounts");
			System.out.println("2. View Pending User Accounts");
			System.out.println("3. View Denied Accounts");
	
			Scanner scanner = new Scanner(System.in);
			int AllowedAction = scanner.nextInt();
			
			
			if (AllowedAction == 1)
				admin_ViewActiveAccounts(empScan, scanner);
			
			else if (AllowedAction == 2) {
				managePendingAccounts();
				System.out.println("\nWould you like to return to the main menu?");
				empcont = empScan.nextLine();
				
			}
			
			else if (AllowedAction == 3) {
				
				displayDeniedAccounts();
				System.out.println("Would you like to return to the main menu?");
				empcont = empScan.nextLine();
				
			}
			else {
				System.out.println("Invalid Input");
				System.out.println("Would you like to return to the main menu?");
				empcont = empScan.nextLine();
			}
		}
	
			main_clearScreen();
			System.out.println("Goodbye");
	}




	private static void admin_ViewActiveAccounts(Scanner empScan, Scanner scanner) {
		String display;
		{
			
			String viewMoreAccounts="yes";
		
			while(viewMoreAccounts.equalsIgnoreCase("yes")){
				
			
			displayActiveUserAccounts();
			System.out.println("\nAllowed Actions: ");
			System.out.println("1. View Account Detailed Information");
			System.out.println("2. View Recent Transaction Information");
			System.out.println("3. Return to Main Menu");
			
			int allowedActiveActions = empScan.nextInt();
			
			if(allowedActiveActions==1) {
				
				System.out.println("Which User Account would you like to select");
				int activeUserID = scanner.nextInt() ;
				
				int currentAccounts= 0;
				ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
				
				try (Connection connection = DriverManager.getConnection(url, username, password)){
					Statement statement = connection.createStatement();
					display = "select * from accounts where "+activeUserID+" = ANY (userid) AND status = 'Active'";
				
					boolean isResultSet = statement.execute(display);
					main_clearScreen();
					if (isResultSet) {
						ResultSet resultSet = statement.getResultSet();
						ResultSetMetaData rsmd = resultSet.getMetaData();
						System.out.print("Account"+"\t");
						System.out.print("Type"+"\t");
						System.out.print("\t"+"Balance"+"\t");
						System.out.print("\t"+"Status"+"\t");
						System.out.println();
						System.out.println();
						
						int i=0;
						
						while (resultSet.next()) {
							
							int accountNumber = resultSet.getInt(1);
							String accountType = resultSet.getString(2);
							BigDecimal accountBalance = resultSet.getBigDecimal(3);
							String accountStatus = resultSet.getString(4);
							// Print Account Details
							if(accountType.equalsIgnoreCase("Savings")) {
							System.out.print(accountNumber);
							System.out.print("\t"+accountType+"\t");
							System.out.print("\t"+accountBalance+"\t");
							System.out.print("\t"+accountStatus);
							System.out.println();
							System.out.println("--------------------------------------------------------------------------------");
							}
							if(accountType.equalsIgnoreCase("Checking")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType);
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
							if(accountType.equalsIgnoreCase("Joint")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType+"\t");
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
												}
							resultSet.close();
						}
					
					display = "select * from customers where bankID = "+activeUserID+"";
					
					
					statement.execute(display);
					
					ResultSet resultSet = statement.getResultSet();
					ResultSetMetaData rsmd = resultSet.getMetaData();
					
					while (resultSet.next()) {
						String firstName = resultSet.getString(1);
						String lastName = resultSet.getString(2);
						int userID= resultSet.getInt(8);
						int flength= firstName.length();
						if (flength < 10) {
							for(int i =0;i<=(10-flength);i++)
								firstName= firstName + " ";
						}

						System.out.print("\nCurrently Viewing Accounts For: \t");
						System.out.print(userID+"\t");
						System.out.print(firstName+" ");
						System.out.print(lastName);
					}
				} 
				catch (SQLException ex) {
						ex.printStackTrace();
				}
				Scanner activeScan = new Scanner(System.in);
				System.out.println("Would you like to cancel this account?");
				String cancelAccount = activeScan.nextLine();
				if(cancelAccount.contentEquals("yes")) {
					try (Connection connection = DriverManager.getConnection(url, username, password)){
						Statement statement = connection.createStatement();
						display = "UPDATE customers\n" + "SET status = 'Cancelled'\n" + "WHERE\n" + " bankID = "+activeUserID+";";
					
						statement.execute(display);
						statement.close();
																										} 
					catch (SQLException ex) {
						
							ex.printStackTrace();
							
											}
				}
				System.out.println("\n\nWould you like view more active accounts?");
				
				viewMoreAccounts = activeScan.nextLine();
			}
			
			if(allowedActiveActions==2) {
				
				System.out.println("Which User Account would you like to select");
				int activeUserID = scanner.nextInt() ;
				
				int currentAccounts= 0;
				ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
				
				try (Connection connection = DriverManager.getConnection(url, username, password)){
					Statement statement = connection.createStatement();
					display = "select * from transactions where userid = "+activeUserID+"";
				
					boolean isResultSet = statement.execute(display);
					
					main_clearScreen();
					if (isResultSet) {
						ResultSet resultSet = statement.getResultSet();
						ResultSetMetaData rsmd = resultSet.getMetaData();
						System.out.print("TransactionID"+"\t");
						System.out.print("TimeStamp"+"\t");
						System.out.print("\t\t"+"Type"+"\t");
						System.out.print("\t"+"Amount"+"\t");
						System.out.print("\t"+"Account"+"\t");
						System.out.println();
						System.out.println();
						while (resultSet.next()) {
							
							int transactionID = resultSet.getInt(1);
							Timestamp time = resultSet.getTimestamp(2);
							String type = resultSet.getString(3);
							String amount = resultSet.getString(4);
							int account = resultSet.getInt(5);
							// Print Account Details
							if(type.equalsIgnoreCase("Deposit")) {
							System.out.print(transactionID+"\t");
							System.out.print("\t"+time+"\t");
							System.out.print(type+"\t");
							System.out.print("\t"+amount);
							System.out.print("\t\t"+account);
							System.out.println();
							System.out.println("------------------------------------------------------------------------------------------");
							}
							if(type.equalsIgnoreCase("Withdrawal")) {
								System.out.print(transactionID);
								System.out.print("\t"+time);
								System.out.print("\t"+type+"\t");
								System.out.print("\t"+amount);
								System.out.print("\t"+account);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
							if(type.equalsIgnoreCase("Transfer")) {
								System.out.print(transactionID);
								System.out.print("\t"+time+"\t");
								System.out.print("\t"+type+"\t");
								System.out.print("\t"+amount);
								System.out.print("\t"+account);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
												}
							resultSet.close();
						}
					
					display = "select * from customers where bankID = "+activeUserID+"";
					
					
					statement.execute(display);
					
					ResultSet resultSet = statement.getResultSet();
					ResultSetMetaData rsmd = resultSet.getMetaData();
					
					while (resultSet.next()) {
						String firstName = resultSet.getString(1);
						String lastName = resultSet.getString(2);
						int userID= resultSet.getInt(8);
						int flength= firstName.length();
						if (flength < 10) {
							for(int i =0;i<=(10-flength);i++)
								firstName= firstName + " ";
						}

						System.out.print("\nCurrently Viewing Transactions For: \t");
						System.out.print(userID+"\t");
						System.out.print(firstName+" ");
						System.out.print(lastName);
					}
				} 
				catch (SQLException ex) {
						ex.printStackTrace();
				}
				System.out.println("\n\nWould you like view more active accounts?");
				Scanner activeScan = new Scanner(System.in);
				viewMoreAccounts = activeScan.nextLine();
			}
			if(allowedActiveActions==3) {
				viewMoreAccounts="no";
			}
			}
		}
	}	

	
	
/*
 * CUSTOMER functions	
 */
	public static void customerWelcome() {
		main_clearScreen();
		Customer.printWelcome();
		
	}
	
	
	private static void customer_AccountApplication() {
		Scanner applicationScanner = new Scanner(System.in);
		//Menu Options
		System.out.println("What kind of account would you like to open?\n");
		System.out.println("1. Checking Account");
		System.out.println("2. Savings Account");
		System.out.println("3. Joint Account");
		
		//Take Customer Input
		int accountCreationDecider = applicationScanner.nextInt();
		
		// Create Checking Account
		if(accountCreationDecider==1) {
			customer_CreateCheckingAccount();
		}
		
		// Create Savings Account
		if(accountCreationDecider==2) {
			customer_CreateSavingsAccount();	
		}
		
		// Create Joint Account
		if(accountCreationDecider==3) {
			customer_CreateJointAccount();
		}
	}
	// Account Application Functions
		private static void customer_CreateJointAccount() {
		ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
		ArrayList <Integer> validUserID = new ArrayList<Integer>();
		Scanner jointScanner = new Scanner(System.in);
		int repeat = 0;
		
		// Enter Joint Account Loop
		while(repeat ==0) {
			System.out.println("Do you have the userID of the person you would like to add to this account?");
			
			// Take Customer Input
			String beginCreation= jointScanner.nextLine();
			
			// If Customer Knows Joint ID
			if(beginCreation.equalsIgnoreCase("yes")) {
				int toJoinID;
				
				// Connect to Database
				try (Connection creatingJointAccount = DriverManager.getConnection(url, username, password)){
						Statement createJointAccount = creatingJointAccount.createStatement();
						System.out.println("Please enter the other parties userID now");
						toJoinID = jointScanner.nextInt();
						
						// Validate User ID
						String search = "SELECT bankid FROM customers WHERE status = 'Active'";
						
						boolean isResultSet = createJointAccount.execute(search);
						if (isResultSet) {	
							ResultSet resultSet = createJointAccount.getResultSet();
							
							// Cycle Through ResultSet
							while (resultSet.next()) {	
								
								// Pull Account Details
								int identifier = resultSet.getInt(1);
								validUserID.add(identifier);
							}

								resultSet.close();
						}
						
						// If User ID is Valid
						if(validUserID.contains(toJoinID)) {
							
							// Create Savings Account
							String create= "INSERT INTO accounts(type, balance, status, userid)"
											+ "VALUES ('Joint', 0.00,'Pending','{"+userID+","+ toJoinID +"}')";
							createJointAccount.executeUpdate(create);
							
							// Display Currently Pending Accounts
							int currentAccounts= 0;
							String display = "SELECT * FROM accounts WHERE status = 'Pending' AND "+userID+" = ANY (userid)";
							boolean isResultSet1 = createJointAccount.execute(display);
							if (isResultSet1) {
								ResultSet resultSet1 = createJointAccount.getResultSet();
								main_clearScreen();
								
								// Print Header Line
								System.out.print("Account"+"\t");
								System.out.print("Type"+"\t");
								System.out.print("\t"+"Balance"+"\t");
								System.out.print("\t"+"Status"+"\t");
								System.out.println();
								System.out.println();
								
								// Cycle Through ResultSet
								while (resultSet1.next()) {
									
									// Pull Account Details
									int accountNumber = resultSet1.getInt(1);
									String accountType = resultSet1.getString(2);
									BigDecimal accountBalance = resultSet1.getBigDecimal(3);
									String accountStatus = resultSet1.getString(4);
									
									// Print Account Details
									if(accountType.equalsIgnoreCase("Savings")) {
									System.out.print(accountNumber);
									System.out.print("\t"+accountType+"\t");
									System.out.print("\t"+accountBalance+"\t");
									System.out.print("\t"+accountStatus);
									System.out.println();
									System.out.println("--------------------------------------------------------------------------------");
									}
									if(accountType.equalsIgnoreCase("Checking")) {
										System.out.print(accountNumber);
										System.out.print("\t"+accountType);
										System.out.print("\t"+accountBalance+"\t");
										System.out.print("\t"+accountStatus);
										System.out.println();
										System.out.println("--------------------------------------------------------------------------------");
									}
									if(accountType.equalsIgnoreCase("Joint")) {
										System.out.print(accountNumber);
										System.out.print("\t"+accountType+"\t");
										System.out.print("\t"+accountBalance+"\t");
										System.out.print("\t"+accountStatus);
										System.out.println();
										System.out.println("--------------------------------------------------------------------------------");
									}
									
									// Gather Number Of Accounts
									currentAccounts = resultSet1.getRow();
									accountNumbers.add(accountNumber);
								}
									System.out.println("\nYou currently have " + currentAccounts + " pending applications");
									
									// End Savings Account Creation
									System.out.println("	Your application for a new savings account is currently pending");
									System.out.println("\t\t\t\t -~- \t\t\t\t\t\n");
									resultSet1.close();
							} 
					
							createJointAccount.close();
							repeat=1;
						}	
						else {
							main_clearScreen();
							System.out.println("The userID you entered was not found");
						}
				
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
		}
		else {
			main_clearScreen();
			System.out.println("Please contact your institution before proceeding with creating a joint account\n\n");
			repeat=1;
		}
		}
	}
		private static void customer_CreateSavingsAccount() {
			ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
			
			// Connect to Database
			try (Connection creatingSavingsAccount = DriverManager.getConnection(url, username, password)){
					Statement createSavingsAccount = creatingSavingsAccount.createStatement();
					
					// Create Savings Account
					String create= "INSERT INTO accounts(type, balance, status, userid)"
							+ "VALUES ('Savings', 0.00,'Pending','{"+userID+"}')";
					createSavingsAccount.executeUpdate(create);
					
					// Display Currently Pending Accounts
					int currentAccounts= 0;
					String display = "SELECT * FROM accounts WHERE status = 'Pending' AND "+userID+" = ANY (userid)";
					boolean isResultSet = createSavingsAccount.execute(display);
					if (isResultSet) {
						
						ResultSet resultSet = createSavingsAccount.getResultSet();
						main_clearScreen();
						
						// Print Header Line
						System.out.print("Account"+"\t");
						System.out.print("Type"+"\t");
						System.out.print("\t"+"Balance"+"\t");
						System.out.print("\t"+"Status"+"\t");
						System.out.println();
						System.out.println();
						
						// Cycle Through ResultSet
						while (resultSet.next()) {
							
							// Pull Account Details
							int accountNumber = resultSet.getInt(1);
							String accountType = resultSet.getString(2);
							BigDecimal accountBalance = resultSet.getBigDecimal(3);
							String accountStatus = resultSet.getString(4);
							
							// Print Account Details
							if(accountType.equalsIgnoreCase("Savings")) {
							System.out.print(accountNumber);
							System.out.print("\t"+accountType+"\t");
							System.out.print("\t"+accountBalance+"\t");
							System.out.print("\t"+accountStatus);
							System.out.println();
							System.out.println("--------------------------------------------------------------------------------");
							}
							
							if(accountType.equalsIgnoreCase("Checking")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType);
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
								}
							if(accountType.equalsIgnoreCase("Joint")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType+"\t");
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
							// Gather Number Of Accounts
							currentAccounts = resultSet.getRow();
							accountNumbers.add(accountNumber);
						}
							System.out.println("\nYou currently have " + currentAccounts + " pending applications");
							resultSet.close();
					} 
			
					createSavingsAccount.close();
					
			} catch (SQLException ex) {
					ex.printStackTrace();
				}
			
			// End Savings Account Creation
			System.out.println("	Your application for a new savings account is currently pending");
			System.out.println("\t\t\t\t -~- \t\t\t\t\t\n");
		}
		private static void customer_CreateCheckingAccount() {
			ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
			
			// Connect to Database
			try (Connection creatingCheckingAccount = DriverManager.getConnection(url, username, password)){
					Statement createCheckingAccount = creatingCheckingAccount.createStatement();
					
					// Create Checking Account
					String create= "INSERT INTO accounts(type, balance, status, userid)"
							+ "VALUES ('Checking', 0.00,'Pending','{"+userID+"}')";
					createCheckingAccount.executeUpdate(create);
					
					// Display Currently Pending Accounts
					int currentAccounts= 0;
					String display = "SELECT * FROM accounts WHERE status = 'Pending' AND "+userID+" = ANY (userid)";
					boolean isResultSet = createCheckingAccount.execute(display);
					if (isResultSet) {
						
						ResultSet resultSet = createCheckingAccount.getResultSet();
						main_clearScreen();
						
						// Print Header Line
						System.out.print("Account"+"\t");
						System.out.print("Type"+"\t");
						System.out.print("\t"+"Balance"+"\t");
						System.out.print("\t"+"Status"+"\t");
						System.out.println();
						System.out.println();
						
						// Cycle Through ResultSet
						while (resultSet.next()) {
							
							// Pull Account Details
							int accountNumber = resultSet.getInt(1);
							String accountType = resultSet.getString(2);
							BigDecimal accountBalance = resultSet.getBigDecimal(3);
							String accountStatus = resultSet.getString(4);
							
							// Print Account Details
							if(accountType.equalsIgnoreCase("Savings")) {
							System.out.print(accountNumber);
							System.out.print("\t"+accountType+"\t");
							System.out.print("\t"+accountBalance+"\t");
							System.out.print("\t"+accountStatus);
							System.out.println();
							System.out.println("--------------------------------------------------------------------------------");
							}
							
							if(accountType.equalsIgnoreCase("Checking")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType);
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
								}
							if(accountType.equalsIgnoreCase("Joint")) {
								System.out.print(accountNumber);
								System.out.print("\t"+accountType+"\t");
								System.out.print("\t"+accountBalance+"\t");
								System.out.print("\t"+accountStatus);
								System.out.println();
								System.out.println("--------------------------------------------------------------------------------");
							}
							// Gather Number Of Accounts
							currentAccounts = resultSet.getRow();
							accountNumbers.add(accountNumber);
						}
							System.out.println("\nYou currently have " + currentAccounts + " pending applications");
							resultSet.close();
					} 
			
					createCheckingAccount.close();
					
			} catch (SQLException ex) {
					ex.printStackTrace();
				}
			
			// End Checking Account Creation
			System.out.println("	Your application for a new checking account is currently pending");
			System.out.println("\t\t\t\t -~- \t\t\t\t\t\n");
		}
		
	
	private static void customer_currentActiveAccounts(int rotation) {
		ArrayList<Integer> availableAccounts;
		Scanner customerScanner = new Scanner(System.in);
		int customerLooper;
		availableAccounts=displayCustomerCurrentAccounts();
		System.out.println("\nPlease Select From The Following Options\n");
		System.out.println("1. Return to Main Menu");
		System.out.println("2. Make A Deposit");
		System.out.println("3. Make A Withdrawal");
		
		int accountActionDecider = customerScanner.nextInt();
		
		if(accountActionDecider==1) {
			customerLooper=0;
			rotation++;
		}
		
		if(accountActionDecider==2) {
			makeDeposit(availableAccounts, customerScanner);	
		}
		
		if(accountActionDecider==3) {
			makeWithdrawal(availableAccounts, customerScanner);	
		}
	}

	private static void makeDeposit(ArrayList<Integer> availableAccounts, Scanner customerDecider) {
		String display;
		main_clearScreen();
		System.out.println("Into which Account would you like to make a deposit?\n");
		
		System.out.println(availableAccounts);
		
		int depositAccountDecider = customerDecider.nextInt();
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from accounts where accountid = "+depositAccountDecider+"";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				System.out.print("Account"+"\t");
				System.out.print("Type"+"\t");
				System.out.println();
				
				int i=0;
				
				while (resultSet.next()) {
					
					int accountnum = resultSet.getInt(1);
					String accounttype = resultSet.getString(2);
					BigDecimal accountbalance = resultSet.getBigDecimal(3);
					String accountStatus = resultSet.getString(4);
					System.out.print(accountnum);
					System.out.print("\t"+accounttype);
					System.out.println();
					System.out.println();
					System.out.print("Current Balance: ");
					System.out.print("\t"+accountbalance+"\t");

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
		System.out.println("How much would you like to deposit?");
		
		BigDecimal depositAmountDecider = customerDecider.nextBigDecimal();
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "UPDATE accounts SET balance= balance + "+depositAmountDecider+"WHERE accountid = "+depositAccountDecider+"";
		
			statement.executeUpdate(display);
			
			String log = "INSERT INTO transactions(time, type, amount, account, userid)"
						+"VALUES (NOW(),'Deposit',"+depositAmountDecider+","+depositAccountDecider+", "+userID+")";
		
			statement.execute(log);
			statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
	}
	private static void makeWithdrawal(ArrayList<Integer> availableAccounts, Scanner customerDecider) {
		String display;
		main_clearScreen();
		System.out.println("Into which Account would you like to make a withdrawal?\n");
		for(int i=1;i<=availableAccounts.size();i++)
			System.out.println(availableAccounts);
		
		int withdrawalAccountDecider = customerDecider.nextInt();
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from accounts where accountid = "+withdrawalAccountDecider+"";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				System.out.print("Account"+"\t");
				System.out.print("Type"+"\t");
				System.out.println();
				
				int i=0;
				
				while (resultSet.next()) {
					
					int accountnum = resultSet.getInt(1);
					String accounttype = resultSet.getString(2);
					BigDecimal accountbalance = resultSet.getBigDecimal(3);
					String accountStatus = resultSet.getString(4);
					System.out.print(accountnum);
					System.out.print("\t"+accounttype);
					System.out.println();
					System.out.println();
					System.out.print("Current Balance: ");
					System.out.print("\t"+accountbalance+"\t");

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
		System.out.println("How much would you like to deposit?");
		BigDecimal withdrawalAmountDecider = customerDecider.nextBigDecimal();
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "UPDATE accounts SET balance= balance - "+withdrawalAmountDecider+"WHERE accountid = "+withdrawalAccountDecider+"";
		
			statement.execute(display);
			
			String log = "INSERT INTO transactions(time, type, amount, account, userid)"
					+"VALUES (NOW(),'Withdrawal',"+withdrawalAmountDecider+","+withdrawalAccountDecider+", '{"+userID+"}')";
	
			statement.execute(log);
			statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
	}
	private static ArrayList<Integer> displayCustomerCurrentAccounts() {
		String display;
		int currentAccounts= 0;
		ArrayList <Integer> accountNumbers = new ArrayList<Integer>();
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from accounts where "+userID+" = ANY (userid) AND status = 'Active'";
		
			//SELECT * FROM sal_emp WHERE 10000 = ANY (pay_by_quarter);
			
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				System.out.print("Account"+"\t");
				System.out.print("Type"+"\t");
				System.out.print("\t"+"Balance"+"\t");
				System.out.print("\t"+"Status"+"\t");
				System.out.println();
				System.out.println();
				
				int i=0;
				
				while (resultSet.next()) {
					
					int accountnum = resultSet.getInt(1);
					String accounttype = resultSet.getString(2);
					BigDecimal accountbalance = resultSet.getBigDecimal(3);
					String accountStatus = resultSet.getString(4);
					System.out.print(accountnum);
					System.out.print("\t"+accounttype);
					System.out.print("\t"+accountbalance+"\t");
					System.out.print("\t"+accountStatus);

					System.out.println();
					System.out.println("--------------------------------------------------------------------------------");
					currentAccounts = resultSet.getRow();
					//System.out.println(accountnum);
					accountNumbers.add(accountnum);
										}
					resultSet.close();
							} 
		
				statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
		System.out.println("\nYou currently have " + currentAccounts + " active accounts");
		return accountNumbers;
	}

	
	

	private static void managePendingAccounts() {
		String p2Decider = null;
		int numberOfAccounts;
		Scanner PendingDecider = new Scanner(System.in);
		String pDecider;
		// Display Accounts with Pending Status
		
		numberOfAccounts=displayPendingUserAccounts();
		
		if (numberOfAccounts>0) {
			
			System.out.println("\n Would You Like to Update the Status of These User Accounts?");
			pDecider = PendingDecider.nextLine();
	
	
			if(pDecider.equalsIgnoreCase("yes")){
				
				pendingUserAccountManagement(PendingDecider);
			
												} 
			else
				try {
					main_employeeInterface();
				} catch (Exception e) {
					main_clearScreen();
					System.out.println("You have committed an action which was deemed unsafe\n\n Goodbye");
				}
		}
		
			System.out.println("Would you like to view pending Bank Account Applications");
			pDecider = PendingDecider.nextLine();
			
			if(pDecider.equalsIgnoreCase("yes")) {
				
				int pendingBankLoop=0;
				while(pendingBankLoop==0) {
				String managePendingBankAccounts = "yes";
				
				while (managePendingBankAccounts.equalsIgnoreCase("yes")){
					
					numberOfAccounts = displayPendingBankAccounts();
					
					if(numberOfAccounts>0) {
					System.out.println("\n Would You Like to Update the Status of These Bank Accounts?");
					pDecider = PendingDecider.nextLine();
	
	
					if(pDecider.equalsIgnoreCase("yes")){
						
						System.out.println("Which Account Would you like to update");
						p2Decider = PendingDecider.nextLine();
						
						selectPendingBankAccount(p2Decider);
						
														
	
					System.out.println("Would you like to approve or deny this application");
					Scanner empDScanner = new Scanner(System.in);
					String empDecision = empDScanner.nextLine();
					
					// Approve Functionality
					
					if(empDecision.equalsIgnoreCase("approve")) {
						
						approvePending(p2Decider);
						
						// Set Up Customer Bank Account
						try (Connection connection = DriverManager.getConnection(url, username, password)){

							
							//while(true) {
						Statement statement = connection.createStatement();
								
						String customer;
						
						customer= "UPDATE accounts SET status='Active' WHERE accountid = "+p2Decider+"";
						
						
						
						statement.executeUpdate(customer);
						
						}
	
						 catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 }
						
																}
					// Deny Functionality
					
					if(empDecision.equalsIgnoreCase("deny")) {		
						
						try (Connection connection = DriverManager.getConnection(url, username, password)){
							
							//while(true) {
						Statement statement = connection.createStatement();
								
						String customer;
						
						customer= "DELETE FROM accounts WHERE accountid = "+p2Decider+"";
						
						
						
						statement.executeUpdate(customer);
						
						}
	
						 catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 }
															}
					
														}
					System.out.println("Would you like to continue with pending Bank Acounts?");
					}
					managePendingBankAccounts= PendingDecider.nextLine();
					if(managePendingBankAccounts.equalsIgnoreCase("yes"))
						pendingBankLoop=0;
					else
						pendingBankLoop=1;
				}
					managePendingBankAccounts="no";
					
				}
				
			} else
				try {
					main_employeeInterface();
				} catch (Exception e) {
					main_clearScreen();
					System.out.println("You have committed an action which was deemed unsafe\n\n Goodbye");
				}
	}

	private static void pendingUserAccountManagement(Scanner PendingDecider) {
		String p2Decider;
		System.out.println("Whose Account Would you like to update");
		p2Decider = PendingDecider.nextLine();
		
		selectPendingUserAccount(p2Decider);
		
										

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
				+ "VALUES ('Checking', 0.00,'Active','{"+bankID+"}')";
		
		
		
		statement.executeUpdate(customer);
		
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

	private static void displayActiveUserAccounts() {
		String display;
		int activeAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from customers where status = " + "'Active'";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String customerEmail = resultSet.getString(5);
					String customerStatus = resultSet.getString(7);
					int userID= resultSet.getInt(8);
					int flength= firstName.length();
					if (flength < 10) {
						for(int i =0;i<=(10-flength);i++)
							firstName= firstName + " ";
					}
					System.out.print("UserID" +"  ");
					System.out.print("First Name\t");
					System.out.print("Last Name");
					System.out.print("\t"+"Email"+"\t\t");
					System.out.print("\t"+"\t"+"Status\n\n");
					
					System.out.print(userID+"\t");
					System.out.print(firstName+"\t");
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
	private static void displayActiveUserBankAccounts(int activeUserId) {
		String display;
		int activeAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from customers where userID = " + ""+activeUserId+"";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					String firstName = resultSet.getString(1);
					String lastName = resultSet.getString(2);
					String customerEmail = resultSet.getString(5);
					String customerStatus = resultSet.getString(7);
					int userID= resultSet.getInt(8);
					int flength= firstName.length();
					if (flength < 10) {
						for(int i =0;i<=(10-flength);i++)
							firstName= firstName + " ";
					}
					System.out.print("UserID" +"  ");
					System.out.print("First Name\t");
					System.out.print("Last Name");
					System.out.print("\t"+"Email"+"\t\t");
					System.out.print("\t"+"\t"+"Status\n\n");
					
					System.out.print(userID+"\t");
					System.out.print(firstName+"\t");
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
		String display;
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "UPDATE customers\n" + "SET status = 'Denied'\n" + "WHERE\n" + " fname = '"+p2Decider+"';";
		
			statement.execute(display);
			statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
	}

	private static void approvePending(String p2Decider) {
		String display;
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "UPDATE customers\n" + "SET status = 'Active'\n" + "WHERE\n" + " fname = '"+p2Decider+"';";
		
			statement.execute(display);
			statement.close();
																							} 
		catch (SQLException ex) {
			
				ex.printStackTrace();
				
								}
	}

	private static int selectPendingUserAccount(String p2Decider) {
		String display;
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from customers where fname = " + "'"+p2Decider+"'";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
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

	private static int displayPendingUserAccounts() {
		String display;
		int pendingAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from customers where status = " + "'Pending'";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
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
		return pendingAccounts;
	}
	private static int displayPendingBankAccounts() {
		String display;
		int pendingAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from accounts where status = " + "'Pending'";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
				ResultSet resultSet = statement.getResultSet();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
			
				while (resultSet.next()) {
					int accountID = resultSet.getInt(1);
					String type = resultSet.getString(2);
					String status = resultSet.getString(4);
					Array customerID = resultSet.getArray(5);
					
					System.out.print(accountID +"\t");
					System.out.print(type);
					System.out.print("\t"+"\t"+status);
					System.out.print("\t"+"\t"+customerID);
					
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
		
		return pendingAccounts;
	}



	
	
	
	private static void displayDeniedAccounts() {
		String display;
		int deniedAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from customers where status = " + "'Denied'";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
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
		String display;
		int cancelledAccounts= 0;
		
		try (Connection connection = DriverManager.getConnection(url, username, password)){
			Statement statement = connection.createStatement();
			display = "select * from customers where status = " + "'Cancelled'";
		
			boolean isResultSet = statement.execute(display);
		
			if (isResultSet) {
				main_clearScreen();
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

	private static int selectPendingBankAccount(String p2Decider) {
		String display;
		int pendingAccounts= 0;
	try (Connection connection = DriverManager.getConnection(url, username, password)){
		Statement statement = connection.createStatement();
		display = "select * from accounts where accountid = " + "'"+p2Decider+"'";
	
		boolean isResultSet = statement.execute(display);
	
		if (isResultSet) {
			main_clearScreen();
			ResultSet resultSet = statement.getResultSet();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			
		
			while (resultSet.next()) {
				int accountID = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String status = resultSet.getString(4);
				Array customerID = resultSet.getArray(5);
				
				System.out.print(accountID +"\t");
				System.out.print(type);
				System.out.print("\t"+"\t"+status);
				System.out.print("\t"+"\t"+customerID);
				
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
	return bankID;
	}

}
