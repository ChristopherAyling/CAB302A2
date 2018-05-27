package Delivery;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Stock.Item;
import Store.Store;

import java.util.*;

public class ManifestTests {

	private Manifest truckCollection;
	
	@Before
	public void setUp() {
		truckCollection = new Manifest();
	}

	@Test
	public void testConstruction() {
		truckCollection = new Manifest();
	}
	
	@Test
	public void testAdd() {
		truckCollection = new Manifest();
		
		Truck truck = new OrdinaryTruck();
		
		truckCollection.add(truck);
	}
	
	@Test
	public void testTrucks() {
		truckCollection = new Manifest();
		
		assertEquals(truckCollection.getTrucks(), Collections.emptyList());
		
		Truck truck = new OrdinaryTruck();
		
		truckCollection.add(truck);
		assertEquals(truckCollection.getTrucks().get(0), truck);
	}
	
	@Test
	public void getCostTest() {
		//create trucks
		
		// calculate expected price
		
		// compare prices
	}

	@Test
	public void constructFromStoreTestOrdinaryOnly() throws DeliveryException {
		Store store = Store.getInstance();
		Item item1 = new Item("i1", 1, 1, 1, 10);
		store.setItemProperties(item1);
		Manifest man1 = new Manifest(store);
		System.out.println("manifest created from store:\n"+man1.toString());
		
		assertTrue("manifest.toString() should contain text", man1.toString().length()>0);
		assertEquals("first character should be: > ", (">"), Character.toString(man1.toString().charAt(0)));
		for (Truck truck : man1.getTrucks()) {
			for (Item item : truck.getCargo().getItems()) {
				store.getInventory().add(item);
			}
		}
		Manifest man2 = new Manifest(store);
		assertEquals(0, man2.getCost(), 0.001);
	}
	
	@Test
	public void testToString() throws DeliveryException {
		Manifest man = new Manifest();
		assertEquals("", man.toString());
		Truck ord = new OrdinaryTruck();
		Truck fridge = new RefrigeratedTruck();
		
		Item item1 = new Item("i1", 1, 1, 1, 1);
		Item item2 = new Item("i2", 1, 1, 1, 1, 1);
		ord.loadCargo(item1);
		fridge.loadCargo(item1);
		fridge.loadCargo(item2);
		
		man.add(ord);
		man.add(fridge);
		System.out.println("manifest created manually:\n"+man.toString());
		assertEquals(ord.getCost() + fridge.getCost(), man.getCost(), 0.001);
	}

}
