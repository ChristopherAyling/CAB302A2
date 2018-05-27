/**
 * 
 */
package Store;

import java.text.NumberFormat;

import Stock.Item;
import Stock.Stock;
import Stock.StockException;

public class Store {

	private static Store instance;
	
	private String name;
	private double capital = 100000;
	private Stock inventory= new Stock();
	private Stock itemProperties = new Stock();
	
	/**
	 * 
	 */
	protected Store() {
	}
	
	public static Store getInstance() {
		if (instance == null) {
			instance = new Store();
		}
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
		this.capital = this.capital + capital;
	}
	
	public double getCapital() {
		return this.capital;
	}
	
	public String displayCapital() {
		return NumberFormat.getCurrencyInstance().format(capital);
	}
	
	public Stock getInventory() {
		return this.inventory;
	}
	
	public void setItemProperties(Item item) {
		this.itemProperties.add(item);
	}
	
	protected Item copyItem(Item item) {
		if (item.getTemperature() == null) {
			return new Item(item.getName(), item.getManufacturingCost(), item.getSellPrice(), item.getReorderPoint(), item.getReorderAmount());
		}
		return new Item(item.getName(), item.getManufacturingCost(), item.getSellPrice(), item.getReorderPoint(), item.getReorderAmount(), item.getTemperature());
	}
	
	public Item getItemProperties(String itemName) throws StockException {
		for(Item item : itemProperties.getItems()) {
			if(item.getName().equals(itemName)) return copyItem(item);
		}
		throw new StockException("Item with name: " + itemName + " does not have properties loaded");
	}
	
	public Stock getItemProperties() {
		return itemProperties;
	}

}
