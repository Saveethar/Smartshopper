package com.retail.shopper.SmartShopperProduct.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.retail.shopper.SmartShopperProduct.model.Product;
import com.retail.shopper.SmartShopperProduct.model.ProductAPIResponse;

@Component
public interface ProductService {

	public ProductAPIResponse getAllProducts();

}