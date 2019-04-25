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

public class Bank {

	static String EmployeeID = null;
	static String EmployeeFirstName;
	static String EmployeeLastName;
	static String EmployeeType;
	
	
	
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
		String AllowedAction = scanner.nextLine();
		
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
	    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  
		}
	


}

