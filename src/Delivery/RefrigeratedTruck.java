/** RefrigeratedTruck.Java
 * 
 * Extends Truck.java
 * 
 * Implements getCost as per refrigerated truck specifications.
 * 
 * Refrigerated trucks also have a temaerature which is equal to either the coldest item in
 * its cargo but is bound at -20.0 and 10.0. This temperature can be calculated using
 * getTermperature. 
 * 
 */
package Delivery;

import Stock.*;

/**
 * RefrigeratedTruck concrete class of abstract Truck. Can hold items which
 * require temperature control between -20 and 10 degrees celsius.
 * 
 * @author Chris
 *
 */
public class RefrigeratedTruck extends Truck {
	public final double maxTemp = 10;
	public final double minTemp = -20;

	public RefrigeratedTruck() {
		cargoMaxCapacity = 800;
	}
	
	
	/**
	 * Load an item into the truck. If the item's required temperature
	 * is not in the safe range a DeliveryException will be thrown.
	 * 
	 * @throws DeliveryException If the item requires a temperature outside the safe range.
	 */
	@Override
	public void loadCargo(Item item) throws DeliveryException {

		if ((item.getTemperature() == null) || (item.getTemperature() <= maxTemp) && (item.getTemperature() >= minTemp)) {
			super.loadCargo(item);
		} else {
			throw new DeliveryException("Item's temperature <" + item.getTemperature() + "> is outside RefrigeratedTruck's safe range: <" + minTemp + "> to <" + maxTemp + ">");			
		}
	}
	
	
	/**
	 * Load all items in a stock in the trcuk. If the item's required
	 * temperature is not in the safe range a DeliveryException will
	 * be thrown.
	 * 
	 * @throws DeliveryException If an item requires a temperature outside the safe range.
	 */
	@Override
	public void loadCargo(Stock stock) throws DeliveryException {
		if ((stock.getColdestItemTemperature() == null) || (stock.getColdestItemTemperature() <= maxTemp) && (stock.getColdestItemTemperature() >= minTemp)) {
			super.loadCargo(stock);
		} else {
			throw new DeliveryException("Item's temperature <" + stock.getColdestItemTemperature() + "> is outside RefrigeratedTruck's safe range: <" + minTemp + "> to <" + maxTemp + ">");
		}
	}
	
	
	/**
	 * Load an item into the truck. If the item's required temperature
	 * is not in the safe range a DeliveryException will be thrown.
	 * 
	 * @throws DeliveryException If the item requires a temperature outside the safe range.
	 */
	@Override
	public void loadCargo(Item item, int quantity) throws DeliveryException {
		if ((item.getTemperature() == null) || (item.getTemperature() <= maxTemp) && (item.getTemperature() >= minTemp)) {
			super.loadCargo(item, quantity);
		} else {
			throw new DeliveryException("Item's temperature <" + item.getTemperature() + "> is outside RefrigeratedTruck's safe range: <" + minTemp + "> to <" + maxTemp + ">");
		}
	}

	
	/**
	 * Calculate the cost of the truck using the following formula:
	 * <br>
	 * w + 900 + 200*0.7^(t/5)
	 * <br>
	 * Where w is the wholesale cost of all items in the truck and t
	 * is the temperature the truck maintains.
	 * 
	 * @return cost in dollars
	 */
	@Override
	public double getCost() {
		return cargo.getWholesaleCost() + 900.0 + (200*java.lang.Math.pow(0.7, (this.getTemperature()/5.0)));
	}
	
	
	/**
	 * Returns the temperature in degrees celcius that maintains a safe
	 * temperature for the trucks cargo. Equal to the temperature of
	 * the item in the cargo with the lowest temperature and in the
	 * range from -20 to 10 inclusive.
	 * 
	 * @return temperature In degrees celcius.
	 */
	@Override
	public Double getTemperature(){
		if (cargo.getColdestItemTemperature() == null) {
			return maxTemp;
		} else {
			return cargo.getColdestItemTemperature();
		}
	}
	
	
	@Override
	public String getTypeToString() {
		return "Refrigerated";
	}
}
