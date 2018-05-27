package Stock;

/**
* @author Lucas Wickham
*/
public class Item {

	private String name;
	private double manufacuringCost, sellPrice;
	private Double temperature;
	private int reorderPoint, reorderAmount;
	
	/**
	 * Item with temperature
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
	 * Item without temperature
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
		this.temperature = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Double getManufacturingCost() {
		return this.manufacuringCost;
	}
	
	public Double getSellPrice() {
		return this.sellPrice;
	}
	
	public Integer getReorderPoint() {
		return this.reorderPoint;
	}
	
	public Integer getReorderAmount() {
		return this.reorderAmount;
	}
	
	public boolean requiresTemperatureControl() {
		if (temperature == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Double getTemperature() {
		return this.temperature;
	}
	
	public boolean equals(Object object) {
		Item otherItem = (Item) object;
		if (getName().equals(otherItem.getName())) {
			return true;
		}
		else return false;
	}

}
