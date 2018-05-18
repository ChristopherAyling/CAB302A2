/**
 * 
 */
package GUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Chris and Lucas
 *
 */
public class UserInterface {

	/**
	 * 
	 */
	public UserInterface() {
		//pass
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
        JLabel capitalLabel = new JLabel("Capital: XXX.XX");
        topPanel.add(capitalLabel);
        
        //add buttons
        JButton loadItemPropertiesButton = new JButton("Load Item Properties");
        JButton exportManifestButton = new JButton("Export Manifest");
        JButton loadManfestButton = new JButton("Load Manifest");
        JButton loadSalesLogButton = new JButton("Load Sales Log");
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
