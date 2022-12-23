package com.example.inventory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

	private final Map<String, Integer> stock;

	public WebController() {
		stock = new HashMap<>();

		stock.put("sku1514", 2);
		stock.put("sku2561", 0);
		stock.put("sku3564", 50);
		stock.put("sku4542", 1000);
		stock.put("sku5625", 1000);
	}

	@GetMapping("availability/{sku}")
	public boolean availability(final @PathVariable String sku) {
		if(stock.containsKey(sku)) {
			return stock.get(sku) > 0;
		}
		return false;
	}

	@GetMapping("stock/{sku}")
	public int stockOfProduct(final @PathVariable String sku) {
		return stock.get(sku);
	}

	@GetMapping("remove-stock/{sku}")
	public boolean removeStock(final @PathVariable String sku) {
		if(availability(sku)) {
			stock.put(sku, stock.get(sku)-1);
			return true;
		}
		return false;
	}
}
