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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import GUI.CSVFormatException;
import Stock.*;
import Store.*;

/**
 * A collection of trucks. Can be constructed manually or automaticall from a
 * CSV file or an instance of Store.Store. Manifests have the ability
 * to calculate their cost.
 * 
 * @author Christopher Ayling
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
	 * Constructor. Construct a new Manfest object which contains trucks that
	 * together contain the reorder amount of any items below their reorder point.
	 * 
	 * @param Store
	 *            to create shipment for
	 * @throws StockException
	 * @throws DeliveryException
	 */
	public Manifest(Store store) throws DeliveryException {
		Stock current = store.getInventory();
		Stock inNeedOf = new Stock();
		Stock properties = store.getItemProperties();

		// calculate needs
		for (Item item : properties.getItems()) { // for every possible item
			if (current.count(item) <= item.getReorderPoint()) { // if the amount of items in the inventory is less than
																	// the reorder point
				inNeedOf.add(item, item.getReorderAmount()); // add to inNeedOf
			}
		}

		// Sort items into fridge and ordinary
		Stock fridgeItems = new Stock();
		Stock ordItems = new Stock();
		for (Item item : inNeedOf.getItems()) {
			if (item.getTemperature() == null) {
				ordItems.add(item);
			} else {
				fridgeItems.add(item);
			}
		}

		Item coldestItem = null;
		Truck currentTruck = new RefrigeratedTruck();

		while (fridgeItems.size() > 0) { // fill trucks coldest item first
			coldestItem = fridgeItems.getColdestItem();
			while (fridgeItems.count(coldestItem) > 0) {
				try { // adding to current cold truck
					currentTruck.loadCargo(coldestItem);
					fridgeItems.remove(coldestItem, 1);
				} catch (DeliveryException e) { // if full
					trucks.add(currentTruck); // add to manifest
					currentTruck = new RefrigeratedTruck(); // make a new one
				}
			} // end while
		} // end while	

		while (ordItems.size() > 0) { // for every left over ordinary item
			try { // try adding it to current truck
				Item itemToAdd = ordItems.getItems().get(0);
				currentTruck.loadCargo(itemToAdd);
				ordItems.remove(itemToAdd, 1);
			} catch (DeliveryException e) { // if it is full, add it to manifest and make a new one.
				trucks.add(currentTruck);
				currentTruck = new OrdinaryTruck();
			}
		} // end while
		trucks.add(currentTruck);
	}

	
	/**
	 * Constructor. Construct a new Manifest object from the data contained in the
	 * file specified.
	 * 
	 * the CSV must be in the following form: >[truck type] [item],[quantity]
	 * [item],[quantity] >[truck type] [item],[quantity]
	 * 
	 * @param path
	 * 				path to a file containing manifest data.
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
				if (truck != null)
					trucks.add(truck);
				truck = new OrdinaryTruck();
				break;
			case ">Refrigerated":
				if (truck != null)
					trucks.add(truck);
				truck = new RefrigeratedTruck();
				break;
			default: // else add an item to a truck
				if (truck == null)
					throw new CSVFormatException(); // why is there not a truck specified??

				// parse the String
				String[] values = line.split(",");
				int quantity = Integer.parseInt(values[1]);
				String name = values[0];
				item = store.getItemProperties(name);

				// add to current truck's cargo
				for (int i = 0; i != quantity; i++) {
					truck.loadCargo(item);
				}
				break;
			}// end switch
		} // end while
		bufferedReader.close();
	}

	
	/**
	 * Add a truck to the manifest.
	 * 
	 * @param truck
	 */
	public void add(Truck truck) {
		trucks.add(truck);
	}

	
	/**
	 * Sums the cost of all trucks in the manifest.
	 * 
	 * @return cost of manifest in dollars
	 */
	public double getCost() {
		double cost = 0.0;
		for (Truck truck : trucks) {
			if (truck.countItemsInCargo() == 0)
				continue; // don't add trucks with no items in to the manifest
			cost += truck.getCost();
		}
		return cost;
	}

	
	/**
	 * Return a formatted string representing the cost of the truck.
	 * e.g. "$10.00" as opposed to "10.003829298"
	 * 
	 * @return formatted currency string
	 */
	public String displayCost() {
		return NumberFormat.getCurrencyInstance().format(getCost());
	}

	
	/**
	 * Return all the trucks in the manifest.
	 * 
	 * @return trucks 
	 * 				an ArrayList of all trucks in the manifest.
	 */
	public ArrayList<Truck> getTrucks() {
		return trucks;
	}

	
	/**
	 * Return a string representation of the manifest in the following form:
	 * <br>
	 * >[truck type]<br>
	 * [item],[quantity]<br>
	 * [item],[quantity]<br>
	 * >[truck type]<br>
	 * [item],[quantity]
	 * 
	 * @return String
	 *
	 */
	public String toString() {
		StringBuilder manifestSB = new StringBuilder();
		String nl = "\n";
		Map<String, Integer> itemCounts = new HashMap<String, Integer>();
		itemCounts.put("name", 7);
		boolean firstTruck = true;
		for (Truck truck : trucks) {
			if (truck.countItemsInCargo() == 0)
				continue; // don't add trucks with no items in to the manifest
			if (firstTruck) {
				manifestSB.append(truck.toString());
				firstTruck = false;
			} else {
				manifestSB.append(nl);
				manifestSB.append(truck.toString());
			}
		} // end for
		return manifestSB.toString();
	}

	
	/**
	 * Write the manifest to a CSV file using the following format:
	 * <br>
	 * >[truck type]<br>
	 * [item],[quantity]<br>
	 * [item],[quantity]<br>
	 * >[truck type]<br>
	 * [item],[quantity]
	 * 
	 * @param path
	 *            The path to save the file at.
	 */
	public void writeToCSV(String path) throws IOException {
		FileWriter writer = new FileWriter(path);
		writer.write(this.toString());
		writer.close();
	}

	
	/**
	 * Return the amount of trucks in the manifest.
	 * 
	 * @return size
	 */
	public int size() {
		return trucks.size();
	}
}
