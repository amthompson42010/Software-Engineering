/**
 * @author Alexander Mark Thompson
 * @title CS 315 Member's Account
 * @description MemberAccount class to store name, address, member number, and member status
 */

public class MemberAccount extends Account implements java.io.Serializable 
{
	private int number; // member number
	private String status; //membership status
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new Member account tied to a name, street addresss, city,
	 * state, zipcode, and member's number.
	 * @param  name   The name associated with the member's account.
	 * @param  street The street associated with the member's account.
	 * @param  city   The city associated with the member's account.
	 * @param  state  The state associated with the member's account.
	 * @param  zip    The zipcode associated with the member's account.
	 * @param  number The member's number associated with their account.
	 * @param  status The status of the member's account.
	 * @return        This is a contructor for the MemberAccount class.
	 */
	public MemberAccount(String name, String street, String city, String state, 
			int zip, int number, String status)
	{
		super(name, street, city, state, zip);
		this.number=number;
		this.status=status;
	}

	/**
	 * This section are the gets and sets for the variables used in the 
	 * Member's Account class.
	 */
	public void setNumber(int number) { this.number = number; }
	public void setStatus(String status) { this.status = status; }
	public int getNumber() { return this.number; }
	public String checkStatus() { return this.status; }
}
