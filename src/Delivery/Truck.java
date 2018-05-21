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
	private Stock cargo = new Stock();
	
	/**
	 * Load cargo into the Truck.
	 *
	 * @param stock
	 */
	public abstract void loadCargo(Stock stock);
	
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
		return cargo.count();
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
