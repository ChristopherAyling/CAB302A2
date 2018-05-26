package Stock;

import static org.junit.Assert.*;

import java.util.OptionalInt;

import org.junit.Test;

public class ItemTests {

	private String name = "Oscar";
	private Double manufacturingCost = 55.5;
	private Double sellPrice = 34.2;
	private Integer reorderPoint = 10;
	private Integer reorderAmount = 20;
	private int temp = 8;
	
	@Test
	public void noTempConstructorTest() {
		new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
	}
	
	@Test
	public void withTempConstructorTest() {
		new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temp);
	}
	
	@Test
	public void getNameTest() {
		Item item = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
		assertEquals(name, item.getName());
	}
	
	@Test
	public void getManufacturingCostTest() {
		Item item = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
		assertEquals(manufacturingCost, item.getManufacturingCost());
	}
	
	@Test
	public void getSellPriceTest() {
		Item item = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
		assertEquals(sellPrice, item.getSellPrice());
	}
	
	@Test
	public void getReorderPointTest() {
		Item item = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
		assertEquals(reorderPoint, item.getReorderPoint());
	}
	
	@Test
	public void getReorderAmountTest() {
		Item item = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
		assertEquals(reorderAmount, item.getReorderAmount());
	}
	
	@Test
	public void getTempTest() {
		Item item = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temp);
		assertTrue(temp == item.getTemperature());
	}
	
	@Test
	public void getTempNoTempTest() {
		Item item = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount);
		assertEquals(null, item.getTemperature());
	}
	
	@Test 
	public void equalsTest() {
		Item item1 = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temp);
		Item item2 = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temp);
		// https://www.sitepoint.com/implement-javas-equals-method-correctly/
		assertTrue(item1.equals(item1));
		assertTrue(item1.equals(item2));
		assertTrue(item2.equals(item1));
	}
}
