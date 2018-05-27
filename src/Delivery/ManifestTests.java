package Delivery;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Stock.Item;

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
	public void testToString() throws DeliveryException {
		Manifest man = new Manifest();
		Truck ord = new OrdinaryTruck();
		Truck fridge = new RefrigeratedTruck();
		
		Item item1 = new Item("i1", 1, 1, 1, 1);
		Item item2 = new Item("i2", 1, 1, 1, 1, 1);
		ord.loadCargo(item1);
		fridge.loadCargo(item1);
		fridge.loadCargo(item2);
		
		man.add(ord);
		man.add(fridge);
		System.out.println(man.toString());
	}

}
