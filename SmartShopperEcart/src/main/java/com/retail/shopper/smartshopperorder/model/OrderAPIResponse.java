package com.retail.shopper.smartshopperorder.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.retail.shopper.smartshopperorder.service.model.Product;

@Component
public class OrderAPIResponse {

	private String errorString;
	private String successString;
	private String status;
	private Collection<Product> productList = new ArrayList<Product>();

	/**
	 * @return the productList
	 */
	public Collection<Product> getProductList() {
		return productList;
	}

	/**
	 * @param productList the productList to set
	 */
	public void setProductList(Collection<Product> productList) {
		this.productList = productList;
	}

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuccessString() {
		return successString;
	}

	public void setSuccessString(String successString) {
		this.successString = successString;
	}


	@Override
	public String toString() {
		return "ProductAPIResponse [errorString=" + errorString + ", successString=" + successString + ", status="
				+ status +"]";
	}

	
	
}
