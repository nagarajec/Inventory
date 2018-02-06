package com.inventory.service;

import com.inventory.model.Inventory;

public interface InventoryService {
	
	public void getReport();
	public void addProduct(String[] values, Inventory inventory);
	public void updateBuyProduct(String[] values, Inventory inventory);
	public void updateSellProduct(String[] values, Inventory inventory);
	public void deleteProduct(String[] values, Inventory inventory);
	public void printInfo();

}
 