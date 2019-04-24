package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Customer extends Bank{
	static String firstName;
	static String lastName;
	
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
		try {
			Customer.printToFile(firstName);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
// Gather Last Name
		System.out.println("Last Name: ");
		String lastName = infoScanner.nextLine();
		try {
			Customer.printToFile(lastName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

// Gather Birth Date
		System.out.println("Birth Date (mmddyy): ");
		String birthDay = infoScanner.nextLine();
		
		try {
			Customer.printToFile(birthDay);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
// Gather SSN
		System.out.println("Social Security Number (xxxxxxxxx): ");
		String socialNumber = infoScanner.nextLine();
		try {
			Customer.printToFile(socialNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Scanner logInfoScanner = new Scanner(System.in);
// Gather Email
		int a = 0;
		
		while (a == 0) {
		System.out.println("Email: ");
		String email = logInfoScanner.nextLine();
		
		System.out.println("Confirm Email: ");
		String emailConfirm = logInfoScanner.nextLine();
		if(email.equalsIgnoreCase(emailConfirm)) {
			try {
				Customer.printToFile(email);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a = 1;
		}
		else
			System.out.println("Emails Do Not Match");
		}
		
// Gather Password
		int b = 0;
		
		while (b == 0) {
			System.out.println("Password: ");
			String password = logInfoScanner.nextLine();
			
			System.out.println("Confirm Password: ");
			String passwordConfirm = logInfoScanner.nextLine();
			if(password.equalsIgnoreCase(passwordConfirm)) {
				try {
					Customer.printToFile(password);
					BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));
					writer.newLine();
					writer.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				b = 1;
			}
			else
				System.out.println("Passwords Do Not Match");
		
		}
		Bank.clearScreen();
		System.out.println("Thank You, Please Allow 24 Hours For Approval Processing");
		
	}

	private static void printToFile(String text) throws IOException {
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));
	    writer.append(' '); 
	    writer.append(text);
	     
	    writer.close();
	}

	public static String getFirstName() {
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

	public static void loginCustomer() {
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
		//String userName = loginScanner.nextLine();
		
		try {
			loginLooper = searchForName(loginLooper);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( loginLooper == 0) {
			System.out.println("Password:");
			 try {
				loginLooper = searchForPassword(loginLooper);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		 String passWord = loginScanner.nextLine();
//		 loginScanner.close();
		}
		}
		
	}
	
	public static int searchForName(int x) throws FileNotFoundException {
		// New File
		    File file = new File("Customers.txt");
		    
		//New Scanner For Input
		    Scanner userInput = new Scanner(System.in);
		
		// New Scanner For File
		    Scanner input = new Scanner(file);
		    
		// Sets Name Equal to Console Input
		    String name = userInput.nextLine();
		    String userName = " ";
		    String[] iName;
		    
		// Sets iName Equal to File Input
		    while (input.hasNextLine() && name.equalsIgnoreCase(userName) != true) {
		    	String customerInfo;
		    	customerInfo = input.nextLine();
		    	iName = customerInfo.split(" ");
		    	userName = iName[5];
		    	//System.out.println(iName);
		    }
		    
		    if(name.equalsIgnoreCase(userName)) {
		    	x = 0;
		    }
		    else {
		    	System.out.println("Not Found");
		    	x++;
		    	
		    }
		   
		    //System.out.println(name);
		    
		    return x;
		}
	
	public static int searchForPassword(int x) throws FileNotFoundException {
		// New File
		    File file = new File("Customers.txt");
		    
		//New Scanner For Input
		    Scanner userInput = new Scanner(System.in);
		
		// New Scanner For File
		    Scanner input = new Scanner(file);
		    
		// Sets Name Equal to Console Input
		    String passWord = userInput.nextLine();
		    String uPassWord = " ";
		    String[] iPassword;
		    
		// Sets iName Equal to File Input
		    while (input.hasNextLine() && passWord.equalsIgnoreCase(uPassWord) != true) {
		    	String customerInfo;
		    	customerInfo = input.nextLine();
		    	iPassword = customerInfo.split(" ");
		    	uPassWord = iPassword[6];
		    	//System.out.println(iName);
		    }
		    
		    if(passWord.equalsIgnoreCase(uPassWord)) {
		    	clearScreen();
		    	System.out.println("Welcome");
		    	x = 1;
		    }
		    else {
		    	System.out.println("Not Found");
		    	x = 2;
		    	
		    }
		   
		    //System.out.println(name);
		    
		    return x;
		}
	
	public static void whenWriteStringUsingBufferedWritter_thenCorrect() throws IOException {
			    String str = "Customer Database";
			    BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt"));
			    writer.write(str);
			    writer.close();
			}

}
