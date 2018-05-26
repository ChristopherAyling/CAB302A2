/**
 * 
 */
package Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public void add(Item item, int quantity) {
		for(int i=0; i<quantity; i++) {
			add(item);
		}
	}
	
	public ArrayList<Item> getItems() {
		return items;
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
	
	public int count(Item itemToCount) {
		int itemCount = 0;
		for(Item item : items) {
			if(itemToCount.equals(item)) {
				itemCount++;
			}
		}
		return itemCount;
	}
	
	public Double getColdestItemTemperature() {
		Double coldestTemperature = Double.MAX_VALUE;
		for(Item item : items) {
			if (item.getTemperature() == null) {
				continue;
			} else if(item.getTemperature() < coldestTemperature) {
				coldestTemperature = item.getTemperature();
			}
		}
		if (coldestTemperature == Double.MAX_VALUE) {
			coldestTemperature = null;
		}
		return coldestTemperature;
	}
	
	public int size() {
		return items.size();
	}

	/**
	 * Return a string in the form of:
	 * 
	 * itemName, quantity\n
	 * itemName, quantity\n
	 * ...
	 * 
	 * @return String
	 */
	public String toString() {
		String nl = "\n";
		StringBuilder sb = new StringBuilder();
		Map<String, Integer> itemCounts = new HashMap<String, Integer>();
		for (Item item : items) {
			itemCounts.put(item.getName(), count(item));
//			itemCounts.put(item.getName(), itemCounts.get(item.getName())+1);
		}
		for (String key : itemCounts.keySet()) {
			sb.append(key);
			sb.append(",");
			sb.append(itemCounts.get(key));
			sb.append(nl);
		}
		return sb.toString();
	}
	
}
