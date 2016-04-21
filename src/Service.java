/**
 * @author Alexander Mark Thompson
 * @title CS 315 Semester Project
 * @description Service class to store service code, description, and fee of single service
 */
public class Service implements java.io.Serializable
{
	private int code;
	private String description;
	private String fee;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Service class.
	 * @param {int} code the 6 digit service code associated with the service
	 * @param {String} description a description of the service
	 * @param {String} fee the cost of the service
	 */
	public Service(int code, String description, String fee)
	{
		this.code = code;
		this.description = description; 
		this.fee = fee;
	}

	/**
	 * Gets and sets for teh variables defined in this class.
	 */
	public void setCode(int code) { this.code=code; }
	public void setDescription( String description) { this.description = description; }
	public void setFee(String fee) { this.fee=fee; }
	public int getCode() { return this.code; }
	public String getDescription() { return this.description; }
	public String getFee() { return this.fee; }
}
