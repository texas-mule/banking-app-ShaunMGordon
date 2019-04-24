/**
 * 
 */
package SGBanking;

import java.util.Scanner;

/**
 * @author shaunmgordon
 *
 */
public class SGBanking {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		SGBanking sgbanking = new SGBanking();
		
		System.out.println("Welcome to SG Banking");
		System.out.println("Please select from the following: "
				+ "\n 1. Returning Customer \n "
				+ "2. New Customer");
		
		Scanner scan = new Scanner(System.in);
		
		int option = scan.nextInt();
		
		if(option==1) {
			System.out.println("Welcome Back");
			sgbanking.login();
		}
		else if(option==2) {
			System.out.println("Let's Get You Started");
			sgbanking.register();
		}
		else
			{
				System.out.println("Goodbye");
			}

	}
	
	public void login() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Username:");
		String userName = scan.nextLine();
		
		System.out.println("Password:");
		String passWord = scan.nextLine();
	}
	
	public void register() {
		Scanner scan = new Scanner(System.in);
		System.out.println("First Name:");
		String firstName = scan.nextLine();
		
		System.out.println("Last Name:");
		String lastName = scan.nextLine();
	}
	
	
}

