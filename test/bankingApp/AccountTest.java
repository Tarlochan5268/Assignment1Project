package bankingApp;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AccountTest {
	protected Currency HKD = new Currency("HKD", 0.13);
	protected Currency CAD = new Currency("CAD", 0.75);
	protected Bank TD;
	protected Bank HSBC;
	protected Bank RBC = new Bank("Royal Bank of Canada", CAD);
	protected Account testAccount,testAccount2 = new Account("Test", CAD);

	
	@Before
	public void setUp() throws Exception {
		
		// setup test currencies

		// setup test accounts
		RBC.openAccount("Peter");

		testAccount = new Account("Albert", CAD);
		testAccount.deposit(new Money(100, CAD));
		//testAccount2.deposit(new Money(100, CAD));

		// setup an initial deposit
		RBC.deposit("Peter", new Money(500, CAD));
	}

	@Test
	public void testAddWithdraw() {
		// Something to consider - can you withdraw in a different currency?
		//fail("Write test case here");
		//System.out.println(testAccount.toString()); //@Null Pointer Fail
		//testAccount.withdraw(new Money(10,HKD));
		//assertEquals(0,testAccount.getBalance());
		testAccount2.deposit(new Money(100, CAD));
		assertEquals(100,testAccount2.getBalance().getAmount(),0.001);
		//System.out.println("Amount: "+testAccount2.getBalance().getAmount());

		testAccount2.withdraw(new Money(20, CAD));
		assertEquals(80,testAccount2.getBalance().getAmount(),0.001);
		//check if we can withdraw money in different currency
		testAccount2.withdraw(new Money(10, HKD));
		assertEquals(78.27,testAccount2.getBalance().getAmount(),0.001); //@ WORKS PERFECTLY
	}
	
	@Test
	public void testGetBalance() {
		testAccount2.deposit(new Money(100, CAD));
		assertEquals(100,testAccount2.getBalance().getAmount(),0.001);
		testAccount2.deposit(new Money(50, CAD));
		assertEquals(150,testAccount2.getBalance().getAmount(),0.001);
		//@Different currency deposit -> will it work ?
		testAccount2.deposit(new Money(10,HKD)); //THIS works as well
		assertEquals(151.73,testAccount2.getBalance().getAmount(),0.001);
		//testAccount2.getBalance();
		//fail("Write test case here");
		//testAccount.getBalance();
	}
}
