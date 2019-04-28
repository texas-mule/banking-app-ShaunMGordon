package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Customer extends Bank{
	static String firstName;
	static String lastName;
	static String email;
//	static String password;
	
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
		String p4ssword;
		
		while (b == 0) {
			System.out.println("Password: ");
			p4ssword = infoScanner.nextLine();
			
			System.out.println("Confirm Password: ");
			String passwordConfirm = infoScanner.nextLine();
			if(p4ssword.equalsIgnoreCase(passwordConfirm)) {
				try {
					Customer.printToFile(p4ssword);
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
		
//		int i=0;
//		while (i<6) {
		
		int isResultSet = statement.executeUpdate(customer);
		
//		i++
		}
//		if (isResultSet) {
//			ResultSet resultSet = statement.getResultSet();
//			ResultSetMetaData rsmd = resultSet.getMetaData();			
//			
//			while (resultSet.next()) {
//				EmployeeID = resultSet.getString(1);
//				EmployeeFirstName = resultSet.getString(2);
//				EmployeeLastName = resultSet.getString(3);
//				EmployeeType = resultSet.getString(4);
////				System.out.println(EmployeeID);
////				System.out.println(EmployeeFirstName);
////				System.out.println(EmployeeLastName);
////				System.out.println(EmployeeType);
//				
//				for (int i = 1;i<= rsmd.getColumnCount(); i++) {
//					//System.out.print(resultSet.getString(i) + "\t");
//				
//				loginLooper = 1;
//				}
//				
//				System.out.println();
//				clearScreen();
//				System.out.println("Welcome Back " + EmployeeFirstName + " " + EmployeeLastName);
//				System.out.println("Current Permissions: " + EmployeeType );
//			}
			//resultSet.close();
//		} else {
//			System.out.println(statement.getUpdateCount() + "rows affected");
//		}
 catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		//statement.close();
		
	//}
		
		System.out.println("Thank You, Please Allow 24 Hours For Approval Processing");
}
	}//catch (SQLException ex) {
	//ex.printStackTrace();
//}
		
		
	//}

	private static void printToFile(String text) throws IOException {
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));
	    writer.append(' '); 
	    writer.append(text);
	     
	    writer.close();
	}


//	public static String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
	//}

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
		
		//try {
			//loginLooper = searchForName(loginLooper);
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
							firstName = resultSet.getString(1);
							lastName = resultSet.getString(2);
							email = resultSet.getString(4);
							String status = resultSet.getString(7);
							
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
								
		
										//System.out.print("sql> ");
										sql = "select * from customers where email =('" + scanner.nextLine()+"')";
										//statusL = "select status from customers where status =('" + scanner.nextLine()+"')";
										
										//select * from customers where email =('Shaun_Gordon@baylor.edu')
										if (sql.equalsIgnoreCase("quit"))
											break;
										
										isResultSet = statement.execute(sql);
										resultSet = statement.getResultSet();
										rsmd = resultSet.getMetaData();
										
										if (isResultSet) {			
										
											while (resultSet.next()) {
												firstName = resultSet.getString(1);
												lastName = resultSet.getString(2);
												email = resultSet.getString(4);
												status = resultSet.getString(7);
											}
								loginLooper++;
								
							}
							//EmployeeType = resultSet.getString(4);
//							System.out.println(EmployeeID);
//							System.out.println(EmployeeFirstName);
//							System.out.println(EmployeeLastName);
//							System.out.println(EmployeeType);
							
//							for (int i = 1;i<= rsmd.getColumnCount(); i++) {
//								//System.out.print(resultSet.getString(i) + "\t");
//							 
//							loginLooper = 1;
//							}
							
							System.out.println();
							clearScreen();
							System.out.println("Welcome Back " + firstName + " " + lastName + " "+ status);
							//System.out.println("Current Permissions: " + EmployeeType );
						}
						//resultSet.close();
					} 
					
					//statement.close();
				//}
						}
					} catch (SQLException ex) {
				ex.printStackTrace();
						}
	}
}
}
			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//			FILE SYSTEM
			
//		if( loginLooper == 0) {
//			System.out.println("Password:");
//			 try {
//				loginLooper = searchForPassword(loginLooper);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////		 String passWord = loginScanner.nextLine();
////		 loginScanner.close();
//		}
	
//	FILE SYSTEM
	
//	public static int searchForName(int x) throws FileNotFoundException {
//		// New File
//		    File file = new File("Customers.txt");
//		    
//		//New Scanner For Input
//		    Scanner userInput = new Scanner(System.in);
//		
//		// New Scanner For File
//		    Scanner input = new Scanner(file);
//		    
//		// Sets Name Equal to Console Input
//		    String name = userInput.nextLine();
//		    String userName = " ";
//		    String[] iName;
//		    
//		// Sets iName Equal to File Input
//		    while (input.hasNextLine() && name.equalsIgnoreCase(userName) != true) {
//		    	String customerInfo;
//		    	customerInfo = input.nextLine();
//		    	iName = customerInfo.split(" ");
//		    	userName = iName[5];
//		    	//System.out.println(iName);
//		    }
//		    
//		    if(name.equalsIgnoreCase(userName)) {
//		    	x = 0;
//		    }
//		    else {
//		    	System.out.println("Not Found");
//		    	x++;
//		    	
//		    }
//		   
//		    //System.out.println(name);
//		    
//		    return x;
//		}
	
	
// FILE SYSTEM
	
	
	/*
	
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
		    	uPassWord = iPassword[5];
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
*/
	
