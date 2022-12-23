package com.example.shop.dto;

/**
 * @author Daniel Rutenkolk (pd07753)
 */
public class ProductItem extends Product {

    private final boolean availability;
    private final int stock;

    public ProductItem(final Product product, final boolean availability, final int stock) {
        super(product.getId(), product.getName(), product.getDescription(), product.getImage(), product.getPrice());
        this.availability = availability;
        this.stock = stock;
    }

    public boolean isAvailability() {
        return availability;
    }

    public int getStock() {
        return stock;
    }
}
