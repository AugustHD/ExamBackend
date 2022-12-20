package com.example.exambackend.product.model;

import com.example.exambackend.productorder.model.ProductOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
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
