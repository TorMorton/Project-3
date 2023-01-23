package com.proj3.warehouses.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj3.warehouses.models.Warehouse;
import com.proj3.warehouses.repositories.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    private WarehouseRepository repository;

    @Override
    public Iterable<Warehouse> findAll() {
        return repository.findAll();
    }

    @Override
    public Warehouse findById(int id) {
        Optional<Warehouse> warehouse = repository.findById(id);
        return warehouse.isPresent() ? warehouse.get() : null;
    }

}
