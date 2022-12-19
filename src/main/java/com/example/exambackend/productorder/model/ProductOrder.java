package com.example.exambackend.productorder.model;

import com.example.exambackend.delivery.model.Delivery;
import com.example.exambackend.product.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "PRODUCTORDERS")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "QUANTITY")
    private int quantity;

    @OneToOne(cascade = CascadeType.ALL) // En product-order kan kun have ét product.
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JsonBackReference
    Delivery delivery;

    public ProductOrder(Product product, Delivery delivery) {
        this.product = product;
        this.delivery = delivery;
    }
}
