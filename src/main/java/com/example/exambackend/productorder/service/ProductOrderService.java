package com.example.exambackend.productorder.service;

import com.example.exambackend.productorder.model.ProductOrder;
import com.example.exambackend.repo.ProductOrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductOrderService implements IProductOrderService {

    ProductOrderRepository productOrderRepository;

    public ProductOrderService(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public Set<ProductOrder> findAll() {
        Set<ProductOrder> set = new HashSet<>();
        productOrderRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public ProductOrder save(ProductOrder object) {
        productOrderRepository.save(object);
        return object;
    }

    @Override
    public void delete(ProductOrder object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<ProductOrder> findById(Long aLong) {
        return Optional.empty();
    }
}
