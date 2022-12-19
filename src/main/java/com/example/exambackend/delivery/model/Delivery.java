package com.example.exambackend.delivery.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "DELIVERIES")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // TODO: Lav deliveryDate til en 'Date' datatype
    @Column(name = "DELIVERYDATE")
    private String deliveryDate;

    @Column(name = "FROMWAREHOUSE")
    private String fromWarehouse;

    @Column(name = "DESTINATION")
    private String destination;
}
