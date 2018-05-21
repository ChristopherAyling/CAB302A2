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

	public int cargoMaxCapacity = 800;
	
	public RefrigeratedTruck() {
	}
	
	/**
	 * Load cargo into the truck. Items which require refrigration are allowed too. 
	 */
	public void loadCargo(Stock stock) {
		for (Item item : stock) {
			cargo.add(item);
		}
	}
	
	/**
	 * Calculate the cost of the truck using the following formula:
	 * 
	 * $$ 900 + 200*0.7^{\frac{t}{5}} $$
	 * 
	 * @return cost in dollars
	 */
	public double getCost() {
		return 900.0 + (200*java.lang.Math.pow(0.7, (this.getTemperature()/5.0)));
	}
	
	/**
	 * Returns the temperature in degrees celcius of the coldest item in the Truck. The
	 * temperature returned is in the R(-20.0, 10);
	 * 
	 * @return temperature (c)
	 */
	public double getTemperature(){
		return this.cargo.getColdestItemTemp();
	}

}
