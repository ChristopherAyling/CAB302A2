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
		if (stock.requiresRefrigeration) {
			throw new DeliveryException();
		} else {
			for (Item item : stock) {
				cargo.add(item);
			}
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
		return "Ordinarys";
	}
	
	

}
