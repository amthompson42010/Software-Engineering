/**  
 * @author Alexander Mark Thompson
 * @title CS 315 Modify Members Class
 * @description ModifyMembers class adds, deletes, or updates members. Takes a filename 
 *              of where the member accounts are/will be stored.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ModifyMembers {
	
	ArrayList<MemberAccount> memberArray; //arraylist of member accounts
	private String filename;
	ObjectInputStream inputStr; //retrieves member data from the file
	ObjectOutputStream outputStr; //outputs member data to file
	int memberNum=100000000; //member number
	MemberAccount member;
	
	/**
	 * Contructor for the Modify Members class.
	 * @param  filename This is the name of the file that member records 
	 *                  will be read from/written to
	 */
	public ModifyMembers(String filename)
	{
		this.filename=filename;
		memberArray = new ArrayList<MemberAccount>(); //new instance of memberArray
		Scanner in = new Scanner(System.in);
		
		try { 
			FileInputStream fileIn = new FileInputStream(filename); 
			inputStr = new ObjectInputStream(fileIn);
			MemberAccount obj;
			try{
				memberArray = ((ArrayList<MemberAccount>) inputStr.readObject());
			} catch (ClassNotFoundException e1){
			} catch (EOFException e2) {
			} 
			inputStr.close();
			fileIn.close();
		} catch (IOException e2) {
		}
		for (int i=0; i< memberArray.size(); i++){ //iterates through arraylist in file
			MemberAccount acc = memberArray.get(i);
			member = new MemberAccount(acc.getName(), acc.getStreetAddress(), acc.getCity(), 
					acc.getState(), acc.getZipCode(), acc.getNumber(), acc.checkStatus());
			memberNum=acc.getNumber(); //keeps track of member number
		}
		System.out.println();
		System.out.println("Please choose an option between 1 and 3.");
		System.out.println("    To add a member, input 1");
		System.out.println("    To delete a member, input 2");
		System.out.println("    To modify a member, input 3");
		System.out.print("Enter your selection: ");
		int option = in.nextInt();
		while (option <=0 || option > 3){
			System.out.println();
			System.out.println("Invalid selection. Please choose an option between 1 and 3.");
			System.out.println("    To add a member, input 1");
			System.out.println("    To delete a member, input 2");
			System.out.println("    To modify a member, input 3");
			System.out.print("Enter your selection: ");
			option = in.nextInt();
		}
		switch(option){
		case 1:
			memberNum++;
			MemberAccount memAcc = new MemberAccount("","","","",0,0,"");
			addMember((MemberAccount) memAcc);
			break;
		case 2: 
			deleteMember();
			break;
		case 3:
			updateMember();
			break;
		}
		in.close();
	}

	/**
	 * Takes a blank member account and sets the member data from Scanner input. 
	 * Finally adds the member account to the file 		
	 * @param ma is a blank Member Account that is updated with the member data 
	 *           entered from the scanner.
	 */
	private void addMember(MemberAccount ma)
	{
		System.out.print("Enter the new member's name: ");
		Scanner in = new Scanner(System.in);
		ma.setName(in.nextLine());
		System.out.print("Enter street Address: ");
		ma.setStreet(in.nextLine());
		System.out.print("Enter city: ");
		ma.setCity(in.nextLine());
		System.out.print("Enter state: ");
		ma.setState(in.nextLine());
		System.out.print("Enter zip code: ");
		ma.setZipCode(in.nextInt());
		in.close();
		ma.setNumber(memberNum);
		System.out.printf("Member number: %d%n", ma.getNumber());
		ma.setStatus("Valid");
		System.out.printf("Member Status: %s%n", ma.checkStatus());
		memberArray.add( ma);
		System.out.printf("Member %d has been added. \n",ma.getNumber());
		//outputs memberArray back to file
		try {
			outputStr = new ObjectOutputStream(new FileOutputStream(filename));
			outputStr.writeObject(memberArray);
		}
		catch (IOException e1){
		}
		try {
			outputStr.close();
		} 
		catch (IOException e1) {
		}
	}

	/**
	 * Asks for member number to delete from system. If member found, deletes from the ArrayList of members, 
	 * and then re-writes the new ArrayList back to the file. If number is not found, will present error message 
	 * and prompt for new number.
	 */
	private void deleteMember()
	{
		System.out.print("Please enter member number of member to be deleted: ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt(); //number to be deleted
		boolean flag= false;
		for (int i=0; i< memberArray.size(); i++){ //goes through arraylist of member accounts
			MemberAccount acc = memberArray.get(i); //gets single member account
			int accNum=memberArray.get(i).getNumber(); //gets member number
			if(accNum==num) { 
				flag=true;
				System.out.format("Current member information: %n   Name: %s%n   Street Address: %s%n "
						+ "  City: %s%n   State: %s%n   ZipCode: %d%n   Provider Number: %05d%n   Status: %s%n", acc.getName(), 
						acc.getStreetAddress(), acc.getCity(), acc.getState(), acc.getZipCode(), acc.getNumber(), acc.checkStatus());
				System.out.print("Delete member? Enter 1 for Yes, 2 for No.");
				int choice = in.nextInt();
				switch(choice){
				case 1:
					memberArray.remove(acc); //removes member from list
					System.out.printf("Member %d deleted.", num);
					break;
				case 2:
					System.out.printf("Member %d not deleted. \n",num);
					break;
				}
			} 
			try { //writes updated list of members to file
				outputStr = new ObjectOutputStream(new FileOutputStream(filename));
				outputStr.writeObject(memberArray);
			}
			catch (IOException e1){
			}
		try {
			outputStr.close();
		} catch (IOException e1) {
		}
		}
		if (flag==false){
			System.out.println("Invalid member number. Please try again.");
			System.out.println();
			deleteMember();
		}
		in.close();
	}
	
	/**
	 * Asks for member number of member to be updated. Searches ArrayList for number. 
	 * If found, will ask for new data, and then write data to file. If not found, 
	 * will ask for new number.
	 */
	private void updateMember() //updates member number
	{
		System.out.print("Enter member number of member to be updated: ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		boolean flag = false;
		for (int i=0; i< memberArray.size(); i++){ //checks for membernumber that matches number that was entered
			MemberAccount acc = memberArray.get(i);
			int accNum=memberArray.get(i).getNumber();
			if(accNum==num) { //member number found
				flag=true;
				System.out.format("Current member information: %n   Name: %s%n   Street Address: %s%n "
						+ "  City: %s%n   State: %s%n   ZipCode: %d%n   Member Number: %05d%n   Status: %s%n", acc.getName(),acc.getStreetAddress(),acc.getCity(),acc.getState(),acc.getZipCode(),acc.getNumber(),acc.checkStatus());
				System.out.print("Update member's name: ");
				in = new Scanner(System.in);
				acc.setName(in.nextLine());
				System.out.print("Update member's street address: ");
				acc.setStreet(in.nextLine());
				System.out.print("Update member's city: ");
				acc.setCity(in.nextLine());
				System.out.print("Update member's state: ");
				acc.setState(in.nextLine());
				System.out.print("Update member's zip code: ");
				acc.setZipCode(in.nextInt()); 
				in.nextLine();
				System.out.print("Update member's status: ");
				acc.setStatus(in.nextLine());
				System.out.println();
				System.out.printf("Member %d updated.%n", num);
			} 
			try {
				outputStr = new ObjectOutputStream(new FileOutputStream(filename));
				outputStr.writeObject(memberArray);
			}
			catch (IOException e1){
			}
		try {
			outputStr.close();
		} catch (IOException e1) {
		}
		}
		if (flag==false){
			System.out.println("Invalid member number. Please try again.");
			System.out.println();
			updateMember();
		}
		in.close();
	}
}
