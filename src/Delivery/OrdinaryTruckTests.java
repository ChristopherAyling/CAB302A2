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
	
	@Test(expected = DeliveryException.class)
	public void testLoadTooMuchCargo() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Item item = new Item("there's too many of me!", 1, 1, 1, 1);
		
		for(int i=-1000; i<truck.cargoMaxCapacity; i++) {
			truck.loadCargo(item);	
		}
	}
	
	@Test(expected = DeliveryException.class)
	public void testLoadNTooMuchCargo() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Item item = new Item("there's too many of me!", 1, 1, 1, 1);
		
		for(int i=-1000; i<truck.cargoMaxCapacity; i++) {
			truck.loadCargo(item, 1);	
		}
	}
	
	@Test(expected = DeliveryException.class)
	public void testLoadNManyTooMuchCargo() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Item item = new Item("there's too many of me!", 1, 1, 1, 1);
		
		truck.loadCargo(item, truck.cargoMaxCapacity+1);	
	}
	
	@Test(expected = DeliveryException.class)
	public void testLoadStockTooMuchCargo() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Item item = new Item("there's too many of me!", 1, 1, 1, 1);
		Stock stock = new Stock();
		
		for(int i=-1000; i<truck.cargoMaxCapacity; i++) {
			stock.add(item);
		}
		
		truck.loadCargo(stock);
	}
	
	@Test(expected = DeliveryException.class)
	public void testLoadMultipleStocksTooMuchCargo() throws DeliveryException {
		truck = new OrdinaryTruck();
		
		Item item = new Item("there's too many of me!", 1, 1, 1, 1);
		Stock stock = new Stock();
		stock.add(item);
		
		for(int i=0; i<truck.cargoMaxCapacity+1; i++) {
			truck.loadCargo(stock);
		}		
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
		
		assertEquals(cargo.getWholesaleCost() + 750 + (0.25 * 500), truck.getCost(), 0.001);  // when containing
	}

}
