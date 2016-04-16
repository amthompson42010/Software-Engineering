package team7.chocan;
import java.io.*;
import java.util.*;

/**
 * @author Alexander Mark Thompson
 * @title CS 315 Member's Report
 * @description MemberReport records the member's name, number, and address,
 *              then lists the date of service, provider name, and service 
 *              name for each service provided to the member.
 */

public class MemberReport {
	
	private String name;
	private int number;
	private String street;
	private String city;
	private String state;
	private int zip;
	
	private ArrayList<ServiceReport> services;
	
	/**
	 * Constructor for the Member Report Class.
	 * @param  Name                   Member's Name
	 * @param  Number                 Member's Number
	 * @param  Street                 Member's Street from Address
	 * @param  City                   Member's City from Address
	 * @param  State                  Member's State from Address
	 * @param  Zip                    Member's Zip from Address
	 */
	public MemberReport(String Name, int Number, String Street,
			String City, String State, int Zip) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		name = Name;
		number = Number;
		street = Street;
		city = City;
		state = State;
		zip = Zip;
		
		services = new ArrayList<ServiceReport>();
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("ServiceReports.dat"));
				
		ArrayList<ServiceReport> allServices = (ArrayList<ServiceReport>) (input.readObject());
		
		for(ServiceReport service: allServices) {
			if(service.getMemberNum() == number)
				services.add(service);
		}
	}
	
	/**
	 * Writes the report to a file in the format of Member_DD_MM_YYYY
	 */
	public void writeToFile() throws FileNotFoundException{
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		String fName = String.format("%s_%d-%d-%d",name,day,month,year);
		
		PrintWriter out = new PrintWriter(fName);
		out.println(name);
		out.println(number);
		out.println(street);
		out.println(city);
		out.println(state);
		out.println(zip);
		for (ServiceReport service: services ) {
			out.println("\n" + service.getDateProvided());
			out.println(service.getProviderNum());  //Needs to be name
			out.println(service.getServiceCode());
		}
		out.close();
	}
	
}
