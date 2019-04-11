package com.retail.shopper.smartshopperorder.constants;

public enum OrderConstants {
	

	POST("POST"), PUT("PUT"), GET("GET"),DELETE("DELETE"), MAIL_HOST_STRING("mail.smtp.host"), HOST("82.150.225.79"),SUCCESS_MAIL_SUBJECT("Mashery Self Service API Creation **Status-SUCCESS**"),ERROR_MAIL_SUBJECT("Mashery Self Service API Creation **Status-ERROR**"),
	PARTIAL_SUCCESS_MAIL_SUBJECT("Mashery Self Service API Creation **Status-SUCCESS WITH SOME ERRORS**"),FROM("smartshopper@domain.com"),TO("saveetha.rudramoorthy@amadeus.com");

	private String value = null;

	private OrderConstants(String value) {
		this.value = value;
	}

	private OrderConstants() {
	}

	public String value() {
		return value;
	}

}
