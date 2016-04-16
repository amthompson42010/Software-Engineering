/**
 * @author Alexander Thompson
 * @title CS 315 Billing Data
 * @description Billing Data Class to write the service report to disk. 
 */
import java.io.*;
import java.util.*;

public class BillingData {
	
	/**
	 * Writes a service report to a .dat file.
	 * @param  date                   The date the report is created.
	 * @param  memNum                 The member's number.
	 * @param  provNum                The provider's number.
	 * @param  servCode               The service code.
	 * @param  comment                Comments to wrote about.
	 */
	public static void writeServiceReport(String date, int memNum, int provNum, int servCode, String comment) 
			throws IOException, ClassNotFoundException
	{
		ArrayList<ServiceReport> services;
		ServiceReport newReport = new ServiceReport(date, memNum, provNum, servCode, comment);
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("ServiceReports.dat"));
				
			services = (ArrayList<ServiceReport>) (input.readObject());
			ServiceReport temp = services.get(0);
			System.out.println(temp.getDateRecorded());
			services.add(newReport);
		}
		catch (FileNotFoundException e) {
			services = new ArrayList<ServiceReport>();
			services.add(newReport);
		}
		
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("ServiceReports.dat"));
		output.writeObject(services);
	}	
}