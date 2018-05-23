/**
 * 
 */
package Stock;

import java.util.ArrayList;

public class Stock {

	private ArrayList<Item> items = new ArrayList<Item>();
	
	/**
	 * 
	 */
	public Stock() {
		
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public void remove(Item itemToRemove, int number) {
		for(int i = 0; i < number; i++) {
			for(Item item : items) {
				if(itemToRemove.equals(item)) {
					items.remove(item);
				}
			}
		}
	}
	
	public void remove(Item itemToRemove) {
		for(Item item : items) {
			if(itemToRemove.equals(item)) {
				items.remove(item);
			}
		}
	}
	
	public Integer count(Item itemToCount) {
		int itemCount = 0;
		for(Item item : items) {
			if(itemToCount.equals(item)) {
				itemCount++;
			}
		}
		return itemCount;
	}
	
	public void getColdestItemTemperature() {
		double coldestTemperature = Double.MAX_VALUE;
		for(Item item : items) {
			if(item.getTemperature() < coldestTemperature) {
				coldestTemperature = item.getTemperature();
			}
		}
	}

}
