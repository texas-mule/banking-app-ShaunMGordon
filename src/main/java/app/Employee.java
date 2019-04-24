package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

public class Employee extends Bank {
	
		static int loginLooper;
		static int attemptCounter;
		static int i= -1;
		
		
//	public int getLoginLooper() {
//			return loginLooper;
//		}
//
//	public static void setLoginLooper(int loginLooper) {
//			Employee.loginLooper = loginLooper;
//			System.out.println(loginLooper);
//		}
//
//	public int getAttemptCounter() {
//			return attemptCounter;
//		}
//
//	public static void setAttemptCounter(int attemptCounter) {
//			Employee.attemptCounter = attemptCounter;
//		}

	public static void employeeWelcome() {
		loginLooper=0;
		attemptCounter=0;
	
		System.out.println("Please Enter Employee ID");
		System.out.println("empID:");
//		Scanner employeeID = new Scanner(System.in);
//		int id = employeeID.nextInt();
		
	while (loginLooper==0) {
		i++;
		
				if(i>0 && i <3) {
					clearScreen();
					System.out.println("That ID Was Not Found\n");
					System.out.println("Attempts Remaining: " + (3-i));
					System.out.println("empID:");
					
				}
				else if(i>3)
					break;
				
			//System.out.println("empID:");
			//String userName = loginScanner.nextLine();
			
			try {
				loginLooper = searchForEmployeeID(loginLooper);
				//System.out.println(loginLooper);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	
			//System.out.println("Welcome Back" + empName);
			
		}
	
	public static int searchForEmployeeID(int x) throws FileNotFoundException {
		// New File
		    File file = new File("Employees.txt");
		    
		//New Scanner For Input
		    Scanner userInput = new Scanner(System.in);
		
		// New Scanner For File
		    Scanner input = new Scanner(file);
		    
		// Sets Name Equal to Console Input
		    String name = userInput.nextLine();
		    String userName = " ";
		    String[] iName ;
		    String[] empName = {"",""};
		    
		// Sets iName Equal to File Input
		    while (input.hasNextLine() && name.equalsIgnoreCase(userName) != true) {
		    	String customerInfo;
		    	customerInfo = input.nextLine();
		    	iName = customerInfo.split(" ");
		    	userName = iName[0];
		    	empName[0]= iName[2];
		    	empName[1] = iName[3];
		   
//		    	System.out.println(userName);
		    	//System.out.println(iName);
		    }
		    
		    if(name.equalsIgnoreCase(userName)) {
		    	//System.out.println(empName);
		    	clearScreen();
		    	System.out.println("Welcome Back " + empName[0] + " " + empName[1]);
		    	String[] employeeDisplayName = empName;
		    	//System.out.println(employeeDisplayName);
		    	Bank.setEmployeeName(employeeDisplayName);
		    	x = 1;
		    }
		    else if(i==2) {
		    	clearScreen();
		    	System.out.println("Final Attempt");
		    	System.out.println("empID:");
		    	x=0;
		    	
		    }
		    else if (i>2) {
		    	clearScreen();
		    	System.out.println("We Could Not Verify That ID Number\nGoodbye");
		    	x=0;
		    } 
		   
		    //System.out.println(name);
		    //System.out.println(x);
		    return x;
		}

}
