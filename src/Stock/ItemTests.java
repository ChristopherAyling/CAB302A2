package Stock;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTests {

	private String name = "Oscar";
	private double manufacturingCost = 55.5;
	private double sellPrice = 34.2;
	private int reorderPoint = 10;
	private int reorderAmount = 20;
	private int temp = 8;
	
	
	@Test
	public void ConstructorTests() {
		Item itemWithoutTemp = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
		
		Item itemWithTemp = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temp);
	}
}
