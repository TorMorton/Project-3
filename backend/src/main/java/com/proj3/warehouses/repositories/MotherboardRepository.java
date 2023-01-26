package com.proj3.warehouses.repositories;


import com.proj3.warehouses.models.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotherboardRepository extends JpaRepository<Motherboard, Integer> {

//    List<Motherboard> getAllMotherboards();
}
