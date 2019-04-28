public class BankAccount {

	// Constants
	private final static double requiredMinimum = 50.0;
	private final static double premiumAmount = 800.0;
        
	// Attributes
	private double balance;
	
	// Constructs a bank account with fifty dollar balance
	public BankAccount () {
		this.balance = BankAccount.requiredMinimum;
	}
	
	// Constructs a bank account with a given balance
	public BankAccount (double initialBalance) {
		this.balance = initialBalance;
	}
	
	// Deposits money into the bank account
	public void deposit (double amount) throws InsufficientFundsException {
		
        // If there are no funds to deposit, throw exception
        if (amount == 0.0) {
            throw new InsufficientFundsException();
        }
        // Otherwise instantiates balance to newBalance
        else {
        	double newBalance = this.balance + amount;
            this.balance = newBalance;
        } 
	}
	
	// Withdraws money from the bank account
	public void withdraw (double amount) throws AccountOverdrawnException {
            
            // If transaction is lower than the minimum, throws exception
            if (this.balance - amount < requiredMinimum) {
                throw new AccountOverdrawnException();
            }
            // Otherwise instantiates balance to newBalance
            else {
                double newBalance = this.balance - amount;
                this.balance = newBalance;
            }           
	}
	
	// Transfers money
	public void transfer(double amount, BankAccount toAccount) throws 
		AccountOverdrawnException, InsufficientFundsException {     
		
        this.withdraw(amount);
        toAccount.deposit(amount);          
	}
	
    // Determines whether or not an account has premium status
	public boolean isPremiumAccount() {
		return (this.balance > BankAccount.premiumAmount);
	}
	
	// Gets the current balance of the bank account
	public double getBalance () {
		return this.balance;
	}	
}
