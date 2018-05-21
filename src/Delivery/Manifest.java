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
	 */
	public Manifest(String path) throws IOException{
		// create empty stock object
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
				// get quantity
				int quantity = Integer.parseInt(values[1]);					
				// add to current truck's cargo
				truck.loadCargo(name, quantity);
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
