/** Truck.java
 * 
 * Truck abstract class.
 * 
 * Used 
 * 
 */
package Delivery;

import Stock.*;

public abstract class Truck {
	protected Stock cargo = new Stock();
	
	/**
	 * Load stock into the Truck.
	 *
	 * @param stock
	 */
	public abstract void loadCargo(Stock stock);
	
	
	
	/**
	 * Load an item into the Truck.
	 * 
	 * @param item to load into truck
	 */
	public abstract void loadCargo(Item item);
	
	
	/**
	 * Returns the cargo held in the Truck
	 * 
	 * @return cargo
	 */
	public Stock getCargo() {
		return cargo;
	}
	
	
	/**
	 * 
	 * @return Amount of items in the truck
	 */
	public int getCargoCurrentCapacity() { 
		return cargo.size();
	}
	
	/**
	 * @return Cost of Truck in Dollars
	 */
	public abstract double getCost();
	
	/**
	 * @return Type of truck.
	 */
	public abstract String getTypeToString();
}
