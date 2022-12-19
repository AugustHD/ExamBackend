package com.example.exambackend.product.service;

import com.example.exambackend.product.model.Product;
import com.example.exambackend.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService implements IProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Set<Product> findAll() {
        Set<Product> set = new HashSet<>();
        productRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Product save(Product object) {
        productRepository.save(object);
        return object;
    }

    @Override
    public void delete(Product object) {

    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }
}
