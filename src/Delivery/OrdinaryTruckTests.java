package Delivery;

import static org.junit.Assert.*;

import org.junit.Test;

import Stock.Item;
import Stock.Stock;

public class OrdinaryTruckTests {
	
	private OrdinaryTruck truck;

	@Test
	public void testConstruction() {
		truck = new OrdinaryTruck();
	}
	
	@Test
	public void testCargoMaxCapacity() {
		truck = new OrdinaryTruck();
		
		assertEquals(truck.cargoMaxCapacity, 1000);
	}
	
	@Test
	public void testLoadCargo() throws DeliveryException {
		// load that fits
		
		truck = new OrdinaryTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < truck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		// load that does not fit
		
		truck = new OrdinaryTruck();
		
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
	public void testCapacityValues() {
		Truck truck = new OrdinaryTruck();
		
		assertEquals(1000, truck.getCargoCurrentCapacity());
		assertEquals(1000, truck.cargoMaxCapacity);
	}
	
	@Test
	public void testLoadCargoSingleItem() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Item item = new Item("name", 1, 2, 1, 1);
		
		truck.loadCargo(item);
	}
	
	@Test
	public void testLoadCargoSingleStock() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Stock stock = new Stock();
		
		truck.loadCargo(stock);
	}
	
	@Test
	public void testCargo() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Stock cargo = new Stock();
		
		for(int i = 50; i < truck.cargoMaxCapacity; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		truck.loadCargo(cargo);
		
//		assertEquals(truck.getCargo(), cargo);
		assertEquals(truck.getCargo().toString(), cargo.toString());
	}
	
	
	@Test
	public void testCargoCurrentCapacity() throws DeliveryException {	
		truck = new OrdinaryTruck();
		
		assertEquals(truck.getCargoCurrentCapacity(), 1000);  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCargoCurrentCapacity(), 1000 - 500);  // when containing
	}
	
	
	@Test
	public void testCost() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		assertEquals(750.0, truck.getCost(), 0.001);  // when empty
		
		Stock cargo = new Stock();
		
		for(int i = 0; i < 500; i++) {
			cargo.add(new Item("name", 1, 2, 1, 1));
		}
		
		truck.loadCargo(cargo);
		
		assertEquals(truck.getCost(), 750 + (0.25 * 500), 0.001);  // when containing
	}

}
