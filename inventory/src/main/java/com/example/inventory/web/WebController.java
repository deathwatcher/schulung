package com.example.inventory.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
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
	public Mono<Boolean> getAvailability(final @PathVariable String sku) {
		return Mono.just(availability(sku));
	}

	@GetMapping("stock/{sku}")
	public Mono<Integer> stockOfProduct(final @PathVariable String sku) {
		return Mono.justOrEmpty(stock.get(sku));
	}

	@GetMapping("remove-stock/{sku}")
	public Mono<Boolean> removeStock(final @PathVariable String sku) {
		if(availability(sku)) {
			stock.put(sku, stock.get(sku)-1);
			return Mono.just(true);
		}
		return Mono.just(false);
	}

	private boolean availability(final String sku) {
		if(stock.containsKey(sku)) {
			return stock.get(sku) > 0;
		}
		return false;
	}
}
