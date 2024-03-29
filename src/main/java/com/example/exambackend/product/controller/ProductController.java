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
    public ResponseEntity<Product> findProduct(@RequestParam Long productID) {
        Optional<Product> product_ = productService.findById(productID);
        if (product_.isPresent()) {
            Product product = product_.get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            System.out.println("Could not find product ID :(");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Der er nok en simplere måde at gøre det her på!
    @GetMapping("/findProductByName")
        public ResponseEntity<Product> findProductByName(@RequestParam String productName) {
        Optional<Product> product_ = productService.findByName(productName);
        if (product_.isPresent()) {
            Product product = product_.get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            System.out.println("Could not find product name :(");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: Lav om til en @PatchMapping
    @PutMapping("/editProduct")
    public ResponseEntity<Product> editProduct(@RequestBody Product updatedProduct, @RequestParam Long productID) {
      Optional <Product> oldProduct_ = productService.findById(productID);
      if (oldProduct_.isPresent()) {
          updatedProduct.setId(productID);
          productService.save(updatedProduct);
          return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
      } else {
          System.out.println("Could not find product ID :(");
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }
    }
}
