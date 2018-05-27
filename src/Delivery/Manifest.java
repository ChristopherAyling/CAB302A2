/** Manifest.java
 * 
 * A collection of Trucks.
 * 
 */
package Delivery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI.CSVFormatException;
import Stock.*;
import Store.*;

/**
 * @author Chris
 *
 */
public class Manifest {
	
	private ArrayList<Truck> trucks = new ArrayList<Truck>(); 
	
	/**
	 * Constructor. Construct a new Manifest object.
	 * 
	 */
	public Manifest() {

	}
	
	
	/**
	 * Constructor. Construct a new Manfest object which contains trucks
	 * that together contain the reorder amount of any items below their
	 * reorder point.
	 * 
	 * @param Store to create shipment for
	 * @throws StockException
	 * @throws DeliveryException 
	 */
	public Manifest(Store store) throws DeliveryException {
		Stock current = store.getInventory();
		Stock inNeedOf = new Stock();
		Stock properties = store.getItemProperties();
		
		// calculate needs
		for (Item item : properties.getItems()) { // for every possible item
			if (current.count(item) <= item.getReorderPoint()) { // if the amount of items in the inventory is less than the reorder point
//				System.out.println(item.getName() + " count: " + current.count(item) + " roa: " + item.getReorderAmount());
				inNeedOf.add(item, item.getReorderAmount()); // add to inNeedOf
			}
		}
		
		// TODO optimize shipment
		
		// Sort items into fridge and ord
		Stock fridgeItems = new Stock();
		Stock ordItems = new Stock();
		for (Item item : inNeedOf.getItems()) {
			if (item.getTemperature() == null) {
				ordItems.add(item);
			} else {
				fridgeItems.add(item);
			}
		}
		
		// Sort fridge items from coldest to warmest
		Stock sortedFridgeItems = new Stock();
		Item coldestItem = null;
		while (fridgeItems.size() > 0) {
			coldestItem = fridgeItems.getColdestItem();
			sortedFridgeItems.add(coldestItem);
			fridgeItems.remove(coldestItem, 1);
		}
		
		//DEBUG
		System.out.println(sortedFridgeItems.toString());
		System.out.println("--Sorted fridge items ^^");
		
		
		// Concat lists
		List<Item> all = new ArrayList<>(sortedFridgeItems.getItems());
		all.addAll(ordItems.getItems());
		Stock toRefill = new Stock();
		for (Item item : all) {
			toRefill.add(item);
		}
		
		//DEBUG
		System.out.println(inNeedOf.toString());
		System.out.println("--In need of ^^");
		System.out.println(toRefill.toString());
		System.out.println("--to Refill ^^");
		
		
		Truck fridge = new RefrigeratedTruck();
		Truck ord = new OrdinaryTruck();
		for (Item item : inNeedOf.getItems()) {
			if (item.getTemperature() != null) {
				fridge.loadCargo(item);
			} else {
				ord.loadCargo(item);
			}
		}
		
		trucks.add(ord);
		trucks.add(fridge);
	}
	
	/**
	 * Constructor. Construct a new Manifest object from the
	 * data contained in the file specified.
	 * 
	 * the CSV must be in the following form:
	 * >[truck type]
     * [item],[quantity]
     * [item],[quantity]
     * >[truck type]
     * [item],[quantity]
	 * 
	 * @param path path to a file containing manifest data.
	 * @throws StockException 
	 * @throws DeliveryException 
	 *
	 */
	public Manifest(String path) throws IOException, CSVFormatException, StockException, DeliveryException {
		Store store = Store.getInstance();
		Truck truck = null;
		Item item;
		
		FileReader reader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		
		while ((line = bufferedReader.readLine()) != null) {
			switch (line) { // make a truck
			case ">Ordinary":
				if (truck != null) trucks.add(truck);
				truck = new RefrigeratedTruck();
				break;
			case ">Refrigerated":
				if (truck != null) trucks.add(truck);
				truck = new OrdinaryTruck();
				break;
			default: // else add an item to a truck
				if (truck == null) throw new CSVFormatException(); // why is there not a truck specified??
				
				// parse the String
				String[] values = line.split(",");
				int quantity = Integer.parseInt(values[1]);
				String name = values[0];
				item = store.getItemProperties(name);
				
				// add to current truck's cargo
				for (int i=0; i!=quantity; i++) {
					truck.loadCargo(item);
				}
				break;
			}// end switch
		}//end while
		bufferedReader.close();
	}
		
	/**
	 * Add a truck to the manifest.
	 * @param truck
	 */
	public void add(Truck truck) {
		trucks.add(truck);
	}
	
	
	/**
	 * Sums the cost of all trucks in the manifest.
	 * @return cost of manifest in dollars
	 */
	public double getCost() {
		double cost = 0.0;
		for (Truck truck : trucks) {
			cost += truck.getCost();
		}
		return cost;
	}
	
	public ArrayList<Truck> getTrucks(){
		return trucks;
	}
	
	/**
	 * Return a string representation of the manifest in the following form:
	 *
	 * >[truck type]
     * [item],[quantity]
     * [item],[quantity]
     * >[truck type]
     * [item],[quantity]
	 *
	 */
	public String toString() {
		StringBuilder manifestSB = new StringBuilder();
		String nl = "\n";
		Map<String, Integer> itemCounts = new HashMap<String, Integer>();
		itemCounts.put("name", 7);
		boolean firstTruck = true;
		for (Truck truck : trucks) {
			if (firstTruck) {
				manifestSB.append(truck.toString());
				firstTruck = false;
			} else {
				manifestSB.append(nl);
				manifestSB.append(truck.toString());
			}
		}//end for
		return manifestSB.toString();
	}
	

	/**
	 * Write the manifest to a CSV file using the following format:
	 * 
	 * >[truck type]
     * [item],[quantity]
     * [item],[quantity]
     * >[truck type]
     * [item],[quantity]
	 * 
	 * @param path the path to save the file at.
	 */
	public void writeToCSV(String path) throws IOException {
		FileWriter writer = new FileWriter(path);
		writer.write(this.toString());
		writer.close();
	}
	
	public int size() {
		return trucks.size();
	}
}
