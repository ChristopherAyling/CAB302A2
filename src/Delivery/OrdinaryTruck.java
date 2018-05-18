/**
 * 
 */
package Delivery;


/**
 * @author Chris
 *
 */
public class OrdinaryTruck extends Truck {

	public static final int cargoMaxCapacity = 1000;
	
	public OrdinaryTruck() {
		
	}
	
	
	public double getCost() {
		return 750.0 + (0.25*this.getCargoCurrentCapacity());
	}

}
