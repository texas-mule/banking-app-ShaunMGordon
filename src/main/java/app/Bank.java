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
import app.Customer;
import app.Employee;
import java.util.ArrayList;
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
	//static String display;
	
/************************************/
	
	// Main
	
/***********************************/
	
	
	public static void main(String[] args) {
		
		// Run Program
		String runProgramVariable = "Go";
		while (runProgramVariable.equalsIgnoreCase("Go")) {
			
			// Print Welcome Message
			main_printWelcome();
		
			// Implement Method to Decipher Between Employee and Customer	
				int appUserDifferentiator = main_bankDecider();
			
			// Initiate Employee Interface
			if(appUserDifferentiator==1 && EmployeeType!=null) {
				main_employeeInterface();
			}
			
			//Initiate Customer Interface
			if(appUserDifferentiator==2) {
				
				ArrayList<Integer> availableAccounts;
				
				Scanner customerScanner = new Scanner(System.in);
				int customerTypeDifferientator = customerScanner.nextInt();
				
				if(customerTypeDifferientator == 1) {
					
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
							customer_currentActiveAccounts(rotation, customerScanner);
							
						}
						
						// Apply For New Account
						if(customerMenuDecider == 2) {
							
							customer_AccountApplication(customerScanner);
						}
					}
				}
				else{
					Customer.registerCustomer();	
				}
			}
		}
		
	}






private static void customer_AccountApplication(Scanner customerScanner) {
	//Menu Options
	System.out.println("What kind of account would you like to open?\n");
	System.out.println("1. Checking Account");
	System.out.println("2. Savings Account");
	System.out.println("3. Joint Account");
	
	//Take Customer Input
	int accountCreationDecider = customerScanner.nextInt();
	
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
	
/*
 * MAIN functions
 */
	public static int main_bankDecider() {
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
	private static void main_employeeInterface() {		
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
			
			
			if (AllowedAction == 1) {
				
				String viewMoreAccounts="yes";
			
				while(viewMoreAccounts.equalsIgnoreCase("yes")){
					
				
				displayActiveUserAccounts();
				System.out.println("\nAllowed Actions: ");
				System.out.println("1. View Account Detailed Information");
				System.out.println("2. Return to Main Menu");
				
				int allowedActiveActions = empScan.nextInt();
				if(allowedActiveActions==1) {
					
					System.out.println("Which User Account would you like to select");
					int activeUserID = scanner.nextInt();
					
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
						display = "select * from customers where bankID = "+activeUserID+"";
						
						//SELECT * FROM sal_emp WHERE 10000 = ANY (pay_by_quarter);
						
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
	
	
	
	
	/*
 * ADMIN functions	
 */
		
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
			
			displayActiveUserAccounts();
			
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
	
	
/*
 * CUSTOMER functions	
 */
	public static void customerWelcome() {
		main_clearScreen();
		Customer.printWelcome();
		
	}
	
	

	
	
	private static void customer_currentActiveAccounts(int rotation, Scanner customerDecider) {
		ArrayList<Integer> availableAccounts;
		int customerLooper;
		availableAccounts=displayCustomerCurrentAccounts();
		System.out.println("\nPlease Select From The Following Options\n");
		System.out.println("1. Return to Main Menu");
		System.out.println("2. Make A Deposit");
		System.out.println("3. Make A Withdrawal");
		
		int accountActionDecider = customerDecider.nextInt();
		
		if(accountActionDecider==1) {
			customerLooper=0;
			rotation++;
		}
		
		if(accountActionDecider==2) {
			makeDeposit(availableAccounts, customerDecider);	
		}
		
		if(accountActionDecider==3) {
			makeWithdrawal(availableAccounts, customerDecider);	
		}
	}

	private static void makeDeposit(ArrayList<Integer> availableAccounts, Scanner customerDecider) {
		String display;
		main_clearScreen();
		System.out.println("Into which Account would you like to make a deposit?\n");
		for(int i=1;i<=availableAccounts.size();i++)
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
		
			statement.execute(display);
		
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
				main_employeeInterface();
		}
		
			System.out.println("Would you like to view pending Bank Account Applications");
			pDecider = PendingDecider.nextLine();
			
			if(pDecider.equalsIgnoreCase("yes")) {
				
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
					managePendingBankAccounts= PendingDecider.nextLine();
				}
					managePendingBankAccounts="no";
					
				}
				
			}
		
		else
			main_employeeInterface();
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
