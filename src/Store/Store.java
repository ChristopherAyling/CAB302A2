/**
 * 
 */
package Store;

import java.text.NumberFormat;

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
	
	public Double getCapital() {
		return this.capital;
	}
	
	public String displayCapital() {
		return NumberFormat.getCurrencyInstance().format(capital);
	}
	
	public Stock getInventory() {
		return this.inventory;
	}

}
