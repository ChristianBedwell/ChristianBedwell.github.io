// user defined exception for account overdrawn
public class AccountOverdrawnException extends Exception {

	private static final long serialVersionUID = 3073893446951649972L;

	public AccountOverdrawnException() {
		super("Account cannot be less than $50.00");
	}	
}