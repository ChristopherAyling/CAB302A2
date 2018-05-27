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
	 * 
	 * @throws DeliveryException 
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
	 * 
	 * @throws DeliveryException 
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
	 * 
	 * @throws DeliveryException
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
	 * 
	 * $$ 900 + 200*0.7^{\frac{t}{5}} $$
	 * 
	 * @return cost in dollars
	 */
	@Override
	public double getCost() {
		return cargo.getWholesaleCost() + 900.0 + (200*java.lang.Math.pow(0.7, (this.getTemperature()/5.0)));
	}
	
	
	
	/**
	 * Returns the temperature in degrees celcius of the coldest item in the Truck. The
	 * temperature returned is in the set R(-20.0, 10);
	 * 
	 * @return temperature (c)
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
