package app;

import java.io.IOException;
import java.util.Scanner;
import org.xml.sax.InputSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import app.Customer;
import app.Employee;

public class Bank extends Object{

	static String EmployeeID = null;
	static String EmployeeFirstName;
	static String EmployeeLastName;
	static String EmployeeType;
	static String restart = "no";
	
	
	
//	String employeeID = null;
//	String employeeType = null;
//	static String[] employeeName = null;
//	
//
//	public String getEmployeeID() {
//		return employeeID;
//	}
//
//	public void setEmployeeID(String employeeID) {
//		this.employeeID = employeeID;
//	}
//
//	public String getEmployeeType() {
//		return employeeType;
//	}
//
//	public void setEmployeeType(String employeeType) {
//		this.employeeType = employeeType;
//	}
//
//	public String[] getEmployeeName() {
//		return employeeName;
//	}
//
//	public static void setEmployeeName(String[] employeeName) {
//		Bank.employeeName = employeeName;
//	}
//
//
	
	/************************************/
	
	// Main
	
	/***********************************/
	
	
	public static void main(String[] args) {
		
		String flow = "Go";
		
		while (flow.equalsIgnoreCase("Go")) {
			
			
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
//		String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
//		String username = "postgres";
//		String password = "Safety48@@";
//		String sql;
//		
//		try (Connection connection = DriverManager.getConnection(url, username, password)){
//			Scanner scanner = new Scanner(System.in);
//			
//			while(true) {
//				Statement statement = connection.createStatement();
//				//System.out.print("sql> ");
//				sql = scanner.nextLine();
//				if (sql.equalsIgnoreCase("quit"))
//					break;
//				
//				boolean isResultSet = statement.execute(sql);
//				
//				if (isResultSet) {
//					ResultSet resultSet = statement.getResultSet();
//					ResultSetMetaData rsmd = resultSet.getMetaData();
//					
//					while (resultSet.next()) {
//						for (int i = 1;i<= rsmd.getColumnCount(); i++) {
//							System.out.print(resultSet.getString(i) + "\t");
//							
//						}
//						
//						System.out.println();
//					}
//					resultSet.close();
//				} else {
//					System.out.println(statement.getUpdateCount() + "rows affected");
//				}
//				
//				statement.close();
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
	
//		Print Welcome Message
		int x= 0;
		printWelcome();
		
//		Implement Method to Decipher Between Employee and Customer
		while (x == 0) {
		x = bankDecider(x);
		//System.out.println(EmployeeType);
		
		//System.out.println(employeeName[0] + " " + employeeName[1]);
	}
		if(x==1) {
		if(EmployeeType.equalsIgnoreCase("Employee")) {
			System.out.println();
			System.out.println();
			System.out.println("Allowed Actions:");
			System.out.println();
			System.out.println("1. View Active Accounts");
			System.out.println("2. View Pending Accounts");
			System.out.println("3. View Denied Accounts");
		
		}
		else if(EmployeeType.equalsIgnoreCase("Admin")){
			System.out.println();
			System.out.println();
			System.out.println("Allowed Actions:");
			System.out.println();
			System.out.println("1. View Active Accounts");
			System.out.println("2. View Pending Accounts");
			System.out.println("3. View Denied Accounts");
			System.out.println("4. View Cancelled Acounts");
			
		}
		else
			break;
		
		Scanner scanner = new Scanner(System.in);
		int AllowedAction = scanner.nextInt();
		
		if (AllowedAction == 2) {
			
			String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
			String username = "postgres";
			String password = "Safety48@@";
			String sql;
			
			try (Connection connection = DriverManager.getConnection(url, username, password)){
				Scanner allowedScanner = new Scanner(System.in);
				
				//while(true) {
					Statement statement = connection.createStatement();
					//System.out.print("sql> ");
					sql = "select * from customers where status = " + "'Pending'";
					
					
					if (sql.equalsIgnoreCase("quit"))
						break;
					
					boolean isResultSet = statement.execute(sql);
					
					if (isResultSet) {
						ResultSet resultSet = statement.getResultSet();
						ResultSetMetaData rsmd = resultSet.getMetaData();			
						
						while (resultSet.next()) {
							int i = 1;
							
							EmployeeID = resultSet.getString(1);
							EmployeeFirstName = resultSet.getString(2);
							EmployeeLastName = resultSet.getString(3);
							EmployeeType = resultSet.getString(4);
//							System.out.println(EmployeeID);
//							System.out.println(EmployeeFirstName);
//							System.out.println(EmployeeLastName);
//							System.out.println(EmployeeType);
							
							for (i = 1;i<= rsmd.getColumnCount(); i++) {
								if(i!=3 && i!=4 && i!=6)
								System.out.print(resultSet.getString(i) + "\t");
							
							//loginLooper = 1;
							}
							
							System.out.println();
							//clearScreen();
							
							//System.out.println(resultSet.getString(i));
							i++;
							//System.out.println("Welcome Back " + EmployeeFirstName + " " + EmployeeLastName);
							//System.out.println("Current Permissions: " + EmployeeType );
						}
						resultSet.close();
					} else {
						System.out.println(statement.getUpdateCount() + "rows affected");
					}
					
					statement.close();
				//}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			System.out.println("\n Would You Like to Update the Status of These Accounts?");
			
			Scanner PendingDecider = new Scanner(System.in);
			String pDecider = PendingDecider.nextLine();
			
			if(pDecider.equalsIgnoreCase("yes")){
				System.out.println("Whose Account Would you like to update");
				String p2Decider = PendingDecider.nextLine();
				
				try (Connection connection = DriverManager.getConnection(url, username, password)){
					Scanner allowedScanner = new Scanner(System.in);
					
					//while(true) {
						Statement statement1 = connection.createStatement();
						//System.out.print("sql> ");
						sql = "select * from customers where fname = '"+p2Decider+"'";
						
						if (sql.equalsIgnoreCase("quit"))
							break;
						
						
						
						boolean isResultSet1 = statement1.execute(sql);
						
						if (isResultSet1) {
							ResultSet resultSet1 = statement1.getResultSet();
							ResultSetMetaData rsmd1 = resultSet1.getMetaData();			
							
							while (resultSet1.next()) {
								int i = 1;
								
								//System.out.println("here");
								EmployeeID = resultSet1.getString(1);
								EmployeeFirstName = resultSet1.getString(2);
								EmployeeLastName = resultSet1.getString(3);
								EmployeeType = resultSet1.getString(4);
//								System.out.println(EmployeeID);
//								System.out.println(EmployeeFirstName);
//								System.out.println(EmployeeLastName);
//								System.out.println(EmployeeType);
								
								for (i = 1;i<= rsmd1.getColumnCount(); i++) {
									if(i!=6)
									System.out.print(resultSet1.getString(i) + "\t");
								
								//loginLooper = 1;
								}
								
								System.out.println();
								//clearScreen();
								
								//System.out.println(resultSet.getString(i));
								i++;
								//System.out.println("Welcome Back " + EmployeeFirstName + " " + EmployeeLastName);
								//System.out.println("Current Permissions: " + EmployeeType );
							}
							resultSet1.close();
						} else {
							System.out.println(statement1.getUpdateCount() + "rows affected");
						}
						
						statement1.close();
					//}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			System.out.println("Would you like to approve or deny this application");
			Scanner empDScanner = new Scanner(System.in);
			String empDecision = empDScanner.nextLine();
			
			if(empDecision.equalsIgnoreCase("approve")) {
				
				try (Connection connection = DriverManager.getConnection(url, username, password)){
					
					Scanner allowedScanner = new Scanner(System.in);
					
					//while(true) {
						Statement statement1 = connection.createStatement();
						//System.out.print("sql> ");
						sql = "UPDATE customers\n" + 
								"SET status = 'Active'\n" + 
								"WHERE\n" + 
								" fname = 'Shaun';";
						
						if (sql.equalsIgnoreCase("quit"))
							break;
						
						
						
						boolean isResultSet1 = statement1.execute(sql);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if(empDecision.equalsIgnoreCase("deny")) {
				
			}
		}
		}
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
//				employee.EmployeeFirstName = EmployeeFirstName;
//				employee.EmployeeID;
//				employee.EmployeeLastName;
//				employee.EmployeeType;
				
				
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
		
		Scanner sc = new Scanner(System.in);
		int option1 = sc.nextInt();
		
		if(option1 == 1) {
			Customer.loginCustomer();
	
		}
		else
		{
			System.out.println(option1);
			Customer.registerCustomer();
			
		}
		
	}
	
	public static void clearScreen() {  
	    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  
		}
	


}

