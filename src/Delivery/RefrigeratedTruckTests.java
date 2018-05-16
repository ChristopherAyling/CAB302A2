package Delivery;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefrigeratedTruckTests {

	private RefrigeratedTruck truck;

	@Test
	public void testConstruction() {
		truck = new RefrigeratedTruck();
	}
	
	@Test
	public void testCargoMaxCapacity() {
		truck = new RefrigeratedTruck();
		
		AssetEquals(RefrigeratedTruck.cargoMaxCapacity, 800);
	}
	
	@Test
	public void testLoadCargo() {
		// load that fits
		
		truck = new RefigeratedTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < truck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		// load that does not fit
		
		truck = new RefrigeratedTruck();
		
		cargo = new Stock();
		
		for(int i = 0; i <= truck.getCargoMaxCapacity; i++) {
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
		truck = new RefrigeratedTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < RefrigeratedTruck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCargo(), cargo);
	}
	
	@Test
	public void testTemperature() {
		truck = new RefrigeratedTruck();
		
		assertEquals(truck.temperature(), 10);  // base/high temperature
		
		Stock cargo = new Stock();
		
		cargo.add(new Item("name", 1, 2, 1, 1, -20));
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.temperature(), -20);  // low temperature
	}
	
	@Test
	public void testCargoCurrentCapacity() {	
		truck = new RefrigeratedTruck();
		
		assertEquals(truck.getCargoCurrentCapacity(), 800);  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCargoCurrentCapacity(), 800 - 500);  // when containing
	}
	
	@Test
	public void testCost() {
		truck = new RefrigeratedTruck();
		
		AssertEquals(truck.getCost(), 900);  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1, 10));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCost(), 900 + 200 * java.lang.Math.pow(0.7, truck.getTemperature() / 5));  // when containing
	}

}
