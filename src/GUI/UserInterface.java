/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EventListener;

import Store.*;
import Stock.*;
import Delivery.*;

import javax.swing.*;

/**
 * 
 * User Interface for a supermarket inventory system
 * 
 * @author Chris and Lucas
 *
 */
public class UserInterface {

	public static Store store = Store.getInstance();
	
	/**
	 * Constructor
	 */
	public UserInterface() {
	}
	
	
	
	/**
	 * Create the show the graphical user interface.
	 */
	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//Create and set up the window. 
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // create top level panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // create and add buttons to panel
        JPanel topPanel = new JPanel(new FlowLayout());
        
        // add capital label
        JLabel capitalLabel = new JLabel("Capital: " + store.displayCapital());
        topPanel.add(capitalLabel);

        //add buttons
        JButton loadItemPropertiesButton = new JButton("Load Item Properties");
        loadItemPropertiesButton.addActionListener(new ActionListener() {
        	
        	/**
        	 * Initialises item properties.
        	 * 
        	 * All item quantities must be zero.
        	 * 
        	 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Load Item Properties
				System.out.println("Item properties button clicked");				
			}
		});
        
        JButton exportManifestButton = new JButton("Export Manifest");
        exportManifestButton.addActionListener(new ActionListener() {
        	
        	/**
        	 * Export a manifest based on current inventory.
        	 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//find all items where amount in stock is less than reorder point
				//create manifest to order the the reorder quantity of each item which minimizes cost
				//save as a CSV to a location of the user's choice
				
				// TODO Create an optimised manifest object from current inventory.
					// TODO get
				
				// TODO User selects location to save manifest to.
				FileDialog fd = new FileDialog(frame, "Export Manifest", FileDialog.SAVE);
				fd.setVisible(true);
				String path = fd.getFile();
				// TODO Write manifest to CSV and save to location.
				Manifest manifest = new Manifest();
				try {
					manifest.writeToCSV(path);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "Error writing manifest to CSV");
				}
				
				System.out.println("Export manifest button clicked");
			}
		});
        
        JButton loadManifestButton = new JButton("Load Manifest");
        loadManifestButton.addActionListener(new ActionListener() {
			
        	/**
        	 * Load in a manifest; decreases capital and increases inventory.
        	 */
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Load manifest button clicked");
				// present user with file selection dialog
				FileDialog fd = new FileDialog(frame, "Load Manifest", FileDialog.SAVE);
				fd.setVisible(true);
				String path = fd.getFile();
				
				Truck truck;
				Item item;
				Stock stock;
				
				FileReader reader = new FileReader(path);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line;
				
				Manifest manifest = new Manifest();
				
				java.util.List<String> rows;
				try {
					rows = Files.readAllLines(Paths.get(path));
					for (int i=0; i<rows.size(); i++) {
						switch (rows.get(i)) {
						case ">Ordinary":
							truck = new OrdinaryTruck();
							for (; i<rows.size(); i++) {
								
							}
							break;
						case ">Refrigerated":
							truck = new RefrigeratedTruck();
							break;
						default:
							break;
						}
					}
				} catch (IOException e3) {
					e3.printStackTrace();
				}
				
				
				
				try {
					
					for (String row : Files.readAllLines(Paths.get(path))) {
						stock = new Stock();
						// create a new truck
						switch (row) {
						case ">Ordinary":
							truck = new OrdinaryTruck();
							break;
						case ">Refrigerated":
							truck = new RefrigeratedTruck();
							break;
						default:
							break;
						}
						// get the items
						String[] itemData = row.split(",");
						item = new Item("test", 10, 1, 10, 10);
						store.getInventory().add(item);
						stock.add(item);
					}// end for
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "IOException");
				} catch (CSVFormatException e2){
					JOptionPane.showMessageDialog(frame, "CSV is in the wrong format");
				}
				
				double cost = 0;
				while ((line = bufferedReader.readLine()) != null) {
					stock = new Stock();
					switch (line) { // create a truck
					case ">Ordinary":
						truck = new OrdinaryTruck();
						break;
					case ">Refrigerated":
						truck = new RefrigeratedTruck();
						break;
					default:
						JOptionPane.showMessageDialog(frame, "Your CSV is in the wrong format!");
						break;					
					}//end switch
					
					while ((line = bufferedReader.readLine()) != null) {// fill the truck
						String[] itemData = line.split(","); // itemData[0] is name and itemData[1] is quantity
//						item = store.getItem(itemData[0]);
						item = new Item("test", 10.0, 0.1, 10, 20);
						store.getInventory().add(item);
						stock.add(item);
					}//end while
					truck.loadCargo(stock);
					cost += truck.getCost();
					store.addCapital(-cost);
					capitalLabel.setText("Capital: " + store.displayCapital());
				}//end while
				bufferedReader.close();
				
				// update capital shown in GUI
				capitalLabel.setText("Capital: " + store.displayCapital());
				// update item quantities in GUI
				
				frame.repaint();			
			}//end action performed
		});
        
        JButton loadSalesLogButton = new JButton("Load Sales Log");
        loadSalesLogButton.addActionListener(new ActionListener() {
        	
        	/**
        	 * Load in a sales log; increases capital and  inventory.
        	 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Load Sales
				System.out.println("Load Sales Log button clicked");
				
				store.addCapital(100);
				capitalLabel.setText("10000000000");
				frame.repaint();
			}
		});
        
        topPanel.add(loadItemPropertiesButton);
        topPanel.add(exportManifestButton);
        topPanel.add(loadManifestButton);
        topPanel.add(loadSalesLogButton);
        exportManifestButton.setEnabled(false);
        loadManifestButton.setEnabled(false);
//        loadSalesLogButton.setEnabled(false);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // create and add table to centre
        String[] columnNames = {"Name", "Quantity", "Manufactuing Cost", "Sell Price", "Reorder Point", "Reorder Amount", "Temperature"};
        JTable table = new JTable(3, 8); //TODO initialize table with columns and empty rows
        mainPanel.add(table, BorderLayout.CENTER);// TODO put it in a scroll pane
        
        //add panel to gui
        frame.getContentPane().add(mainPanel);
        frame.pack();
        
        //Display the window. 
        frame.setPreferredSize(new Dimension(800, 800)); //TODO find better dimensions
        frame.pack(); 
        frame.setVisible(true);
	}

	/**
	 * The entry point of the program. This launches the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main Started");
		createAndShowGUI();
	}

}
