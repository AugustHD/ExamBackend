package com.example.exambackend.repo;

import com.example.exambackend.productorder.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}
