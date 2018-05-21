/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public static Store store = new Store();
	
	public UserInterface() {
	}
	
	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//Create and set up the window. 
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // create top level panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // create and add buttons to panel
        JPanel topPanel = new JPanel(new FlowLayout());
        
        // add captial label
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
				// TODO Export Manifest
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
				// TODO Load Manifest
				System.out.println("Load manifest button clicked");
			}
		});
        
        JButton loadSalesLogButton = new JButton("Load Sales Log");
        loadSalesLogButton.addActionListener(new ActionListener() {
        	
        	/**
        	 * Load in a sales log; increases capital and decreases inventory.
        	 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Load Sales
				System.out.println("Load Sales Log button clicked");
			}
		});
        
        topPanel.add(loadItemPropertiesButton);
        topPanel.add(exportManifestButton);
        topPanel.add(loadManfestButton);
        topPanel.add(loadSalesLogButton);
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
//        frame.setLocation(new Point(200, 200));
        frame.pack(); 
        frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main Started");
		createAndShowGUI();
	}

}
