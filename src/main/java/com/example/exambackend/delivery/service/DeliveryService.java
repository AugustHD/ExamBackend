package com.example.exambackend.delivery.service;

import com.example.exambackend.delivery.model.Delivery;
import com.example.exambackend.repo.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DeliveryService implements IDeliveryService {

    DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Set<Delivery> findAll() {
        Set<Delivery> set = new HashSet<>();
        deliveryRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Delivery save(Delivery object) {
        deliveryRepository.save(object);
        return object;
    }

    @Override
    public void delete(Delivery object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Delivery> findById(Long aLong) {
        return Optional.empty();
    }
}
