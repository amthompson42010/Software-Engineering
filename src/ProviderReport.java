/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description Provider report records that provider's name, number, and address, then lists the date,
 * member name and number, service code, and fee for each service provided. Finally, it lists
 * the total number of consultations and total fee.
 */
package team7.chocan;
import java.io.*;
import java.util.*;

public class ProviderReport {
	private String name;
	private int number;
	private String street;
	private String city;
	private String state;
	private int zip;
	private int feeTotal = 0;
	private int consultationNum = 0;
	private ArrayList<ServiceReport> services;
	
	/**
	 * Constructor reads the ServiceReports.dat file for all services provided by the given provider
	 * 
	 * @param Name The provider's name
	 * @param Number The provider's 9-digit number
	 * @param Street The provider's street address
	 * @param City The provider's city
	 * @param State The provider's state
	 * @param Zip The provider's Zip code
	 */
	public ProviderReport(String Name, int Number, String Street,
			String City, String State, int Zip) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		name = Name;
		number = Number;
		street = Street;
		city = City;
		state = State;
		zip = Zip;
		
		services = new ArrayList<ServiceReport>();
		ObjectInputStream input =
				 new ObjectInputStream(new FileInputStream("ServiceReports.dat"));
				
		ArrayList<ServiceReport> allServices = (ArrayList<ServiceReport>) (input.readObject());
		
		for(ServiceReport service: allServices) {
			if(service.getProviderNum() == number)
				services.add(service);
				consultationNum++;
		}
		
	}
	
	/**
	 * writeToFile writes the report to a file named in the format Provider_DD_MM_YYY
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
			out.println(service.getDateRecorded());
			out.println(service.getMemberName());  
			out.println(service.getMemberNum());
			out.println(service.getFee());
			feeTotal += Integer.parseInt(service.getFee());
			
		}
		out.println("Total number of consultations:" + consultationNum);
		out.println("Total fee for week" + feeTotal);
		out.close();
	}
	
}
