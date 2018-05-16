package Delivery;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class ManifestTests {

	private Manifest truckCollection;

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
		
		AssertEquals(truckCollection.trucks(), Collections.emptyList());
		
		Truck truck = new OrdinaryTruck();
		
		truckCollection.add(truck);
		
		AssertEquals(truckCollection.trucks().get(0), truck);
	}

}
