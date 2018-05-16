package Delivery;

import static org.junit.Assert.*;

import org.junit.Test;

import Stock.Item;

public class OrdinaryTruckTests {
	
	private OrdinaryTruck truck;

	@Test
	public void testConstruction() {
		truck = new OrdinaryTruck();
	}
	
	@Test
	public void testCargoMaxCapacity() {
		truck = new OrdinaryTruck();
		
		assertEquals(OrdinaryTruck.cargoMaxCapacity, 1000);
	}
	
	@Test
	public void testLoadCargo() {
		// load that fits
		
		truck = new OrdinaryTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < OrdinaryTruck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		// load that does not fit
		
		truck = new OrdinaryTruck();
		
		cargo = new Stock();
		
		for(int i = 0; i <= OrdinaryTruck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		Boolean exceptionOnOverload = false;
		
		try {
			truck.loadCargo(cargo);
		} catch(DeliveryException e){
			exceptionOnOverload = true;
		}
		
		assertTrue(exceptionOnOverload);
		
		// TODO may need to be modified depending on how stock is handled
	}
	
	@Test
	public void testCargo() {
		truck = new OrdinaryTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < OrdinaryTruck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCargo(), cargo);
		
	}
	
	@Test
	public void testCargoCurrentCapacity() {	
		truck = new OrdinaryTruck();
		
		assertEquals(truck.getCargoCurrentCapacity(), 1000);  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCargoCurrentCapacity(), 1000 - 500);  // when containing
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCost() {
		truck = new OrdinaryTruck();
		
		assertEquals(truck.getCost(), 750.0);  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCost(), 750 + (0.25 * 500));  // when containing
	}

}
