/**
 * 
 */
package Delivery;

/**
 * @author Chris
 *
 */
public class RefrigeratedTruck extends Truck {

	public int cargoMaxCapacity = 800;
	
	public RefrigeratedTruck() {
	}
	
	public double getCost() {
		return 900.0 + (200*java.lang.Math.pow(0.7, (this.getTemperature()/5.0)));
	}
	
	public double getTemperature(){
		return this.cargo.getColdestItemTemp();
	}
	

}
