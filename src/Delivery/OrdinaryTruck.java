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
	
	public OrdinaryTruck() {
		
	}
	
	/**
	 * Load stock into the truck. If an item requires refrigeration, raise
	 * an exception as refrigerated items may not be held by an OrdinaryTruck.
	 */
	public void loadCargo(Stock stock) throws DeliveryException {
		for (Item item : stock) {
			loadCargo(item);
		}
	}
	
	/**
	 * Load an item into the truck. If the item requires refrigeration, raise
	 * an exception as refigerated items may not be held by and OrdinaryTruck.
	 */
	public void loadCargo(Item item) throws DeliveryException {
		if (stock.requiresRefrigeration) {
			throw new DeliveryException();
		} else {
			cargo.add(item);
		}
	}
	
	public void loadCargo(Item item, int quantity) throws DeliveryException {
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
