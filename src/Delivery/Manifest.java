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

import GUI.CSVFormatException;
import Stock.*;

import Stock.Item;

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
	 * @deprecated
	 */
	public Manifest(String path) throws IOException, CSVFormatException {
		Truck truck;
		Item item;
		
		FileReader reader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while (line != null) {
			switch (line) {
			case ">Ordinary": // make a new truck
				truck = new RefrigeratedTruck();
				break;
			case ">Refrigerated": // make a new truck
				truck = new OrdinaryTruck();
				break;
			default: // add item to current truck
				String[] values = line.split(",");
				// get item name
				String name = values[0];
				// create Item instance
//				Item item = new Item();
				// get quantity
				int quantity = Integer.parseInt(values[1]);					
				// add to current truck's cargo
				break;
			}// end switch
		}//end while
		bufferedReader.close();
	}
	

	/**
	 * Construct a manifest that minimizes the cost of reordering
	 * the missing items.
	 * 
	 * @param stock which needs restocking
	 */
	public Manifest(Stock toRestock) {
		// create an optimal manifest
		
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
	 */
	public ArrayList<String> getItems(){
		ArrayList<String> itemNames =  new ArrayList<String>();
		for (Truck truck : trucks) {
			for (Item item : truck.getCargo()) {
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
		String nl = System.getProperty("line.seperator");
		StringBuilder manifestSB = new StringBuilder();
		for (Truck truck : trucks) {
			manifestSB.append(">"+truck.getTypeToString()+nl);
			for (Item item : truck.getCargo()) {
				String itemName = item.getName();
				int quantity = truck.getCargo().getQuantity(itemName);
				manifestSB.append(itemName + "," + quantity + nl);
			}//end for
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
