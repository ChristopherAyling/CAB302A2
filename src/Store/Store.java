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
	private double capital;
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
	
	public void setItemProperties(Item item) {
		this.itemProperties.add(item);
	}
	
	private Item copyItem(Item item) {
		return new Item(item.getName(), item.getManufacturingCost(), item.getSellPrice(), item.getReorderPoint(), item.getReorderAmount(), item.getTemperature());
	}
	
	public Item getItemProperties(String itemName) throws StockException {
		for(Item item : itemProperties.getItems()) {
			if(item.getName().equals(itemName)) return copyItem(item);
		}
		throw new StockException();
	}

	public String displayCapital() {
		return "Not Implemented";
	}
}
