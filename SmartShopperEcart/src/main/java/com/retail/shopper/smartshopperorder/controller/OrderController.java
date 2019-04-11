package com.retail.shopper.smartshopperorder.controller;

import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.retail.shopper.smartshopperorder.exception.ResourceNotFoundException;
import com.retail.shopper.smartshopperorder.jpa.OrderRepository;
import com.retail.shopper.smartshopperorder.model.Order;
import com.retail.shopper.smartshopperorder.model.OrderAPIResponse;
import com.retail.shopper.smartshopperorder.service.OrderService;
import com.retail.shopper.smartshopperorder.util.Utils;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private Utils utils;

	@Autowired
	private OrderService orderService;

	@Autowired
	OrderAPIResponse orderAPIResponse;

	/**
	 * Get all product list from inventory.
	 *
	 * @return the list
	 */
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	/**
	 * Gets Product by id. Will be used by both internal and external users
	 */
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(
			@PathVariable(value = "id") Long orderId)
			throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(
				() -> new ResourceNotFoundException(
						"Order not found with id :: " + orderId));
		return ResponseEntity.ok().body(order);
	}

	@ApiOperation(value = "createOrder", response = OrderAPIResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = OrderAPIResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Request URI not found") })
	@PostMapping(value = "/order/add", produces = "application/json")
	public OrderAPIResponse createOrder(@Valid @RequestBody Order order) {
		order.setCreatedAt(new Date());
		order.setUpdatedAt(new Date());
		
		orderAPIResponse = orderService.checkoutOrder(order.getCustomerId(),order.getProductId());
		
		if (orderAPIResponse.getStatus().equals("Success")) {
			order.setFulfilmentStatus(true);
			order.setFulfilmentDate(new Date());
			orderRepository.save(order);
		} else {
			order.setFulfilmentStatus(false);	
			orderRepository.save(order);
		}
			
		return orderAPIResponse;		
	}

	// Can add REST methods to modify and delete product from product catalogue
	// for admin purposes
}
