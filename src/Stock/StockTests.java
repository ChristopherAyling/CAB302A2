package Stock;


import static org.junit.Assert.*;

import java.io.Console;

import org.junit.Before;
import org.junit.Test;

public class StockTests {

	Stock stock;
	
	Item noTempItem1 = new Item("Canned Tomatoes", 12.3, 9, 56, 89);
	Item noTempItem2 = new Item("Table salt", 12.3, 9, 56, 89);
	
	Item tempItem1 = new Item("Cold Magical Elixir", 12.3, 9, 56, 89, 2.0);
	Item tempItem2 = new Item("Cool Cucumber", 777.777, 892.1, 4, 5, -18.1);
	Item tempItem3 = new Item("Too Cold Tomato", 18.2, 19.1, 66, 99, -498.3);

	
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
		stock.add(tempItem1);
		stock.add(tempItem2);
		stock.add(tempItem3);
		
		for (Item item: stock.getItems()) {
			c++;
		}
		assertEquals(2, c);
	}
	
	@Test
	public void removeTest() {
		stock.add(tempItem1);
		stock.remove(tempItem1.getName());
	}
	
	@Test
	public void getColdestItemNoItemsTest() {
		assertEquals(stock.getColdestItemTemperature(), 10.0);
	}
	
	@Test
	public void getColdestItemDryGoodsTest() {
		stock.add(noTempItem1);
		assertEquals(stock.getColdestItemTemperature(), noTempItem1.getTemperature());
	}
	
	@Test
	public void getColdestItemTest() {
		stock.add(tempItem1);
		stock.add(tempItem2);
		assertEquals(stock.getColdestItemTemperature(), tempItem2.getTemperature());
	}
	
	@Test
	public void getColdestItemTooColdTest() {
		stock.add(tempItem3);
		assertEquals(stock.getColdestItemTemperature(), tempItem3.getTemperature());
	}
	
	@Test
	public void getQuantityTest() {
		assertEquals(0, stock.getQuantity(tempItem1.getName()));
		stock.add(tempItem1);
		assertEquals(1, stock.getQuantity(tempItem1.getName()));
		stock.add(tempItem1);
		assertEquals(2, stock.getQuantity(tempItem1.getName()));
		stock.add(noTempItem1);
		assertEquals(1, stock.getQuantity(noTempItem1.getName()));
		assertEquals(0, stock.getQuantity("some random other item"));
	}
}
