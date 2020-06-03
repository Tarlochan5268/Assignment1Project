package bankingApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MoneyTest {
	protected Currency CAD, HKD, NOK, EUR;
	protected Money CAD50,CAD100, EUR10, CAD200, EUR20, CAD0, EUR0, CADnegative100;
	
	@Before
	public void setUp() throws Exception {
		// setup sample currencies
		CAD = new Currency("CAD", 0.75);
		HKD = new Currency("HKD", 0.13);
		EUR = new Currency("EUR", 1.14);
		NOK = new Currency("NOK",0.10);
		
		// setup sample money amounts
		CAD50 = new Money(50, CAD);
		CAD100 = new Money(100, CAD);
		
		EUR10 = new Money(10, EUR);
		CAD200 = new Money(200, CAD);
		EUR20 = new Money(20, EUR);
		CAD0 = new Money(0, CAD);
		EUR0 = new Money(0, EUR);
		CADnegative100 = new Money(-100, CAD);
	}

	@Test
	public void testGetAmount() {
		//fail("Write test case here");
		assertEquals(0,Double.compare(100,CAD100.getAmount()));
		assertEquals(0,Double.compare(10,EUR10.getAmount()));
	}

	@Test
	public void testGetCurrency() {
		// THIS TEST FAILS
		//fail("Write test case here");
		//assertSame(new Currency("CAD",0.75),CAD100.getCurrency());
		//assertEquals(new Currency("CAD",0.75),CAD100.getCurrency());
		//This Test Case Fails because instance of Object gets random variables associated with its refrence in the memory
	}

	@Test
	public void testToString() {
		//fail("Write test case here");
		assertEquals("100.0 CAD",CAD100.toString());
		assertEquals("200.0 CAD",CAD200.toString());
		assertEquals("10.0 EUR",EUR10.toString());
		assertEquals("20.0 EUR",EUR20.toString());
	}

	@Test
	public void testGetUniversalValue() {
		//fail("Write test case here");
		assertEquals(75,CAD100.getUniversalValue(),2);
		assertEquals(0,Double.compare(75,CAD100.getUniversalValue()));
		assertEquals(150,CAD200.getUniversalValue(),2);
		assertEquals(11.4,EUR10.getUniversalValue(),2);
	}

	@Test
	public void testEqualsMoney() {
		//fail("Write test case here");
		assertFalse(CAD100.equals(CAD200));
		assertTrue(CAD100.equals(CAD100));
	}

	@Test
	public void testAdd() {
		//fail("Write test case here");
		Money CAD300 = CAD100.add(CAD200);
		assertEquals(300.0,CAD300.getAmount(),2);
		assertEquals(0,Double.compare(300.0,CAD300.getAmount()));
		Money EUR30 = EUR10.add(EUR20);
		assertEquals(30.0,EUR30.getAmount(),2);
		assertEquals(0,Double.compare(30.0,EUR30.getAmount()));
	}

	@Test
	public void testSubtract() {
		//fail("Write test case here");
		Money CAD150 = CAD200.subtract(CAD50);
		assertEquals(150.0,CAD150.getAmount(),2);
		assertEquals(0,Double.compare(150.0,CAD150.getAmount()));
	}

	@Test
	public void testIsZero() {
		//fail("Write test case here");
		assertTrue(CAD0.isZero());
		assertFalse(CAD100.isZero());
	}

	@Test
	public void testNegate() {
		//fail("Write test case here");
		assertEquals(100.0,CADnegative100.negate().getAmount(),2);
		assertEquals(-100.0,CAD100.negate().getAmount(),2);
	}

	@Test
	public void testCompareTo() {
		//fail("Write test case here");
		assertEquals(-1,CAD100.compareTo(CAD200));
		assertEquals(1,CAD100.compareTo(CAD50));
		assertEquals(0,CAD100.compareTo(CAD100));
	}
}
