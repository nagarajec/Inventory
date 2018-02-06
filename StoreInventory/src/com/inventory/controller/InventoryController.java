package com.inventory.controller;

import java.util.Scanner;

import com.inventory.model.Inventory;
import com.inventory.service.InventoryService;
import com.inventory.service.InventoryServiceImpl;

public class InventoryController {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		InventoryService service = new InventoryServiceImpl();
		service.printInfo();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Inventory inventory = new Inventory();
			String input = scanner.nextLine();
			String[] values = input.split(" ");
			if (values[0].equals("create")) {
				service.addProduct(values, inventory);
			}
			if (values[0].equals("updateBuy")) {
				service.updateBuyProduct(values, inventory);
			}
			if (values[0].equals("updateSell")) {
				service.updateSellProduct(values, inventory);
			}
			if (values[0].equals("delete")) {
				service.deleteProduct(values, inventory);
			}
			if (values[0].equals("report")) {
				service.getReport();
			}
			if ("q".equals(input)) {
				System.out.println("Exit!");
				System.exit(0);
			}
		}

	}

}
