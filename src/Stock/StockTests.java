package Stock;

import static org.junit.Assert.*;

import java.io.Console;

import org.junit.Test;

public class StockTests {

	Stock stock;
	Item testItem1 = new Item("Danni", 12.3, 9, 56, 89);
	Item testItem2 = new Item("Cheryl", 12.3, 9, 56, 89, 77);
	Item testItem3 = new Item("Andreas", 12.3, 9, 56, 89, 4);
	
	@Before
	public void ConstructorTest() {
		stock = new Stock();
	}
	
	@Test
	public void addTest() {
		stock.add(testItem1);
	}
	
	@Test
	public void getTest() {
		stock.add(testItem1);
		assertEquals(testItem1, stock[0]);
	}
	
	@Test
	public void iterTest() {
		int c = 0;
		stock.add(testItem1);
		stock.add(testItem2);
		stock.add(testItem3);
		
		for (Item item: stock) {
			c++;
		}
		assertEquals(2, c);
	}
	
	// duplicates
	
	// adding items
	
	// removing items
	
	// length
	
	// iteration
	
	// Exception Raising
	
	// Getting an item which doesn't exist
}
