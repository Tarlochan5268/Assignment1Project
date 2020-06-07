package bankingApp;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;


public class BankTest {
	protected Currency CAD = new Currency("CAD", 0.75);
	protected Currency HKD = new Currency("HKD", 0.13);
	protected Bank RBC = new Bank("Royal Bank of Canada", CAD);
	protected Bank TD = new Bank("TD Bank", CAD);
	protected Bank HSBC = new Bank("Hong Kong Shanghai Banking Corporation", HKD);
	
	
	@Before
	public void setUp() throws Exception {
		// add sample customers to the banks
		// HINT:  uncomment these lines AFTER you test the openAccount() function
		// You can quickly uncomment / comment by highlighting the lines of code and pressing 
		// CTRL + / on your keyboard  (or CMD + / for Macs)
		
		this.RBC.openAccount("Marcos");
		this.RBC.openAccount("Albert");
		this.TD.openAccount("Jigesha");
//		this.HSBC.openAccount("Pritesh");
	}

	@Test
	public void testGetName() {
		//fail("Write test case here");
		assertEquals("Royal Bank of Canada",RBC.getName());
		assertEquals("TD Bank",TD.getName());
		assertEquals("Hong Kong Shanghai Banking Corporation",HSBC.getName());
	}

	@Test
	public void testGetCurrency() {
		//fail("Write test case here");
		assertEquals("CAD",RBC.getCurrency().getName());
		assertEquals("HKD",HSBC.getCurrency().getName());
		//assertEquals("bankingApp.Currency@17d0685f",RBC.getCurrency()); //@Fails
	}

	@Test
	public void testOpenAccount() throws AccountExistsException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		// See the example in class notes for testing exceptions.
		//fail("Write test case here");
		//RBC.openAccount("Marcos");
		Exception exception = assertThrows(AccountExistsException.class, () -> {
					RBC.openAccount("Marcos");

				});

		String expectedMessage = "Account Already Exist";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		// See the example in class notes for testing exceptions.
		//fail("Write test case here");
		RBC.deposit("Marcos",new Money(10,CAD)); // will work as account exist
		Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
			RBC.deposit("Test",new Money(10,CAD)); // will give exception as account does not exist

		});
		assertEquals(10,RBC.getBalance("Marcos"),0.001); // deposited amount 10 to marcos account
		String expectedMessage = "Account Does Not Exist";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		// See the example in class notes for testing exceptions.
		//fail("Write test case here");
		assertEquals(0,RBC.getBalance("Marcos"),0.001);
		RBC.deposit("Marcos",new Money(15,CAD));
		assertEquals(15,RBC.getBalance("Marcos"),0.001);
		RBC.withdraw("Marcos",new Money(5,CAD)); // will work as account exist
		assertEquals(10,RBC.getBalance("Marcos"),0.001);
		Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
			RBC.withdraw("Test",new Money(5,CAD)); // will give exception as account does not exist

		});
		//10 from above and 10 from this function
		assertEquals(10,RBC.getBalance("Marcos"),0.001); // deposited amount 10 to marcos account
		String expectedMessage = "Account Does Not Exist";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		// See the example in class notes for testing exceptions.
		//fail("Write test case here");
		RBC.deposit("Marcos",new Money(10,CAD));
		// deposited amount 10 to marcos account
		assertEquals(10,RBC.getBalance("Marcos"),0.001); // will work as account exist
		Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
			RBC.getBalance("Test"); // will give exception as account dont exist

		});
		String expectedMessage = "Account Does Not Exist";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		// Note: You should test both types of transfers:
		// 1. Transfer from account to account
		// 2. Transfer between banks
		// See the Bank.java file for more details on Transfers
		//fail("Write test case here");

		RBC.deposit("Marcos",new Money(15,CAD));
		assertEquals(15,RBC.getBalance("Marcos"),0.001); // will work as account exist
		RBC.deposit("Albert",new Money(5,CAD));
		assertEquals(5,RBC.getBalance("Albert"),0.001); // will work as account exist
		RBC.transfer("Marcos","Albert",new Money(1,CAD)); //transfer from Marcos to Albert account
		assertEquals(14,RBC.getBalance("Marcos"),0.001);
		assertEquals(6,RBC.getBalance("Albert"),0.001);
		TD.deposit("Jigesha",new Money(10,CAD));
		assertEquals(10,TD.getBalance("Jigesha"),0.001);
		RBC.transfer("Marcos",TD,"Jigesha",new Money(3,CAD)); //TRANSFER between different Banks
		assertEquals(13,TD.getBalance("Jigesha"),0.001);
		Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
			RBC.transfer("Marcos","Test",new Money(1,CAD)); // will give exception as account does not exist
		});
		assertEquals(11,RBC.getBalance("Marcos"),0.001);
		String expectedMessage = "Account Does Not Exist";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
}
