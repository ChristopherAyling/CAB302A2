/**
 * 
 */
package Store;

import Stock.Stock;

public class Store {

	private static Store instance = new Store();
	
	private String name;
	private double capital;
	private Stock inventory;
	
	/**
	 * 
	 */
	private Store() {
		
	}
	
	public static Store getInstance() {
		return instance;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCapital(double capital) {
		this.capital = capital;
	}
	
	public void addCapital(double capital) {
		this.capital += capital;
	}
	
	public double getCapital() {
		return this.capital;
	}
	
	public Stock getInventory() {
		return this.inventory;
	}

	public String displayCapital() {
		return "Not Implemented";
	}
}
