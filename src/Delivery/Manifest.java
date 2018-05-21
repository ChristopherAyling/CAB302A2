/** Manifest.java
 * 
 * A collection of Trucks.
 * 
 */
package Delivery;
import java.util.ArrayList;

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
	 * @param path patht to a file containing manifest data.
	 */
	public Manifest(String path) {
		
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
	public void writeToCSV(String path) {
		// create string to write to CSV
		StringBuilder manifestSB = new StringBuilder();
		for (Truck truck : trucks) {
			manifestSB.append(">"+truck.getTypeToString());
			for (Item item : truck.getCargo()) {
				String itemName = item.getName();
				int quantity = truck.getCargo().getQuantity(itemName);
				manifestSB.append(itemName + "," + quantity);
			}//end for
		}//end for
		
		// save as a file.
	}
}
