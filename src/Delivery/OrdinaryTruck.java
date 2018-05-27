/**
 * 
 */
package Delivery;

import Stock.*;

/**
 * @author Chris
 *
 */
public class OrdinaryTruck extends Truck {

//	public static final int cargoMaxCapacity = 1000;
	
	/**
	 * Construct an Ordinary Truck.
	 */
	public OrdinaryTruck() {
		cargoMaxCapacity = 1000;
	}
		
	/**
	 * Load an item into the truck. If the item requires refrigeration, raise
	 * an exception as refrigerated items may not be held by and OrdinaryTruck.
	 * @throws StockException 
	 */
	@Override
	public void loadCargo(Item item) throws DeliveryException {
		if (item.getTemperature() != null) {
			throw new DeliveryException("Items which require temperature control may not be loaded into an instance of OrdinayTruck, try using RefrigeratedTruck instead\n" +
										"Item temperature: " + item.getTemperature() +
										"\nItem name: " + item.getName()
										);
		}
		super.loadCargo(item);
	}
	
	
	/**
	 * Load stock into the truck. If an item requires refrigeration, raise
	 * an exception as refrigerated items may not be held by an OrdinaryTruck.
	 * @throws StockException 
	 */
	@Override
	public void loadCargo(Stock stock) throws DeliveryException {
		if (stock.getColdestItemTemperature() != null) {
			throw new DeliveryException("Items which require temperature control may not be loaded into an instance of OrdinayTruck, try using RefrigeratedTruck instead");
		}
		super.loadCargo(stock);
	}
	
	
	/**
	 * Load multiple of the same item into the truck. if the item
	 * requires temperature control a DeliveryException will be thrown.
	 */
	@Override
	public void loadCargo(Item item, int quantity) throws DeliveryException {
		if (item.getTemperature() != null) {
			throw new DeliveryException("Items which require temperature control may not be loaded into an instance of OrdinayTruck, try using RefrigeratedTruck instead");
		}
		super.loadCargo(item, quantity);
	}

	
	/**
	 * Calculate and return the cost of the truck using the
	 * following formula:
	 * 
	 * (Where q is the amount of items in the truck.)
	 * 
	 * 750 + 0.25q
	 * 
	 * @return cost of truck in Dollars
	 */
	@Override
	public double getCost() {
		return cargo.getWholesaleCost() + 750.0 + (0.25*this.countItemsInCargo());
	}
	
	@Override
	public String getTypeToString() {
		return "Ordinary";
	}
	
	

}
