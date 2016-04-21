/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description Reports class is used to run the member, provider, and summary reports.
 */
package team7.chocan;
import java.io.*;
import java.util.*;

public class Reports {
	
	/**
	 * runMemberReport reads in file of members and creates a MemberReport object for each member.
	 * It then calls writeToFile
	 */
	public void runMemberReport() throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		
		ObjectInputStream input =
				 new ObjectInputStream(new FileInputStream("MemberAccounts.dat"));
		ArrayList<MemberAccount> members = (ArrayList<MemberAccount>) (input.readObject());
		
		for(MemberAccount member : members)
		{
			String name = member.getName();
			int number = member.getNumber();
			String street = member.getStreetAddress();
			String city = member.getCity();
			String state = member.getState();
			int zipCode = member.getZipCode();
			MemberReport report = new MemberReport(name,number,street,city,state,zipCode);
			report.writeToFile();
		}
		
	}
	
	/**
	 * runProviderReport reads in file of providers and creates a ProviderReport object for each provider.
	 * It then calls writeToFile.
	 */
	public void runProviderReport() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream input =
				 new ObjectInputStream(new FileInputStream("ProviderAccounts.dat"));
		ArrayList<ProviderAccount> providers = (ArrayList<ProviderAccount>) (input.readObject());
		
		for(ProviderAccount provider : providers)
		{
			String name = provider.getName();
			int number = provider.getNumber();
			String street = provider.getStreetAddress();
			String city = provider.getCity();
			String state = provider.getState();
			int zipCode = provider.getZipCode();
			ProviderReport report = new ProviderReport(name,number,street,city,state,zipCode);
			report.writeToFile();
		}
	}

	/**
	 * runSummaryReport creates a SummaryReport object and calls its writeToFile method.
	 */
	public void runSummaryReport() throws FileNotFoundException
	{
		SummaryReport report = new SummaryReport();
		report.writeToFile();
	}
}
