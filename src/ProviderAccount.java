/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description Provider Account class to store name, address, and provider number 
 */
public class ProviderAccount extends Account implements java.io.Serializable
{
	private int number;
	private static final long serialVersionUID = 1L;

	/**
	 * A constructor for the Provider Account class.
	 * @param {String} name the name associated with the provider account
	 * @param {String} street the street associated with the provider account
	 * @param {String} city the city associated with the provider account
	 * @param {String} state the state associated with the provider account
	 * @param {int} zip the zipcode associated with the provider account
	 * @param {int} number the provider number associated with the provider account
	 */
	public ProviderAccount(String name, String street, String city, String state, 
			int zip, int number)
	{
		super(name, street, city, state, zip);
		this.number=number;
	}
	
	/**
	 * Gets and sets for the variables declared in this file.
	 */
	public void setNumber(int number) { this.number=number; }
	public int getNumber() { return this.number; }
}
