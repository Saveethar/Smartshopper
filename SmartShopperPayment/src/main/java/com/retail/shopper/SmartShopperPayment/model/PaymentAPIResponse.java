package com.retail.shopper.SmartShopperPayment.model;

import org.springframework.stereotype.Component;

@Component
public class PaymentAPIResponse {

	private String errorString;
	private String successString;
	private String status;

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
