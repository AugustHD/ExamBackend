package com.example.exambackend.product.model;

import com.example.exambackend.productorder.model.ProductOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table (name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "WEIGHT")
    private int weight;

    @OneToOne(mappedBy = "product")
    @JsonBackReference
    private ProductOrder productOrder;
}
