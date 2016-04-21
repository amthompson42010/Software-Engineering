/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description Reads in a provider directory file, and breaks it into the appropriate attributes.
 */
import java.io.*;
import java.util.*;
public class ProviderDirectory {

	/**
	 * Function to display the directory for the providers.
	 */
	public static void display() 
			throws FileNotFoundException, IOException, ClassNotFoundException{
		
		try {
			ObjectInputStream inStr = new ObjectInputStream(new FileInputStream("ProviderDirectory.dat"));
			ArrayList<Service> services = (ArrayList<Service>) (inStr.readObject());
			inStr.close();
			for(Service service: services){
				System.out.println(service.getCode() +"    " + service.getDescription() + "    " +service.getFee());
			}
		}
		catch(Exception e){
			System.out.println("No services are available at this time");
		}
	}
}