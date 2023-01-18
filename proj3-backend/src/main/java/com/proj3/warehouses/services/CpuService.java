package com.proj3.warehouses.services;

import org.springframework.stereotype.Service;

import com.proj3.warehouses.models.Cpu;

public interface CpuService {
	
	Iterable<Cpu> findAll();
	Cpu findById(int id);
	Cpu save(Cpu cpu);
	Cpu update(Cpu cpu);
	void delete(Cpu cpu);
	void deleteById(int id);

}
