package com.proj3.warehouses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository < CpuWarehouse, Long > {
	UserRepository findByID(String id);
}
