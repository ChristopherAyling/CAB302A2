package Store;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StoreTests {

	private Store store = null, store2 = null;
	private String name = "Family Mart";
	private double capital = 7283.27;
	private Stock inventory = new Stock();
	
	
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
		assertEquals(capital, store.getCapital());
		
		// test can handle no change, increments and decrements
		store.addCapital(0.0);
		assertEquals(capital, store.getCapital());

		store.addCapital(1.0);
		assertEquals(capital+1.0, store.getCapital());

		store.addCapital(-1.0);
		assertEquals(capital-1.0, store.getCapital());
	}
	
	@Test
	public void displayCapitalTest() {
		// test displaying 
		store.setCapital(100000.0);
		assertEquals(store.displayCapital(), "$100,000.00");
		store.setCapital()
	}
	
	@Test
	public void InventoryTest() {
		store.getInventory();
	}
}
