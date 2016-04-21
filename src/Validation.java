
/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description This Class validates member numbers, active membership and service codes
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {
	public static void main(String args[]){
		
	}
	
	/**
	 * Checks to see if the member is active
	 * @param  memNum                 Member's number
	 * @return                        True or false depending on active state
	 */
	public static boolean isActiveMember(int memNum) 
			throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream inStr =
				new ObjectInputStream(new FileInputStream("MemberAccounts.dat"));
		ArrayList<MemberAccount> members = (ArrayList<MemberAccount>) (inStr.readObject());
		inStr.close();
		for(MemberAccount member :members){
			if(memNum == member.getNumber()){
				if(member.checkStatus().equals("Valid")){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks to see if the Service Code is valid.
	 * @param  serviceCode            the service code
	 * @return                        the service description
	 */
	public static String isValidServiceCode(int serviceCode) throws FileNotFoundException, IOException, ClassNotFoundException{
		String name = null;
		ObjectInputStream inStr =
				new ObjectInputStream(new FileInputStream("ProviderDirectory.dat"));
		ArrayList<Service> services = (ArrayList<Service>) (inStr.readObject());
		inStr.close();
		for(Service service : services){
			if(serviceCode == service.getCode()){
				name = service.getDescription();
				break;
			}
		}
		
		return name;
	}

	/**
	 * Checks to see if the member's number is valid
	 * @param  memNum                 member's number
	 * @return                        true or false depending on the valid status
	 */
	public static boolean isValidMemberNumber(int memNum) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream inStr =
				new ObjectInputStream(new FileInputStream("MemberAccounts.dat"));
		ArrayList<MemberAccount> members = (ArrayList<MemberAccount>) (inStr.readObject());
		inStr.close();
		for(MemberAccount member :members){
			if(memNum == member.getNumber()){
				return true;				
			}
		}
		return false;
	}

	
	/**
	 * Checks to see if the service data is valid.
	 * @param  date                   the date
	 * @param  memNum                 the member's number
	 * @param  providerNum            the provider's number
	 * @param  serviceCode            the service code
	 * @param  comment                comment for the data
	 * @return                        true or false depending on if the data is verified.
	 */
	public static boolean verifyServiceData(String date, int memNum, int providerNum, int serviceCode, String comment ) throws FileNotFoundException, ClassNotFoundException, IOException{
	//display data and have user verify
		System.out.println("Date: " + date);
		System.out.println("Member Number: " + memNum);
		System.out.println("Provider Number: " + providerNum);
		System.out.println("Service Code: " + serviceCode);
		System.out.println("Comments: " + comment);
		System.out.println("If this information is correct input one otherwise input 2 and re-enter your data. ");
		Scanner scan = new Scanner(System.in);
		int validate = scan.nextInt();
		if(validate == 1){
			return true;
		}
		else if(validate == 2){
			Terminal.billChocAn();
			return false;
		}
		else{
			return false;
		}
		
	}
	


}
