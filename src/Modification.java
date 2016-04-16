
/** 
 * @author Alexander Mark Thompson
 * @title CS 315 Modification Class
 * @description Modification class gives command line options to select either 
 *              members, providers, or services list to modify
 */
import java.util.Scanner;

public class Modification 
{
	/**
	 * Constructor for the Modification class.
	 */
	public Modification() 
	{
		Scanner in = new Scanner(System.in); 
		System.out.println("Please make a selection: "); 
		System.out.println("    To modify members, input 1");
		System.out.println("    To modify providers, input 2");
		System.out.println("    To modify services, input 3");
		System.out.print("Enter your selection: ");
		int option = in.nextInt(); //reads input from console
		while (option <=0 || option > 3){ //makes sure that option is between 1 and 3
			System.out.println();
			System.out.println("Please choose an option between 1 and 3.");
			System.out.println("	To modify members, input 1");
			System.out.println("	To modify providers, input 2");
			System.out.println("    To to modify services, input 3");
			System.out.print("Enter your selection: ");
			option = in.nextInt();
		}
		switch(option){ //calls class based on option selected
		case 1: //calls ModifyMembers class
			ModifyMembers members = new ModifyMembers("MemberAccounts.dat");
			break;
		case 2: //calls ModifyProviders class
			ModifyProviders providers = new ModifyProviders("ProviderAccounts.dat");
			break;
		case 3: //calls ModifyServices class
			ModifyServices services = new ModifyServices("ProviderDirectory.dat");
			break;
		
		}
		in.close();
	}
}