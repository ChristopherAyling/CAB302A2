package Store;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Test cases for Store.
 *
 * @author Christopher Ayling
 *
 */
public class StoreTests {

	private Store store = null, store2 = null;
	private String name = "Family Mart";
	private double capital = 7283.27;
	
	
	@Before
	public void newStore() {
		store = Store.getInstance();
	}

	@Test
	public void testEquality() {
		store2 = Store.getInstance();
		assertTrue(store == store2);
	}

	@Test
	public void NameTest() {
		store.setName(name);
		assertEquals(name, store.getName());
	}

	@Test
	public void CapitalTest() {
		// test setter
		store.setCapital(capital);
		
		// test getter
		assertEquals(capital, store.getCapital(), 0.001);
		
		// test can handle no change, increments and decrements
		store.addCapital(0.0);
		assertEquals(capital, store.getCapital(), 0.001);

		store.addCapital(1.0);
		assertEquals(capital+1.0, store.getCapital(), 0.001);

		store.addCapital(-1.0);
		assertEquals(capital, store.getCapital(), 0.001);
	}

	@Test
	public void displayCapitalTest() {
		// test displaying starting capital
		store.setCapital(100000);
		assertEquals("$100,000.00", store.displayCapital());
		// test negative
		store.setCapital(-10.57);
		assertEquals("-$10.57", store.displayCapital());
		// test 0
		store.setCapital(0);
		assertEquals("$0.00", store.displayCapital());
		// test rounding
		store.setCapital(1.1111111); // should round down
		assertEquals("$1.11", store.displayCapital());
		
		store.setCapital(1.99999999); // should round up
		assertEquals("$2.00", store.displayCapital());
		
		// test comma separation
		store.setCapital(1000000000);
		assertEquals("$1,000,000,000.00", store.displayCapital());
	}

	@Test
	public void InventoryTest() {
		store.getInventory();
	}
	
}
