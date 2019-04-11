package com.retail.shopper.SmartShopperLogin.constants;

public enum UserConstants {
	

	POST("POST"), PUT("PUT"), GET("GET"),DELETE("DELETE"), MAIL_HOST_STRING("mail.smtp.host"), HOST("82.150.225.79"),FROM("smartshopper@domain.com"),TO("saveetha.rudramoorthy@amadeus.com");

	private String value = null;

	private UserConstants(String value) {
		this.value = value;
	}

	private UserConstants() {
	}

	public String value() {
		return value;
	}

}
