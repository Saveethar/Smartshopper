package com.retail.shopper.smartshopperorder.service.model;

import javax.persistence.*;

@Embeddable
public class Product {

	private Long productId;

	private String name;

	public String description;

	private Double unitPrice;

	private int quantity;

	private Double fullPrice=0.0;

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the fullPrice
	 */
	public Double getFullPrice() {
		return fullPrice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name
				+ ", description=" + description + ", unitPrice=" + unitPrice
				+ ", quantity=" + quantity + ", fullPrice=" + fullPrice + "]";
	}
	

}
