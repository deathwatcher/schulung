package com.example.shop.web;

import com.example.shop.dto.Product;
import com.example.shop.dto.ProductItem;
import com.example.shop.service.ProductServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Controller
public class WebController {
	private final ProductServices productServices;

	public WebController(final ProductServices productServices) {
		this.productServices = productServices;
	}

	@GetMapping("/products")
	public List<Product> getAll() {
		return productServices.getAllProducts();
	}

	@GetMapping("/product/{sku}")
	public ProductItem product(final @PathVariable String sku) {
		Optional<ProductItem> opProduct = productServices.getProduct(sku);
		return opProduct.orElse(null);
	}

}
