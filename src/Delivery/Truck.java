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
	protected int cargoMaxCapacity;
	
	/**
	 * Load an item into the Truck.
	 * 
	 * @param item to load into truck
	 * @throws DeliveryException if truck will exceed capacity.
	 */
	public void loadCargo(Item item) throws DeliveryException {
		if (getCargoCurrentCapacity() <= cargoMaxCapacity) {
			cargo.add(item);
		} else {
			throw new DeliveryException("Truck will exceed max capacity");
		}
	}
	
	
	/**
	 * Load multiple of the same item into the truck.
	 * 
	 * @param item
	 * @param quantity
	 * @throws DeliveryException if truck will exceed capacity.
	 */
	public void loadCargo(Item item, int quantity) throws DeliveryException {
		if (quantity + countItemsInCargo() > cargoMaxCapacity) {
			throw new DeliveryException("Truck will exceed max capacity");
		}
		for (int i=0; i < quantity; i++) {
			loadCargo(item);
		}
	}
	
	
	/**
	 * Load stock into the Truck.
	 *
	 * @param stock to load into truck.
	 * @throws DeliveryException if truck will exceed capacity.
	 */
	public void loadCargo(Stock stock) throws DeliveryException {
		if (stock.size() + countItemsInCargo() > cargoMaxCapacity) {
			throw new DeliveryException("Truck will exceeded max capacity");
		} else {
			for (Item item : stock.getItems()) {
				loadCargo(item);
			}
		}
	}
	
	
	
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
		return cargoMaxCapacity - cargo.size();
	}
	
	public int countItemsInCargo() {
		return cargo.size();
	}
	
	public Double getTemperature() {
		return null;
	}
	
	/**
	 * @return Cost of Truck in Dollars
	 */
	public abstract double getCost();
	
	
	/**
	 * @return Type of truck.
	 */
	public abstract String getTypeToString();
	 
	
	/**
	 * Return a string in the form of:
	 * 
	 * >truckType\n
	 * itemName, quantity\n
	 * itemName, quantity \n
	 * ...
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String nl = "\n";
		sb.append(">");
		sb.append(getTypeToString() + nl);
		sb.append(cargo.toString());
		return sb.toString();
	}
	
}
