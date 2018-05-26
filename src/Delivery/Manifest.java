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
import java.util.Map;

import GUI.CSVFormatException;
import Stock.*;
import Store.*;

/**
 * @author Chris
 *
 */
public class Manifest {

	Store store = Store.getInstance();
	
	private ArrayList<Truck> trucks = new ArrayList<Truck>(); 
	
	/**
	 * Constructor. Construct a new Manifest object.
	 * 
	 */
	public Manifest() {

	}
	
	public Manifest(Store store) throws StockException {
		// calculate needs
		Stock current = store.getInventory();
		Stock inNeedOf = new Stock();
		Stock properties = store.getItemProperties();
		for (Item item : properties.getItems()) {
			if (current.count(item) <= item.getReorderPoint()) {
				inNeedOf.add(item, item.getReorderAmount());
			}
		}
		// optimise shipment
		Truck fridge = new RefrigeratedTruck();
		Truck ord = new OrdinaryTruck();
		for (Item item : inNeedOf.getItems()) {
			if (item.getTemperature() < 10) {
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
	 *
	 */
	public Manifest(String path) throws IOException, CSVFormatException, StockException {
		Truck truck = null;
		Item item;
		
		FileReader reader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		
		while ((line = bufferedReader.readLine()) != null) {
			switch (line) { // make a truck
			case ">Ordinary":
				truck = new RefrigeratedTruck();
				break;
			case ">Refrigerated":
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
	 * @return a collection of strings containing the names of the items
	 * in the trucks in the manifest.
	 * 
	 * @deprecated
	 */
	public ArrayList<String> getItems(){
		ArrayList<String> itemNames =  new ArrayList<String>();
		for (Truck truck : trucks) {
			for (Item item : truck.getCargo().getItems()) {
				itemNames.add(item.getName());
			}
		}
		return itemNames;
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
//		String nl = System.getProperty("line.seperator");
		String nl = "\n";
		StringBuilder manifestSB = new StringBuilder();
		Map<String, Integer> itemCounts = new HashMap<String, Integer>();
		itemCounts.put("name", 7);
		for (Truck truck : trucks) {
			manifestSB.append(truck.toString());
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
