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
		int removed = 0;
		while (removed < number) {
			items.remove(itemToRemove);
			removed++;
		}
		
//		for(int i = 0; i < number; i++) {
//			for(Item item : items) {
//				if(itemToRemove.equals(item)) {
//					items.remove(item);
//				}
//			}
//		}
	}
	
	public void remove(Item itemToRemove) {
//		for(Item item : items) {
//			if(itemToRemove.equals(item)) {
//				items.remove(item);
//			}
//		}
//		
		while (items.contains(itemToRemove)) {
			items.remove(itemToRemove);
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
	
	public Item getColdestItem() {
		Item coldestItem = null;
		Double coldestTemperature = Double.MAX_VALUE;
		for(Item item : items) {
			if (item.getTemperature() == null) {
				continue;
			} else if(item.getTemperature() < coldestTemperature) {
				coldestTemperature = item.getTemperature();
				coldestItem = item;
			}
		}
		if (coldestTemperature == Double.MAX_VALUE) {
			coldestItem = null;
		}
		return coldestItem;
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
		if (items.size() == 0) { // if no items
			return "";
			
		} else {
			Map<String, Integer> itemCounts = new HashMap<String, Integer>();
			for (Item item : items) { // get counts of all items in stock
				itemCounts.put(item.getName(), count(item));
			}
			
			String nl = "\n";
			StringBuilder sb = new StringBuilder();
			for (String key : itemCounts.keySet()) { // create a representative string
				sb.append(key);
				sb.append(",");
				sb.append(itemCounts.get(key));
				sb.append(nl);
			}
			return sb.toString().substring(0, sb.length()-nl.length());
		}
	}
	
}
