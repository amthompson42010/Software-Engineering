/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description This Class displays many of the prompts and directs the user
 *              through use of the Program.
 */

import java.util.*;
import java.io.*;

public class Terminal {

	public static Scanner scan = new Scanner(System.in);
	public static int memNum=0;
	public static int providerNum=0;
	public static String date;
	public static int serviceCode=0;
	public static String comment=null;
		
	public static void main(String args[]) 
			throws FileNotFoundException, ClassNotFoundException, IOException{
		System.out.println("To access ChocAn turn on the terminal by pressing 1 then Enter. ");
		int start = scan.nextInt();
		if(start == 1){
			turnOnTerminal();
		}
		else{
			System.out.println("That was invalid please start over.");
		}
		
		
	}
	
	/**
	 * Initialization of program and finds out what the user would like to do
	 */
	public static void turnOnTerminal() 
			throws FileNotFoundException, ClassNotFoundException, IOException{
		System.out.println("Welcome to the ChocAn System");
		System.out.println("If you are provider input 1, if you are a ChocAn Manager input 2. Then Click 'Enter'");
		int user = scan.nextInt();
		if(user == 1){
			System.out.println("Welcome provider please input your provider number and click 'Enter'?");
			providerNum = scan.nextInt();
			System.out.println("Thank you now what would you like to do?");
			System.out.println("To look at the provider directory input 1");
			System.out.println("To bill a service for a member input 2");
			System.out.println("To check if a member is active to receive healthcare input 3");
			System.out.println("Input one and then press Enter");
			int choice = scan.nextInt();
			if (choice ==1){
				System.out.println("Ok we will request the Provider Directory for you. Please Wait");
				requestProviderDirectory();
			}
			else if (choice ==2){
				billChocAn();
				
			}
			else if (choice ==3){
				receiveHealthcare();
				
			}
			else{
				System.out.println("That input was invalid please start over");
			}
		}
		else if(user == 2){
			System.out.println("Welcome manager what would you like to do?");
			System.out.println("To run weekly reports input 1");
			System.out.println("To modify an account input 2");
			int mangChoice = scan.nextInt();
			if (mangChoice ==1){
				runReports();
			}
			else if (mangChoice ==2){
				modifyMembersProvidersServices();
				
			}
			else{
				System.out.println("That input was invalid please start over");
			}
			
		}
		else{
			System.out.println("That is an invalid input, please start over");
		}
		
	}
	
	/**
	 * This requests the provider Directory class and displays the data in the directory.
	 */
	public static void requestProviderDirectory() 
			throws FileNotFoundException, ClassNotFoundException, IOException{
		ProviderDirectory.display();
		System.out.println("To continue to Bill ChocAn input 1, to end the terminal session input 2.");
		int choice = scan.nextInt();
		if(choice ==1){
			billChocAn();
		}
		else{
			System.exit(0);
		}
	}
	
	/**
	 * This calls the modification classes so acccounts can be modified.
	 */
	public static void modifyMembersProvidersServices(){
		Modification edit = new Modification();	
	}
	
	/**
	 * This calls the reports class and the methods to perform such things
	 */
	public static void runReports() 
			throws FileNotFoundException, ClassNotFoundException, IOException{
		System.out.println("What type of report would you like to run? Provider Report (1)? Member Report(2)? Or Summary Report(3)? Input an option and press enter.");
		//initialize reports class
		Reports report = new Reports();
		int reportNum = scan.nextInt();
		if(reportNum == 1){
			report.runProviderReport();
		}
		else if(reportNum == 2){
			report.runMemberReport();
		}
		else if(reportNum == 3){
			report.runSummaryReport();
		}
		else{
			System.out.println("That was an invalid input please start over.");
		}
		
	}
	
	/**
	 * In order to bill ChocAn a service must be provided and data entered, methods for both these are called.
	 */
	public static void billChocAn() 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		receiveHealthcare();
		enterServiceData();
		Validation.verifyServiceData(date, memNum, providerNum, serviceCode, comment);
		
	}
		
	/**
	 * This method collects and confirms service data which is needed for billing and service reports.
	 */
	public static void enterServiceData() throws FileNotFoundException, ClassNotFoundException, IOException{
		scan.nextLine();
		System.out.println("Please input the date in the format MM/DD/YYYY and press 'Enter'.");
		date = scan.nextLine();
		if(date != null){
			
		
		System.out.println("Input a service code. If you need to view the provider directory first input 1.");
		int input = scan.nextInt();
		if(input == 1){
			requestProviderDirectory();
		}
		else{
			serviceCode = input;
		}
		
		String verified = Validation.isValidServiceCode(serviceCode);
		System.out.println("Is" + verified + " the correct service? If yes input 1 else input 2 and try again" );
		int validate = scan.nextInt();
		if(validate == 1){
			scan.nextLine();
			System.out.println("Great. Would you like to add any comments for this report? Input 1 for no otherwise type your comment." );
			comment = scan.nextLine();
			if(comment.equals("1")){
				comment = null;
			}
			
		}
		else if(validate == 2){
			enterServiceData();
			return;
		}
		else{
			System.out.println("That was an invalid input please start your session over." );
			System.exit(0);
		}
		BillingData.writeServiceReport(date, memNum, providerNum, serviceCode, comment);
		
		}
		else{
			System.out.println("That was not a valid input");
		}
	}
	
	/**
	 * This aquires the member number of the member requiring services
	 */
	public static void receiveHealthcare() 
			throws FileNotFoundException, ClassNotFoundException, IOException{
		System.out.println("Please input a member number.");
		memNum = scan.nextInt();
		boolean active = Validation.isActiveMember(memNum);
		boolean valid = Validation.isValidMemberNumber(memNum);
		if(active == true && valid == true){
			System.out.println("This member is both valid and active please proceed with Healthcare Service.");
		}
		else{
			System.out.println("This member is either no longer valid or active. Please start over.");
		}
	}
}
