package com.example.exambackend.delivery.controller;

import com.example.exambackend.delivery.model.Delivery;
import com.example.exambackend.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class DeliveryController {

    DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/addDelivery")
    public ResponseEntity<Delivery> addDelivery(Delivery delivery) {
        deliveryService.save(delivery);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/allDeliveries")
    public ResponseEntity<Set<Delivery>> allDeliveries() {
        Set<Delivery> set = deliveryService.findAll();
        return new ResponseEntity<>(set, HttpStatus.OK);
    }
}
