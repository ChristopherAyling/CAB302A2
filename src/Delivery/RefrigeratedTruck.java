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

	public RefrigeratedTruck() {
		cargoMaxCapacity = 800;
	}
	
	/**
	 * 
	 * @throws DeliveryException 
	 */
	public void loadCargo(Item item) throws DeliveryException {
		if (item.getTemperature() > 10 | item.getTemperature() < -20) {
			throw new DeliveryException("Items which require temperature control may not be loaded into an instance of OrdinayTruck, try using RefrigeratedTruck instead");
		}
		super.loadCargo(item);
	}
	
	
	/**
	 * 
	 * @throws DeliveryException 
	 */
	@Override
	public void loadCargo(Stock stock) throws DeliveryException {
		if (stock.getColdestItemTemperature() > 10 | stock.getColdestItemTemperature() < -20) {
			throw new DeliveryException("Items which require temperature control may not be loaded into an instance of OrdinayTruck, try using RefrigeratedTruck instead");
		}
		super.loadCargo(stock);
	}
	
	
	/**
	 * 
	 * @throws DeliveryException
	 */
	@Override
	public void loadCargo(Item item, int quantity) throws DeliveryException {
		if (item.getTemperature() > 10 | item.getTemperature() < -20) {
			throw new DeliveryException("Items which require temperature control may not be loaded into an instance of OrdinayTruck, try using RefrigeratedTruck instead");
		}
		super.loadCargo(item, quantity);
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
		return 900.0 + (200*java.lang.Math.pow(0.7, (this.getTemperature()/5.0)));
	}
	
	
	/**
	 * Returns the temperature in degrees celcius of the coldest item in the Truck. The
	 * temperature returned is in the R(-20.0, 10);
	 * 
	 * @return temperature (c)
	 */
	@Override
	public Double getTemperature(){
		return this.cargo.getColdestItemTemperature();
	}
	
	@Override
	public String getTypeToString() {
		return "Refrigerated";
	}
}
