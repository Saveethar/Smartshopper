package com.retail.shopper.SmartShopperProduct.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.retail.shopper.SmartShopperProduct.exception.ResourceNotFoundException;
import com.retail.shopper.SmartShopperProduct.jpa.ProductRepository;
import com.retail.shopper.SmartShopperProduct.model.Product;
import com.retail.shopper.SmartShopperProduct.model.ProductAPIResponse;
import com.retail.shopper.SmartShopperProduct.util.Utils;
import com.retail.shopper.SmartShopperProduct.service.ProductService;
import com.retail.shopper.SmartShopperProduct.service.ProductServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Utils utils;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	ProductAPIResponse productAPIResponse;
	

	/**
	 * Get all product list from inventory.
	 *
	 * @return the list
	 */
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		// If there is a supplier, then we will invoke ProductService.getAllProducts
		return productRepository.findAll();
	}
	
	/**
	 * Gets Product by id. Will be used by both internal and external users
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductsById(
			@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ResourceNotFoundException("Product not found with id :: "
						+ productId));
		return ResponseEntity.ok().body(product);
	}
	
	// Add products - Admin usages
	@PostMapping("/products")
	public String createProduct(@Valid @RequestBody Product product) {
		try {
			product.setCreatedAt(new Date());
			product.setUpdatedAt(new Date());
			product.setFullPrice(product.getQuantity()*product.getUnitPrice());
			productRepository.save(product);
			utils.sendEmail("Smartshopper Product catalogue","Product "+product.getName()+" added successfully to product catalogue");
		} catch (Exception e) {
			utils.getPrintableStackTrace(e);
			logger.info("Adding into product catalogue Failing: " + utils.getPrintableStackTrace(e));
			return "Unable to add product";
		}
		
		return "Product added to catalogue Successfully";
	}
	
	// Can add REST methods to modify and delete product from product catalogue for admin purposes
}
