package com.example.exambackend.product.controller;

import com.example.exambackend.product.model.Product;
import com.example.exambackend.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/products")
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

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Product> deleteProduct(Product product) {
        productService.deleteById(product.getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Set<Product>> allProducts() {
        Set<Product> set = productService.findAll();
        return new ResponseEntity<>(set, HttpStatus.OK);
    }

    @GetMapping("/findProduct")
    public ResponseEntity<Product> findProduct(@RequestParam Long productID, Product product) {
        Optional<Product> product_ = productService.findById(productID);
        if (product_.isPresent()) {
            product = product_.get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            System.out.println("Could not find product ID :(");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
