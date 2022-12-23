package com.example.shop.dto;

import org.javamoney.moneta.Money;

public class Product {

	private final String id;
	private final String name;
	private final String description;
	private final String image;
	private final Money price;

	public Product(final String id,final String name, final String description, final String image, final Money price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public Money getPrice() {
		return price;
	}
}
