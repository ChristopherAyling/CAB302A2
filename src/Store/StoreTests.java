package Store;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StoreTests {

	private Store store;
	private String name = "Adam";
	private double capital = 7283.27;
	private Stock inventory = new Stock();
	
	
	@Before
	public void newStore() {
		store = new Store(name, capital, inventory);
	}
	
	@Test
	public void NameTest() {
		assertEquals(name, store.getName());
	}
	
	@Test
	public void CapitalTest() {
		// test getter
		assertEquals(capital, Store.getCapital());
		
		// test can handle no change, increments and decrements
		store.addCapital(0.0);
		assertEquals(capital, Store.getCapital());

		store.addCapital(1.0);
		assertEquals(capital+1.0, Store.getCapital());

		store.addCapital(-1.0);
		assertEquals(capital-1.0, Store.getCapital());
	}
	
	@Test
	public void InventoryTest() {
		
	}
}
