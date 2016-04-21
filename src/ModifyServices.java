/** 
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @Description ModifyServices class adds, deletes, or updates services. Takes a filename 
 * of where the services are/will be stored.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ModifyServices {
	ArrayList<Service> serviceArray;
	ObjectInputStream inputStr; //retrieves service data
	ObjectOutputStream outputStr; //outputs service data to file
	private String filename;
	int serviceCode=700000;
	Service service;
	
	/**
	 * Constructor for the Modify Services class.
	 * @param  {String} filename the filename to be read in
	 */
	public ModifyServices(String filename)
	{
		this.filename=filename;
		serviceArray = new ArrayList<Service>();
		Scanner in = new Scanner(System.in);
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			
			inputStr = new ObjectInputStream(fileIn);
			Service obj;
			try{
				serviceArray = ((ArrayList<Service>) inputStr.readObject());
			} catch (ClassNotFoundException e1){
				e1.printStackTrace();
			} catch (EOFException e2) {
			} 
			inputStr.close();
			fileIn.close();
		} catch (IOException e2) {
		}
		for (int i=0; i< serviceArray.size(); i++){
			Service serv = serviceArray.get(i);
			service = new Service(serv.getCode(), serv.getDescription(), serv.getFee());
			serviceCode=serv.getCode();
		}
		System.out.println();
		System.out.println("Please choose an option between 1 and 3");
		System.out.println("    To add a service, input 1");
		System.out.println("    To delete a service, input 2");
		System.out.println("    To modify a service, input 3");
		System.out.print("Please enter your selection: ");
		int option = in.nextInt();
		while (option <=0 || option > 3){
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
			serviceCode++;
			Service oneServ = new Service(0,"","");
			addService((Service) oneServ);
			break;
		case 2: 
			deleteService();
			break;
		case 3:
			updateService();
			break;
		}
		in.close();
	}

	/**
	 * Takes a blank service entry and sets the service data from Scanner input.
	 * Then it adds the service to the file.
	 * 
	 * @param {Service} oneServ This is a blank service entry that is updated with
	 *                          the service data entered from the scanner's input.
	 */
	private void addService(Service oneServ)
	{
		oneServ.setCode(serviceCode);
		System.out.printf("Service Code: %d%n", oneServ.getCode());
		System.out.print("Enter Description: ");
		Scanner in = new Scanner(System.in);
		oneServ.setDescription(in.nextLine());
		System.out.print("Enter fee: ");
		oneServ.setFee(in.nextLine());
		in.close();
		serviceArray.add( oneServ);
		System.out.printf("Service %d has been added ", oneServ.getCode());

		try {
			outputStr = new ObjectOutputStream(new FileOutputStream(filename));
			outputStr.writeObject(serviceArray);
		}
		catch (IOException e1){
			e1.printStackTrace();
		}
		try {
			outputStr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Asks for service number to delete from system. If service found, deletes from the ArrayList of services, 
	 * and then re-writes the new ArrayList back to the file. If number is not found, will present error message 
	 * and prompt for new number.
	 */
	private void deleteService()
	{
		System.out.print("Enter service number of service to be deleted: ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt(); //number to be deleted
		boolean flag = false;
		for (int i=0; i< serviceArray.size(); i++){ //goes through arraylist of services
			Service serv = serviceArray.get(i); //gets single service 
			int serviceCode=serviceArray.get(i).getCode(); //gets service number
			if(serviceCode==num) {
				flag = true;
				System.out.format("Current service information: %n   Service code: %d%n   Description: %s%n   Fee: %s%n "
						, serv.getCode(), serv.getDescription(), serv.getFee()); 
				System.out.print("Delete service? Enter 1 for Yes, 2 for No.");
				int choice = in.nextInt();
				switch(choice){
				case 1:
					serviceArray.remove(serv); //removes provider from list
					System.out.printf("Service %d deleted.", num);
					break;
				case 2:
					System.out.printf("Service %d not deleted. \n",num);
					break;
				}
			} 
			try { //writes updated list of services to file
				outputStr = new ObjectOutputStream(new FileOutputStream(filename));
				outputStr.writeObject(serviceArray);
			}
			catch (IOException e1){
				e1.printStackTrace();
			}
		try {
			outputStr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		if (flag==false){
			System.out.println("Invalid service code. Please try again.");
			System.out.println();
			deleteService();
		}
		in.close();
	}
	
	/**
	 * Asks for service number of service to be updated. Searches ArrayList for number. 
	 * If found, will ask for new data, and then write data to file. If not found, 
	 * will ask for new number.
	 */
	private void updateService()
	{
		System.out.print("Enter service number of service to be updated: ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		boolean flag = false;
		for (int i=0; i< serviceArray.size(); i++){
			Service serv = serviceArray.get(i);
			int serviceCode=serviceArray.get(i).getCode();
			if(serviceCode==num) {
				flag = true;
				System.out.format("Current service information: %n   Service Code: %d%n   Description: %s%n "
						+ "  Fee: %s%n", serv.getCode(),serv.getDescription(),serv.getFee());
				System.out.print("Update Description: ");
				in = new Scanner(System.in);
				serv.setDescription(in.nextLine());
				System.out.print("Update Fee: ");
				serv.setFee(in.nextLine());
				System.out.println();
				System.out.printf("Service %d updated.%n", num);
			} 
			try {
				outputStr = new ObjectOutputStream(new FileOutputStream(filename));
				outputStr.writeObject(serviceArray);
			}
			catch (IOException e1){
				e1.printStackTrace();
			}
		try {
			outputStr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		if (flag==false){
			System.out.println("Invalid provider number. Please try again.");
			System.out.println();
			updateService();
		}
		in.close();
	}
}
