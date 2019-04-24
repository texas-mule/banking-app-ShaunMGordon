package app;

import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.InputSource;

import app.Customer;
import app.Employee;

public class Bank {
	
	String employeeID = null;
	String employeeType = null;
	static String[] employeeName = null;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String[] getEmployeeName() {
		return employeeName;
	}

	public static void setEmployeeName(String[] employeeName) {
		Bank.employeeName = employeeName;
	}

	public static void main(String[] args) {	

		
// Command Line Arguments
//		String[] inputs = new String[args.length-1];
//		for(int i=0;i<args.length-1;i++)
//			inputs[i]=args[i];
		
//		Print Welcome Message
		int x= 0;
		printWelcome();
		
//		Implement Method to Decipher Between Employee and Customer
		while (x == 0) {
		x = bankDecider(x);
		
		//System.out.println(employeeName[0] + " " + employeeName[1]);
	}}

	public static void printWelcome() {
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
				x = 1;
			}
			else if(decider.equalsIgnoreCase(customerDecider)){
// If no, connect to Customer Version
				customerWelcome();
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


