import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class BankAccountTest {
    
    // Declare two accounts of type BankAccount
    private BankAccount regularAccount;
    private BankAccount premiumAccount;
    
    // Ran before each test case in the BankAccountTest class
    @Before
    public void setUp() {
    	
        // Instantiate both accounts to BankAccount objects
        regularAccount = new BankAccount();
        premiumAccount = new BankAccount(936.8);
    }
    
    // Ran after each test case in the BankAccountTest class
    @After
    public void tearDown() {
    	
        // Instantiate both BankAccount objects to null
        regularAccount = null;
        premiumAccount = null;
    }
    
    // Test of BankAccount method, of class BankAccount
    @Test
    public void testBankAccount() {
    	
        System.out.println("BankAccountTest: BankAccount()");
        
        // Balance should be equal to requiredMinimum of 50.0
        assertEquals(50.0, regularAccount.getBalance(), 0.01);
    }
    
    // Test of parameterized BankAccount method, of class BankAccount
    @Test
    public void testBankAccountWithInitialBalance() {
    	
        System.out.println("BankAccountTest: BankAccount(double initialBalance)");
        
        // Balance should be equal to given balance of 936.8
        assertEquals(936.8, premiumAccount.getBalance(), 0.01);
    }
    
    // Test of getBalance method, of class BankAccount.
    @Test
    public void testGetBalance() {
    	
        System.out.println("BankAccountTest: getBalance()");
        
        // Balance should be equal to requiredMinimum of 50.0
        assertEquals(50.0, regularAccount.getBalance(), 0.01);
        
        // Balance should be equal to given balance of 936.8
        assertEquals(936.8, premiumAccount.getBalance(), 0.01);
    }   
    
    // Test of isPremiumAccount method, of class BankAccount.
    @Test
    public void testIsPremiumAccount() {
    	
        System.out.println("BankAccountTest: isPremiumAccount()");
        
        // Since balance is above premiumAmount, it should be a premium account
        assertTrue(premiumAccount.isPremiumAccount());
        
        // Since balance is below premiumAmount, it should be a regular account
        assertFalse(regularAccount.isPremiumAccount());
    }
    
    // Test of transfer method, of class BankAccount.
    @Test
    public void testTransfer() throws InsufficientFundsException, AccountOverdrawnException {
    	
        System.out.println("BankAccountTest: transfer()");
        double amountTransferred = 47.2;
        premiumAccount.transfer(amountTransferred, regularAccount);
        
        // Balance should be equal to given balance of 936.8 minus the amount 
        // transferred of 47.2
        assertEquals(889.6, premiumAccount.getBalance(), 0.01);
        
        // Balance should be equal to requiredMinimum of 50.0 plus the amount
        // transferred of 47.2
        assertEquals(97.2, regularAccount.getBalance(), 0.01);
    }
    
    // Test of withdraw method, of class BankAccount.
    @Test
    public void testWithdraw() throws InsufficientFundsException, AccountOverdrawnException {
    	
        System.out.println("BankAccountTest: withdraw()");
        double amountWithdrawn = 34.7; 
        double amountDeposited = 38.3;
        
        // Since withdrawal from regularAccount would result in exception
        // Deposit into regular account first
        regularAccount.deposit(amountDeposited);        
        regularAccount.withdraw(amountWithdrawn);
        premiumAccount.withdraw(amountWithdrawn);
        
        // Balance should be equal to the requiredMinimum of 50.0 plus the 
        // amount deposited of 38.3 and minus the amount withdrawn of 34.7
        assertEquals(53.6, regularAccount.getBalance(), 0.01);
        
        // Balance should be equal to the given balance of 936.8 minus the 
        // amount withdrawn of 34.7
        assertEquals(902.1, premiumAccount.getBalance(), 0.01);
    }
    
    // Test of deposit method, of class BankAccount.
    @Test
    public void testDeposit() throws InsufficientFundsException {
    	
        System.out.println("BankAccountTest: deposit()");
        double amountDeposited = 56.3;
        regularAccount.deposit(amountDeposited);
        premiumAccount.deposit(amountDeposited);
        
        // Balance should be equal to requiredMinimum of 50.0 plus the amount
        // deposited of 56.3
        assertEquals(106.3, regularAccount.getBalance(), 0.01);
        
        // Balance should be equal to the given balance of 936.8 plus the amount
        // deposited of 56.3
        assertEquals(993.1, premiumAccount.getBalance(), 0.01);
    }
       
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // Test of AccountOverdrawnException class
    @Test 
    public void testAccountOverdrawnException() throws AccountOverdrawnException, InsufficientFundsException {
    	
    	System.out.println("BankAccountTest: AccountOverdrawnException");
        double amountWithdrawn = 34.5;
        double amountTransferred = 893.4;
        
        // AccountOverdrawnException should be thrown when account balance is 
        // less than 50
        thrown.expect(AccountOverdrawnException.class);
        thrown.expectMessage(containsString("Account cannot be less than $50.00"));
        regularAccount.withdraw(amountWithdrawn);
        premiumAccount.withdraw(amountTransferred);
    }

    // Test of InsufficientFundsException class
    @Test 
    public void testInsufficientFundsException() throws InsufficientFundsException {
    	
        System.out.println("BankAccountTest: InsufficientFundsException");
        double amountDeposited = 0.0;
        
        // InsufficientFundsException should be thrown when the amountDeposited
        // is insufficient to complete a transaction.
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage(containsString("Insufficient funds to complete transaction"));
        regularAccount.deposit(amountDeposited);
        premiumAccount.deposit(amountDeposited);
    }
}    

