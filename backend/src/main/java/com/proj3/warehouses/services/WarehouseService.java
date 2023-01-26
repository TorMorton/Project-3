package com.proj3.warehouses.services;

import com.proj3.warehouses.models.Warehouse;

public interface WarehouseService {

    Iterable<Warehouse> findAll();
    Warehouse findById(int id);


}