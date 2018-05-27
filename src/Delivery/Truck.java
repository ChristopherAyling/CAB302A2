package Delivery;

import Stock.*;

/**
 * Truck abstract class. Can hold items and calculate a cost.
 * 
 * @author Chris
 *
 */
public abstract class Truck {
	protected Stock cargo = new Stock();
	protected int cargoMaxCapacity;

	/**
	 * Load an item into the Truck.
	 * 
	 * @param Ttem
	 *            to load into the truck.
	 * @throws DeliveryException
	 *             if the truck will exceed capacity.
	 */
	public void loadCargo(Item item) throws DeliveryException {
		if (cargo.size() < cargoMaxCapacity) {
			cargo.add(item);
		} else {
			throw new DeliveryException("Truck will exceed max capacity");
		}
	}

	/**
	 * Load multiple of the same item into the truck.
	 * 
	 * @param item
	 *            To load into the truck
	 * @param quantity
	 *            Quantity of specified item to be added to the truck.
	 * @throws DeliveryException
	 *             if truck will exceed capacity.
	 */
	public void loadCargo(Item item, int quantity) throws DeliveryException {
		if (quantity < 0) {
			return;
		} else if (quantity + countItemsInCargo() > cargoMaxCapacity) {
			throw new DeliveryException("Truck will exceed max capacity");
		}
		for (int i = 0; i < quantity; i++) {
			loadCargo(item);
		}
	}

	/**
	 * Load stock into the Truck.
	 *
	 * @param Stock
	 *            to load into truck.
	 * @throws DeliveryException
	 *             if truck will exceed capacity.
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

	/**
	 * Return the temperature the truck will maintain (if any).
	 * 
	 * @return temperature in degrees celcius or null.
	 */
	public Double getTemperature() {
		return null;
	}

	/**
	 * Calculate and return the cost of the truck in dollars.
	 * 
	 * @return Cost of Truck in Dollars
	 */
	public abstract double getCost();

	/**
	 * String representation of the type of truck of the concrete class.
	 * 
	 * @return String
	 */
	public abstract String getTypeToString();

	/**
	 * Return a string in the form of: <br>
	 * >truckType <br>
	 * itemName, quantity <br>
	 * itemName, quantity <br>
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
