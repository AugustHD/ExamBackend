package com.example.exambackend.repo;

import com.example.exambackend.delivery.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
