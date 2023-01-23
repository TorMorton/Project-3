package com.proj3.warehouses.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proj3.warehouses.models.Cpu;

@Repository
public interface CpuRepository extends CrudRepository<Cpu, Integer>{

}
