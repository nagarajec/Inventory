package com.inventory.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.inventory.controller.InventoryController;
import com.inventory.model.Inventory;

public class InventoryServiceImpl implements InventoryService {

	static HashMap<String, Inventory> storeInventory = new HashMap<String, Inventory>();
	BigDecimal totalSoldVal = new BigDecimal(0);
	BigDecimal profit = new BigDecimal(0);
	BigDecimal totalBroughtVal = new BigDecimal(0);

	@Override
	public void getReport() {
		System.out.println("                                  INVENTORY REPORT                               ");
		System.out.println(" ITEM NAME          BROUGHT AT           SOLD AT             AVAILABLE QTY      VALUE");
		System.out.println(" ---------          ----------           -------             -------------      ------");
		Collection<Inventory> productDetails = storeInventory.values();
		Iterator<Inventory> iterator = productDetails.iterator();
		while (iterator.hasNext()) {
			Inventory inventoryTemp = iterator.next();

			totalBroughtVal = totalBroughtVal
					.add(inventoryTemp.getCostPrice().multiply(new BigDecimal(inventoryTemp.getQuantity())));
			totalSoldVal = totalSoldVal
					.add(inventoryTemp.getSellPrice().multiply(new BigDecimal(inventoryTemp.getQuantity())));
			System.out.println(inventoryTemp.toString());

		}

		System.out
				.println(" ------------------------------------------------------------------------------------------");
		System.out.println(" Total Value                                                                           "
				+ totalBroughtVal);
		System.out.println(" Profit Since Previous Report                                                          "
				+ totalSoldVal.subtract(totalBroughtVal));
	}

	@Override
	public void addProduct(String[] values, Inventory inventory) {
		if (values.length != 4) {
			System.out.println(
					"Input Error: Please enter the product information correctly (Ex: create book01 9.22 10.05");
		} else if (!(isNumber(values[2]) && isNumber(values[3]))) {
			System.out.println(
					"Cost price and sell price not entered correctly. Please enter the product information correctly (Ex: create book01 9.22 10.05");
		} else {
			inventory.setProductName(values[1]);
			inventory.setCostPrice(BigDecimal.valueOf(Double.parseDouble(values[2])).setScale(2, RoundingMode.CEILING));
			inventory.setQuantity(1);
			inventory.setSellPrice(BigDecimal.valueOf(Double.parseDouble(values[3])).setScale(2, RoundingMode.CEILING));
			storeInventory.put(values[1], inventory);
		}
	}

	private static boolean isNumber(String string) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(string);
			Double.parseDouble(string);
			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
	}

	@Override
	public void updateBuyProduct(String[] values, Inventory inventory) {
		if (values.length != 3) {
			System.out.println("Input Error: Please enter the product information correctly (Ex: updateBuy book01 100");
		} else if (!(isNumber(values[2]))) {
			System.out.println(
					"Quantity not entered correctly. Please enter the product information correctly (Ex: updateBuy book01 100");
		} else if (storeInventory.containsKey(values[1])) {
			inventory = (Inventory) storeInventory.get(values[1]);
			long existingQuantity = inventory.getQuantity();
			inventory.setQuantity(Long.parseLong(values[2]) + existingQuantity);
			storeInventory.put(values[1], inventory);
		}
	}

	@Override
	public void updateSellProduct(String[] values, Inventory inventory) {
		if (values.length != 3) {
			System.out
					.println("Input Error: Please enter the product information correctly (Ex: updateSell book01 100");
		} else if (!(isNumber(values[2]))) {
			System.out.println(
					"Quantity not entered correctly. Please enter the product information correctly (Ex: updateSell book01 100");
		} else if (storeInventory.containsKey(values[1])) {
			inventory = (Inventory) storeInventory.get(values[1]);
			long existingQuantity = inventory.getQuantity();
			inventory.setQuantity(existingQuantity - Long.parseLong(values[2]));
			storeInventory.put(values[1], inventory);
		}
	}

	@Override
	public void deleteProduct(String[] values, Inventory inventory) {
		if (values.length != 2) {
			System.out.println("Input Error: Please enter the command information correctly (Ex: delete book01");
		} else if (storeInventory.containsKey(values[1])) {
			storeInventory.remove(values[1]);
		}
	}

	@Override
	public void printInfo() {
		System.out.print("Enter Inventory Information in the following order:");
		System.out.println("If you want to create a product, please enter as: create prodcutname costprice sellprice");
		System.out
				.println("If you want to update product buy quantity, please enter as: updateBuy productname quantiry");
		System.out.println(
				"If you want to update product sell quantity, please enter as: updateSell productname quantiry");
		System.out.println("If you want to delete product, please enter as: delete productname");
		System.out.println("If you want current report of the inventory, please enter as: report.");
	}

}
