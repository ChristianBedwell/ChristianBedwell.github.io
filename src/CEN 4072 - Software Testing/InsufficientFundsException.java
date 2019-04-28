// user defined exception for insufficient funds in account
public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = -6667742746659274665L;

	public InsufficientFundsException() {
		super("Insufficient funds to complete transaction");
	}	
}
