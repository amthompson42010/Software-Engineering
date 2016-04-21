/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description The Summary report lists every provider to be paid, the number of consultations each had,
 *              and his/her total fee. It also lists the total number of providers, the total number of 
 *              consultations, and the overall fee
 */
package team7.chocan;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class SummaryReport {
	
	private ArrayList<ServiceReport> services;
	private ArrayList<ArrayList<ServiceReport>> providers;
	
	private int allConsultations = 0;
	private int allFees = 0;
	
	/**
	 * Constructor opens ServiceReports.dat, reads in each service, and groups them by provider
	 */
	public SummaryReport() throws FileNotFoundException{
		try{
		services = new ArrayList<ServiceReport>();
		ObjectInputStream input =
				 new ObjectInputStream(new FileInputStream("ServiceReports.dat"));
				
		ArrayList<ServiceReport> allServices = (ArrayList<ServiceReport>) (input.readObject());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		for(ServiceReport service: services)
		{
			for (int i = 0; i < providers.size(); i++)
			{
				ArrayList<ServiceReport> temp = providers.get(0);
				if(!temp.isEmpty()) {
					if(temp.get(0).getProviderNum() == service.getProviderNum()) {
						temp.add(service);
						break;
					}
				}
			}
			ArrayList<ServiceReport> temp2 = new ArrayList<ServiceReport>();
			temp2.add(service);
			providers.add(temp2);
		}
	}
	/**
	 * writeToFile method writes the report to a file named with the format Summary_DD_MM_YYY
	 */
	public void writeToFile() throws FileNotFoundException {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		String fName = String.format("Summary_%d-%d-%d",day,month,year);
		
		PrintWriter out = new PrintWriter(fName);
		for (int i = 0; i < providers.size(); i++)
		{
			int totalFee = 0;
			int totalConsultations = 0;
			ArrayList<ServiceReport> provider = providers.get(0);
			for(ServiceReport service: provider)
			{
				totalFee += Integer.parseInt(service.getFee());
				totalConsultations++;
			}
			int providerNum = provider.get(0).getProviderNum();
			String providerName = provider.get(0).getProviderName();
			
			out.print(providerNum + " ");
			out.println(providerName);
			out.println("Total fee:" + totalFee);
			out.println("Total Consultations" + totalConsultations + "\n");
			allConsultations += totalConsultations;
			allFees += totalFee;
			
		}
		out.println("Total number of providers: " + providers.size());
		out.println("Overall consultations" + allConsultations);
		out.println("Overall fee" + allFees);
	}
}
