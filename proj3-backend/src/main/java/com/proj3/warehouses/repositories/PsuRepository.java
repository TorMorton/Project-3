package com.proj3.warehouses.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proj3.warehouses.models.Psu;

@Repository
public interface PsuRepository extends CrudRepository<Psu, Integer>{

}