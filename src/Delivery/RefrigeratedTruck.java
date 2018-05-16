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
	private Stock cargo;
	
	public RefrigeratedTruck() {
	}
	
	public void loadCargo(Stock stock) {
		this.cargoMaxCapacity = stock;
	}
	
	public Stock getCargo() {
		return this.cargo;
	}
	
	public int getCurrentCargoCapacity() {
		return this.cargo.count();
	}
	
	public double getCost() {
		return 900.0 + (200*java.lang.Math.pow(0.7, (this.getTemperature()/5.0)));
	}
	
	public double getTemperature(){
		return this.cargo.getColdestItemTemp();
	}
	

}
