package com.retail.shopper.smartshopperorder.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.shopper.smartshopperorder.jpa.OrderRepository;
import com.retail.shopper.smartshopperorder.model.OrderAPIResponse;
import com.retail.shopper.smartshopperorder.service.model.Product;
import com.retail.shopper.smartshopperorder.service.model.User;
import com.retail.shopper.smartshopperorder.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class OrderServiceImpl implements OrderService {

	private final Logger logger = LoggerFactory.getLogger(MethodHandles
			.lookup().lookupClass());

	@Autowired
	OrderAPIResponse orderAPIResponse;

	@Autowired
	private OrderRepository productRepository;

	@Autowired
	private Utils utils;
	
	private Double totalOrderValue = 0.0;

	/**
	 * It is shown that products are fetched from DB. Also commented block is
	 * shown where token is obtained and supplier APIs are invoked to populate
	 * product list
	 */
	@Override
	public OrderAPIResponse checkoutOrder(Long customerId, Long productId) {
		try {
			// Not doing this as APIs are not hosted on API gateway
			// String token = utils.authenticate("apikey", "secret", "username",
			// "password", "areaUuid");

			// Simple way if User object returned matches
			// RestTemplate restTemplate = new RestTemplate();
			// User user =
			// restTemplate.getForObject("http://localhost:8080/users/register/"+CustomerId,
			// User.class);

			String userStr = utils.callApi(null, "GET",
					"http://localhost:9000/api/v1/users/register/"+customerId, null,
					null);
			Gson gson = new Gson();
			User userObj = new User();
			userObj = gson.fromJson(userStr, userObj.getClass());
			
			this.logger.info("User retrieved= " + userObj.toString());
			
			String productArrStr = utils.callApi(null, "GET",
					"http://localhost:9001/api/v1/products/", null,
					null);
			List<Product> prodList = gson.fromJson(productArrStr, new TypeToken<List<Product>>() {
			}.getType());
			
			prodList.forEach(x ->			
			{
				String productName = x.getName();
				this.totalOrderValue = totalOrderValue+ x.getFullPrice();
				this.logger.info("Product checked out= "+productName);
				}
			);	

			orderAPIResponse.setErrorString(null);
			orderAPIResponse.setStatus("Success");
			orderAPIResponse.setProductList(prodList);
			orderAPIResponse.setSuccessString("All products verified. Total Bill amount="+this.totalOrderValue);
			
			this.totalOrderValue = 0.0;

		} catch (Exception e) {
			e.printStackTrace();
			orderAPIResponse.setStatus("Error");
			orderAPIResponse
					.setErrorString("Error occured while getting the services");
			orderAPIResponse.setSuccessString(null);
			logger.info("Error occured while getting the orders");
		}
		return orderAPIResponse;
	}

}
