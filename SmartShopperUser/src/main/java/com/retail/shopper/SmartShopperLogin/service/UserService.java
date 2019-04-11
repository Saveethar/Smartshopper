package com.retail.shopper.SmartShopperLogin.service;

import org.springframework.stereotype.Component;

@Component
public interface UserService {

	public void sendEmail(String subject,String message);

}
