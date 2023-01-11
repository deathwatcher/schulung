package com.example.shop.service;

import com.example.shop.dto.Product;
import com.example.shop.dto.ProductItem;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Daniel Rutenkolk (pd07753)
 */
@Service
public class ProductServices {
    private static final String URL = "http://localhost";
    private static final String PORT = "8081";
    private final Map<String, Product> products;
    private final RestTemplate restTemplate;

    public ProductServices(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        products = new HashMap<>();
        products.put("sku1514", new Product("sku1514","Uhr", "Eine Uhr für die Wand", "", Money.of(65.50, "EUR")));
        products.put("sku2561", new Product("sku2561","Laptop", "Mobiler Computer", "", Money.of(349.99, "EUR")));
        products.put("sku3564", new Product("sku3564","Malbuch", "Ein Buch zum ausmalen", "", Money.of(4.95, "EUR")));
        products.put("sku4542", new Product("sku4542","Gabel", "Eine Gabel zum essen", "", Money.of(1.95, "EUR")));
        products.put("sku5625", new Product("sku5625","Pritt Stift", "Zum Kleben von Papier", "", Money.of(1.35, "EUR")));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<ProductItem> getProduct(final String sku) {
        Boolean available = restTemplate.getForObject(URL+":"+PORT+"/inventory/availability/"+sku, Boolean.class);
        Integer stock = restTemplate.getForObject(URL+":"+PORT+"/inventory/stock/"+sku, Integer.class);
        if(available == null || stock == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(products.get(sku)).map(p -> new ProductItem(p, available, stock));
    }

    public Optional<ProductItem> buy(final String sku) {
        return getProduct(sku).map(p -> {
            Boolean successful = restTemplate.getForObject(URL+":"+PORT+"/inventory/remove-stock/"+sku, Boolean.class);
            if(successful) {
                p.setStock(p.getStock()-1);
            }
            return p;
        });
    }
}
