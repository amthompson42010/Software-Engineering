/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description The Service Report class holds important information for each billed service
 * including  the date, provider, member, service code, fee, and any comments
 */
package team7.chocan;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServiceReport implements Serializable {
		
	final long serialVersionUID = 00001;
		private String dateRecorded;
		private String dateProvided;
		private int memberNum;
		private int providerNum;
		private int serviceCode;
		private String serviceName;
		private String comments;
		private String providerName;
		private String memberName;
		private String fee;
		
		/**
		 * Constructor accesses lists of members, providers, and services to find names
		 * associated with each number
		 * 
		 * @param date the date the service was provided
		 * @param memNum the 9-digit member number
		 * @param provNum the 9-digit provider number
		 * @param servCode the 6-digit service code
		 * @param comment optional comment field
		 */
		public ServiceReport(String date, int memNum, int provNum, int servCode, String comment) 
				throws FileNotFoundException, IOException, ClassNotFoundException
		{
			dateProvided = date;
			memberNum = memNum;
			providerNum = provNum;
			serviceCode = servCode;
			comments = comment;
		
			DateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
			Date dateObj = new Date();
			dateRecorded = df.format(dateObj);
			
			ObjectInputStream inStr = 
					new ObjectInputStream(new FileInputStream("ProviderAccounts.dat"));
			ArrayList<ProviderAccount> providers = (ArrayList<ProviderAccount>) (inStr.readObject());
			inStr.close();
			
			ObjectInputStream inStr2 = 
					new ObjectInputStream(new FileInputStream("MemberAccounts.dat"));
			ArrayList<MemberAccount> members = (ArrayList<MemberAccount>) (inStr2.readObject());
			inStr2.close();
			
			ObjectInputStream inStr3 = 
					new ObjectInputStream(new FileInputStream("ProviderDirectory.dat"));
			ArrayList<Service> services = (ArrayList<Service>) (inStr3.readObject());
			inStr3.close();
			
			for(ProviderAccount provider: providers){
				if(provider.getNumber() == providerNum) providerName = provider.getName();
			}
			
			for(MemberAccount member: members){
				if(member.getNumber() == providerNum) memberName = member.getName();
			}
			
			for(Service service: services){
				if(service.getCode() == serviceCode){
					serviceName = service.getDescription();
					fee = service.getFee();
				}
			}
		}

		/**
		 * Gets and sets for the variables in this class.
		 */
		public String getDateRecorded() { return dateRecorded; }
		public String getDateProvided() { return dateProvided; }
		public int getProviderNum() { return providerNum; }
		public String getProviderName() { return providerName; }
		public int getMemberNum() { return memberNum; }
		public String getMemberName() { return memberName; }
		public int getServiceCode(){ return serviceCode; }
		public String getServiceName() { return serviceName; }
		public String getFee() { return fee; }
		public String setComments() { return comments; }
		public void setDateRecorded(String Date) { dateRecorded = Date; }
		public void setDateProvided(String Date) { dateProvided = Date; }

		/**
		 * Sets the provider number and finds the associated name
		 * @param Num the provider's number
		 */
		public void setProviderNum(int Num){
			providerNum = Num;
			ObjectInputStream inStr;
			try {
				inStr = new ObjectInputStream(new FileInputStream("ProviderAccounts.dat"));
			ArrayList<ProviderAccount> providers = (ArrayList<ProviderAccount>) (inStr.readObject());
			inStr.close();
			
			for(ProviderAccount provider: providers){
				if(provider.getNumber() == providerNum) providerName = provider.getName();
			}
			}
		  catch (Exception e) {
		
			e.printStackTrace();
		  }
		}
		
		/**
		 * Sets the member's number and finds the associated name
		 * @param Num the member number of the client
		 */
		public void setMemberNum(int Num){
			memberNum = Num;
			try{
				ObjectInputStream inStr2 = 
						new ObjectInputStream(new FileInputStream("MemberAccounts.dat"));
				ArrayList<MemberAccount> members = (ArrayList<MemberAccount>) (inStr2.readObject());
				inStr2.close();
				
				for(MemberAccount member: members){
					if(member.getNumber() == providerNum) memberName = member.getName();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		/**
		 * Sets the service code and finds the associated name and fees
		 * @param code The service code
		 */
		public void setServiceCode(int code){
			serviceCode = code;
			try {
			ObjectInputStream inStr3 = 
					new ObjectInputStream(new FileInputStream("ProviderDirectory.dat"));
			ArrayList<Service> services = (ArrayList<Service>) (inStr3.readObject());
			inStr3.close();
			for(Service service: services){
				if(service.getCode() == serviceCode) {
					serviceName = service.getDescription();
					fee = service.getFee();
				}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void setComments(String Comments) { comments = Comments; }
		
}
