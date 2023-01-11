package com.example.shop.web;

import com.example.shop.dto.Product;
import com.example.shop.dto.ProductItem;
import com.example.shop.service.ProductServices;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/shop", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebController {
    private final ProductServices productServices;

    public WebController(final ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/products")
    public Flux<Product> getAll() {
        return Flux.fromIterable(productServices.getAllProducts());
    }

    @GetMapping("/product/{sku}")
    public Mono<ProductItem> product(final @PathVariable String sku) {
        return Mono.justOrEmpty(productServices.getProduct(sku));
    }

    @GetMapping("/buy/{sku}")
    public Mono<ProductItem> buy(final @PathVariable String sku) {
        return Mono.justOrEmpty(productServices.buy(sku));
    }
}
