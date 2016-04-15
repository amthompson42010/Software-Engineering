/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description Account base class to store name and address of 
 *              members/providers 
 */
public class Account implements java.io.Serializable
{
	private String Name;
	private String StreetAddress;
	private String City;
	private String State;
	private int ZipCode;
	private static final long serialVersionUID = 1L;

	/**
	 * Contructor for the Account class.
	 * @param name The name associated with the account
	 * @param street the street associated with the account
	 * @param city the city associated with the account
	 * @param state the state associated with the account
	 * @param zip the zipcode associated with the account
	 */
	public Account(String name, String street, String city, String state, int zip)
	{
		Name=name;
		StreetAddress=street;
		City=city;
		State=state;
		ZipCode=zip;
	}
	/**
	 * Gets and sets for the above variables used for the account class.
	 */
	public void setName(String name) { Name=name; }
	public void setStreet(String street) { StreetAddress=street; }
	public void setCity(String city) { City=city; }
	public void setState(String state) { State=state; }
	public void setZipCode(int zip) { ZipCode=zip; }
	public String getName() { return Name; }
	public String getStreetAddress() { return StreetAddress; }
	public String getCity() { return City; }
	public String getState() { return State; }
	public int getZipCode() { return ZipCode; }	
}
