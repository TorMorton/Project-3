package com.proj3.warehouses.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proj3.warehouses.models.Warehouse;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Integer>{

}
