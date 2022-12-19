package com.example.exambackend.product.controller;

import com.example.exambackend.product.model.Product;
import com.example.exambackend.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/allProducts")
    public ResponseEntity<Set<Product>> allProducts() {
        Set<Product> set = productService.findAll();
        return new ResponseEntity<>(set, HttpStatus.OK);
    }
}
