package com.retail.shopper.SmartShopperProduct.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.retail.shopper.SmartShopperProduct.jpa.ProductRepository;
import com.retail.shopper.SmartShopperProduct.model.Product;
import com.retail.shopper.SmartShopperProduct.model.ProductAPIResponse;
import com.retail.shopper.SmartShopperProduct.util.Utils;


@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	ProductAPIResponse productAPIResponse;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Utils utils;


	/** It is shown that products are fetched from DB. 
	 * Also commented block is shown where token is obtained and supplier APIs are invoked
	 * to populate product list
	 */
	@Override
	public ProductAPIResponse getAllProducts() {
		try {
			System.setProperty("https.protocols", "TLSv1.2");
			
			// Obtain token
			String token = utils.authenticate("apikey", "secret", "username", "password", "areaUuid");

			String getProducts = utils.callApi(token, "GET", "/products","URL Params","URL");
							
			productRepository.findAll();			
			productAPIResponse.setErrorString(null);
			productAPIResponse.setStatus("Success");
			productAPIResponse.setSuccessString("All the products are displayed");			

		} catch (Exception e) {
			productAPIResponse.setStatus("Error");
			productAPIResponse.setErrorString("Error occured while getting the services");
			productAPIResponse.setSuccessString(null);
			logger.info("Error occured while getting the services");
		}
		return productAPIResponse;
	}

}
