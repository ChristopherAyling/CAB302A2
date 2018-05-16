/**
 * 
 */
package Stock;

import java.math.BigDecimal;

public class Item {

	private String name;
	private double manufacuringCost, sellPrice, temperature;
	private int reorderPoint, reorderAmount;
	
	/**
	 * Item with temperture
	 * @param name
	 * @param manufacturingCost
	 * @param sellPrice
	 * @param reorderPoint
	 * @param reorderAmount
	 * @param temperature
	 */
	public Item(String name, double manufacturingCost, double sellPrice, int reorderPoint, int reorderAmount, double temperature) {
		this.name = name;
		this.manufacuringCost = manufacturingCost;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temperature = temperature;
	}
	
	/**
	 * Item without temperture
	 * @param name
	 * @param manufacturingCost
	 * @param sellPrice
	 * @param reorderPoint
	 * @param reorderAmount
	 */
	public Item(String name, double manufacturingCost, double sellPrice, int reorderPoint, int reorderAmount) {
		this.name = name;
		this.manufacuringCost = manufacturingCost;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temperature = 10;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getManufacturingCost() {
		return this.manufacuringCost;
	}
	
	public double getSellPrice() {
		return this.sellPrice;
	}
	
	public int getReorderPoint() {
		return this.reorderPoint;
	}
	
	public int getReorderAmount() {
		return this.reorderAmount;
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
	public boolean equals(Object object) {
		Item otherItem = (Item) object;
		if(this.getName() == otherItem.getName() && 
				this.getManufacturingCost() == otherItem.getManufacturingCost() && 
				this.getSellPrice() == otherItem.getSellPrice() && 
				this.getReorderPoint() == otherItem.getReorderPoint() &&
				this.getReorderAmount() == otherItem.getReorderAmount() && 
				this.getTemperature() == otherItem.getTemperature()) {
			return true;
		}
		return false;
	}

}
