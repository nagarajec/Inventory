package com.inventory.model;

import java.math.BigDecimal;

public class Inventory {

	private String productName;
	private long quantity;
	private BigDecimal costPrice;
	private BigDecimal sellPrice;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Override
	public String toString() {
		return " " + this.productName + "                 " + this.costPrice + "                  " + this.sellPrice
				+ "                    " + this.quantity + "            "
				+ this.costPrice.multiply(new BigDecimal(this.quantity)) + "";
	}

}
