package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employee extends Bank {
	
		static int loginLooper;
		static int attemptCounter;
		static int i= -1;

	public static void employeeWelcome() {
		loginLooper=0;
		attemptCounter=0;
		
//		String EmployeeID = null;
//		String EmployeeFirstName;
//		String EmployeeLastName;
//		String EmployeeType;
	
		System.out.println("Please Enter Employee ID");
		System.out.println("empID:");
//		Scanner employeeID = new Scanner(System.in);
//		int id = employeeID.nextInt();
		
	while (loginLooper==0) {
		i++;
		
		// i>0 && i <3
				if(i>0 && EmployeeID == null && i<3) {
					main_clearScreen();
					System.out.println("That ID Was Not Found\n");
					System.out.println("Attempts Remaining: " + (3-i));
					System.out.println(i);
					System.out.println("empID:");
					
					
				}
				else if(i==3) {
					main_clearScreen();
					System.out.println("Too Many Failed Attempts");
					System.out.println("Goodbye");
					break;
				}
			//System.out.println("empID:");
			//String userName = loginScanner.nextLine();
			
			//loginLooper = searchForEmployeeID(loginLooper);
				
				String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
				String username = "postgres";
				String password = "Safety48@@";
				String sql;
				
				try (Connection connection = DriverManager.getConnection(url, username, password)){
					Scanner scanner = new Scanner(System.in);
					
					//while(true) {
						Statement statement = connection.createStatement();
						//System.out.print("sql> ");
						sql = "select * from employees where empid = " + scanner.nextInt();
						if (sql.equalsIgnoreCase("quit"))
							break;
						
						boolean isResultSet = statement.execute(sql);
						
						if (isResultSet) {
							ResultSet resultSet = statement.getResultSet();
							ResultSetMetaData rsmd = resultSet.getMetaData();			
							
							while (resultSet.next()) {
								EmployeeID = resultSet.getString(1);
								EmployeeFirstName = resultSet.getString(2);
								EmployeeLastName = resultSet.getString(3);
								EmployeeType = resultSet.getString(4);
								
								System.out.println();
								main_clearScreen();
								displayEmployeeWelcomeMessage();
								loginLooper = 1;
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
				
			}
			
		}

	public static void displayEmployeeWelcomeMessage() {
		System.out.println("Welcome Back " + EmployeeFirstName + " " + EmployeeLastName);
		System.out.println("Current Permissions: " + EmployeeType );
	}

}



