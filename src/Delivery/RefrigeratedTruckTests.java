package Delivery;

import static org.junit.Assert.*;

import org.junit.Test;

import Stock.Item;
import Stock.Stock;

public class RefrigeratedTruckTests {

	private RefrigeratedTruck truck;

	@Test
	public void testConstruction() {
		truck = new RefrigeratedTruck();
	}
	
	@Test
	public void testCargoMaxCapacity() {
		truck = new RefrigeratedTruck();
		
		assertEquals(truck.cargoMaxCapacity, 800);
	}
	
	@Test
	public void testLoadCargoSingleItemNoTemp() throws DeliveryException {
		truck = new RefrigeratedTruck();
		
		Item item = new Item("beans", 1, 1, 1, 1);
		
		truck.loadCargo(item);
	}
	
	@Test
	public void testLoadCargoSingleItemTemp() throws DeliveryException {
		truck = new RefrigeratedTruck();
		
		Item item = new Item("beans", 1, 1, 1, 1, 0);
		
		truck.loadCargo(item);
	}
	
	@Test
	public void testLoadCargo() throws DeliveryException {
		// load that fits
		
		truck = new RefrigeratedTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < truck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		// load that does not fit
		
		truck = new RefrigeratedTruck();
		
		cargo = new Stock();
		
		for(int i = 0; i <= truck.cargoMaxCapacity; i++) {
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
	public void testCargo() throws DeliveryException {
		truck = new RefrigeratedTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < truck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCargo().toString(), cargo.toString());
	}
	
	
	@Test
	public void testTemperature() throws DeliveryException {
		truck = new RefrigeratedTruck();
		
		assertEquals(10.0, truck.getTemperature().doubleValue(), 0.001);  // base/high temperature
		
		truck.loadCargo(new Item("name", 1, 2, 1, 1, -20));
		
		assertEquals(-20, truck.getTemperature().doubleValue(), 0.001);  // low temperature
	}
	
	
	@Test
	public void testCargoCurrentCapacity() throws DeliveryException {	
		truck = new RefrigeratedTruck();
		
		assertEquals(800, truck.getCargoCurrentCapacity());  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCargoCurrentCapacity(), 800 - 500);  // when containing
	}
	
	@Test
	public void testCost() throws DeliveryException {
		truck = new RefrigeratedTruck();
		
		assertEquals(900 + 200 * java.lang.Math.pow(0.7, truck.getTemperature() / 5), truck.getCost(), 0.001);  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1, 10));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(900 + 200 * java.lang.Math.pow(0.7, truck.getTemperature() / 5), truck.getCost(), 0.001);  // when containing
	}

}
