/**  
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description Class to add/delete/modify providers and the list of provider accounts. Takes a filename 
 * of where the provider accounts are/will be stored.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ModifyProviders {
	ArrayList<ProviderAccount> providerArray; //arraylist of provider accounts
	private String filename;
	private int option=0;
	ObjectInputStream inputStr;
	ObjectOutputStream outputStr;
	int providerNum=500000000;
	ProviderAccount provider;
	
	/**
	 * Constructor for the Modify Providers class.
	 * @param filename The name of the file where the list of providers is stored
	 */
	public ModifyProviders(String filename)
	{
		this.filename=filename;
		providerArray = new ArrayList<ProviderAccount>();
		Scanner in = new Scanner(System.in);
		try {
			
			FileInputStream fileIn = new FileInputStream(filename);
			inputStr = new ObjectInputStream(fileIn);
			ProviderAccount obj;
			try{
				providerArray = ((ArrayList<ProviderAccount>) inputStr.readObject());
			} catch (ClassNotFoundException e1){
			} catch (EOFException e2) {
			} 
			inputStr.close();
			fileIn.close();
		} catch (IOException e2) {
		}
		for (int i=0; i< providerArray.size(); i++){
			ProviderAccount acc = providerArray.get(i);
			provider = new ProviderAccount(acc.getName(), acc.getStreetAddress(), acc.getCity(), 
					acc.getState(), acc.getZipCode(), acc.getNumber());
			providerNum=acc.getNumber();
		}
		System.out.println();
		System.out.println("Please choose an option between 1 and 3.");
		System.out.println("    To add a provider, input 1");
		System.out.println("    To delete a provider, input 2");
		System.out.println("    To modify a provider, input 3");
		System.out.print("Please enter your selection: ");
		option = in.nextInt();
		while (option <=0 || option > 3){ //makes sure that option is between 1 and 3
			System.out.println();
			System.out.println("Please choose an option between 1 and 3.");
			System.out.println("    To add a member, input 1");
			System.out.println("    To delete a member, input 2");
			System.out.println("    To modify a member, input 3");
			System.out.print("Enter your selection: ");
			option = in.nextInt();
		}
		switch(option){
		case 1:
			providerNum++;
			ProviderAccount provAcc = new ProviderAccount("","","","",0,0);
			addProvider((ProviderAccount) provAcc);
			break;
		case 2: 
			deleteProvider();
			break;
		case 3:
			updateProvider();
			break;
		}
		in.close();
	}

	/**
	 * Takes a blank provider account and sets the provider data from Scanner input. 
	 * Finally adds the provider account to the file 
	 * @param pa is a blank Provider Account that is updated with the provider data 
	 *           entered from the scanner.
	 */
	private void addProvider(ProviderAccount pa)
	{
		System.out.print("Name of new provider: ");
		Scanner in = new Scanner(System.in);
		pa.setName(in.nextLine());
		System.out.print("Street Address: ");
		pa.setStreet(in.nextLine());
		System.out.print("City: ");
		pa.setCity(in.nextLine());
		System.out.print("State: ");
		pa.setState(in.nextLine());
		System.out.print("ZipCode: ");
		pa.setZipCode(in.nextInt());
		in.close();
		pa.setNumber(providerNum);
		providerArray.add( pa);
		System.out.printf("Provider number %d has been added. \n",pa.getNumber());
		try {
			outputStr = new ObjectOutputStream(new FileOutputStream(filename));
			outputStr.writeObject(providerArray);
		}
		catch (IOException e1){
		}
	try {
		outputStr.close();
	} catch (IOException e1) {
	}
	}

	/**
	 * Asks for provider number to delete from system. If provider found, deletes from the ArrayList of providers, 
	 * and then re-writes the new ArrayList back to the file. If number is not found, will present error message 
	 * and prompt for new number. 
	 */
	private void deleteProvider()
	{
		System.out.print("Enter provider number of provider to be deleted: ");
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		int num = in.nextInt(); //number to be deleted
		for (int i=0; i< providerArray.size(); i++){ //goes through arraylist of provider accounts
			ProviderAccount acc = providerArray.get(i); //gets single provider account
			int accNum=providerArray.get(i).getNumber(); //gets provider number
			if(accNum==num) { 
				flag = true;
				System.out.format("Current provider information: %n   Name: %s%n   Street Address: %s%n "
						+ "  City: %s%n   State: %s%n   ZipCode: %d%n   Provider Number: %05d%n", acc.getName(), 
						acc.getStreetAddress(), acc.getCity(), acc.getState(), acc.getZipCode(), acc.getNumber());
				System.out.print("Delete provider? Enter 1 for Yes, 2 for No.");
				int choice = in.nextInt();
				switch(choice){
				case 1:
					providerArray.remove(acc); //removes provider from list
					System.out.printf("Provider %d deleted.", num);
					break;
				case 2:
					System.out.printf("Provider %d not deleted. \n",num);
					break;
				}
			} 
			try { //writes updated list of providers to file
				outputStr = new ObjectOutputStream(new FileOutputStream(filename));
				outputStr.writeObject(providerArray);
			}
			catch (IOException e1){
			}
		try {
			outputStr.close();
		} catch (IOException e1) {
		}
		}
		if (flag==false){
			System.out.println("Invalid provider number. Please try again.");
			System.out.println();
			deleteProvider();
		}
		in.close();
	}
	
	/**
	 * Asks for provider number of provider to be updated. Searches ArrayList for number. 
	 * If found, will ask for new data, and then write data to file. If not found, 
	 * will ask for new number.
	 */
	private void updateProvider()
	{

		System.out.print("Enter provider number of provider to be updated: ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		boolean flag = false;
		for (int i=0; i< providerArray.size(); i++){
			ProviderAccount acc = providerArray.get(i);
			int accNum=providerArray.get(i).getNumber();
			if(accNum==num) { //number was found
				flag = true;
				System.out.format("Current provider information: %n   Name: %s%n   Street Address: %s%n "
						+ "  City: %s%n   State: %s%n   ZipCode: %d%n   Provider Number: %05d%n", acc.getName(), 
						acc.getStreetAddress(), acc.getCity(), acc.getState(), acc.getZipCode(), acc.getNumber());
				System.out.print("Update Name: ");
				in = new Scanner(System.in);
				acc.setName(in.nextLine());
				System.out.print("Update Street Address: ");
				acc.setStreet(in.nextLine());
				System.out.print("Update City: ");
				acc.setCity(in.nextLine());
				System.out.print("Update State: ");
				acc.setState(in.nextLine());
				System.out.print("Update ZipCode: ");
				acc.setZipCode(in.nextInt()); 
				System.out.println();
				System.out.printf("Provider %d updated.%n", num);
			} 
			try {
				outputStr = new ObjectOutputStream(new FileOutputStream(filename));
				outputStr.writeObject(providerArray);
			}
			catch (IOException e1){
			}
		try {
			outputStr.close();
		} catch (IOException e1) {
		}
		}
		if (flag==false){
			System.out.println("Invalid provider number. Please try again.");
			System.out.println();
			updateProvider();
		}
		in.close();
	}
}