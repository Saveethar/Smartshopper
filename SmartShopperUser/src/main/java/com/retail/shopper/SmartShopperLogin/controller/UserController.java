package com.retail.shopper.SmartShopperLogin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.retail.shopper.SmartShopperLogin.exception.ResourceNotFoundException;
import com.retail.shopper.SmartShopperLogin.jpa.UserRepository;
import com.retail.shopper.SmartShopperLogin.model.User;
import com.retail.shopper.SmartShopperLogin.util.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Utils utils;
	
	/**
	 * Intention of method is to mimic login success post registration with
	 * combination of username and email. Need integration with authentication
	 * and authorization server so that token is generate for API access
	 * for both human and robotic users
	 **/
	@RequestMapping(value = "/users", params = { "userName", "email" }, method = RequestMethod.GET)
	public String getUsersById(@Param("userName") String userName,
			@Param("email") String email) throws ResourceNotFoundException {
		// To authenticate and obtain OAuth token from security provider
		//utils.authenticate(apikey, secret, username, password, areaUuid)
		// Very simple check using DB that user is present
		userRepository.findByUserNameAndEmail(userName, email);
		return "Üser Successfully Logged In";
	}

}
