package Stock;

import static org.junit.Assert.*;

import java.io.Console;

import org.junit.Test;

public class StockTests {

	Stock stock;
	
	Item noTempItem1 = new Item("Canned Tomatoes", 12.3, 9, 56, 89);
	Item noTempItem2 = new Item("Table salt", 12.3, 9, 56, 89);
	
	Item tempItem1 = new Item("Cold Magical Elixir", 12.3, 9, 56, 89, 2);
	Item tempItem2 = new Item("Cool Cucumber", 777.777, 892.1, 4, 5, -18);

	
	@Before
	public void ConstructorTest() {
		stock = new Stock();
	}
	
	@Test
	public void addNoTempTest() {
		stock.add(noTempItem1);
		stock.add(noTempItem2);
	}
	
	@Test
	public void addWithTempTest() {
		stock.add(tempItem1);
		stock.add(tempItem2);
	}
	
	@Test
	public void countItemTest() {
		assertEquals(0, stock.count(tempItem1));
		stock.add(tempItem1);
		assertEquals(1, stock.count(tempItem1));
		stock.add(tempItem1);
		assertEquals(2, stock.count(tempItem1));
		stock.add(tempItem1);
		stock.add(tempItem2);
		assertEquals(2, stock.count(tempItem1));
	}
	
	@Test
	public void removeOneTest() {
		stock.add(tempItem1);
		stock.remove(tempItem1);
		assertEquals(0, stock.count(tempItem1));
	}
	
	@Test
	public void removeNTest() {
		stock.add(tempItem1);
		stock.remove(tempItem1, 1);
		assertEquals(0, stock.count(tempItem1));
	}
	
	@Test(expected = StockException.class)
	public void removeItemExceptionTest() {
		stock.remove(tempItem1);
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
	
	@Test
	public void removeTest() {
		stock.add(testItem1);
		stock.remove(testItem1.getName());
	}
}
