package com.example.exambackend.productorder.controller;

import com.example.exambackend.delivery.model.Delivery;
import com.example.exambackend.delivery.service.DeliveryService;
import com.example.exambackend.product.model.Product;
import com.example.exambackend.product.service.ProductService;
import com.example.exambackend.productorder.model.ProductOrder;
import com.example.exambackend.productorder.service.ProductOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductOrderController {

    ProductOrderService productOrderService;
    ProductService productService;
    DeliveryService deliveryService;

    public ProductOrderController(ProductOrderService productOrderService, ProductService productService, DeliveryService deliveryService) {
        this.productOrderService = productOrderService;
        this.productService = productService;
        this.deliveryService = deliveryService;
    }

    @PostMapping("/addProductOrder")
    public ResponseEntity<ProductOrder> addProductOrder(@RequestParam Long productID, @RequestParam Long deliveryID, ProductOrder productOrder) {
        // Check om product og delivery findes.
        // Hvis de findes, så sæt product og delivery i productOrder obj.
        System.out.println("Recieved productID: " + productID + "\nRecieved deliveryID: " + deliveryID);
        Optional<Product> product_ = productService.findById(productID);
        Optional<Delivery> delivery_ = deliveryService.findById(deliveryID);
        if (product_.isPresent() && delivery_.isPresent()) {

            Product product = product_.get();
            productOrder.setProduct(product);

            Delivery delivery = delivery_.get();
            productOrder.setDelivery(delivery);

            productOrderService.save(productOrder);
            return new ResponseEntity<>(productOrder, HttpStatus.OK);
        } else {
            System.out.println("Could not find Product and/or Delivery ID :(");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
