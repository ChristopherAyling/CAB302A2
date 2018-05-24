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

	public static final int cargoMaxCapacity = 1000;
	
	/**
	 * Construct an Ordinary Truck.
	 */
	public OrdinaryTruck() {
		
	}
	
	// TODO change these methods to deal with possible changes to stock.
	
	/**
	 * Load an item into the truck. If the item requires refrigeration, raise
	 * an exception as refigerated items may not be held by and OrdinaryTruck.
	 */
	public void loadCargo(Item item) {
		cargo.add(item);
	}
	
	/**
	 * Load stock into the truck. If an item requires refrigeration, raise
	 * an exception as refrigerated items may not be held by an OrdinaryTruck.
	 */
	public void loadCargo(Stock stock) {
		for (Item item : stock.getItems()) {
			loadCargo(item);
		}
	}
	
	
	public void loadCargo(Item item, int quantity) {
		for (int i=0; i < quantity; i++) {
			loadCargo(item);
		}
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
	public double getCost() {
		return 750.0 + (0.25*this.getCargoCurrentCapacity());
	}
	
	
	public String getTypeToString() {
		return "Ordinary";
	}
	
	

}
