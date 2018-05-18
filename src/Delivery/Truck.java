package Delivery;

import Stock.Stock;

public abstract class Truck {
	private Stock cargo;
	
	public void loadCargo(Stock stock) {
		this.cargo =  stock;
	}
	
	public Stock getCargo(Stock stock) {
		return cargo;
	}
	
	public int getCargoCurrentCapacity() { 
		return cargo.count();
	}
	
	public abstract double getcost();
}
