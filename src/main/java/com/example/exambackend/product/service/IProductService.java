package com.example.exambackend.product.service;

import com.example.exambackend.product.model.Product;
import com.example.exambackend.repo.ICrudRepository;

public interface IProductService extends ICrudRepository<Product, Long> {
}
