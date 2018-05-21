package Delivery;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		
		assertEquals(truckCollection.trucks(), Collections.emptyList());
		
		Truck truck = new OrdinaryTruck();
		
		truckCollection.add(truck);
		assertEquals(truckCollection.trucks().get(0), truck);
	}
	
	@Test
	public void getCostTest() {
		//create trucks
		
		// calculate expected price
		
		// compare prices
	}

}
