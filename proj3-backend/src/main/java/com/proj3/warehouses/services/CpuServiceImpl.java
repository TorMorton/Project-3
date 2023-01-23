package com.proj3.warehouses.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj3.warehouses.models.Cpu;
import com.proj3.warehouses.repositories.CpuRepository;

@Service
public class CpuServiceImpl implements CpuService{

    @Autowired
    private CpuRepository repository;

    @Override
    public Iterable<Cpu> findAll() {
        return repository.findAll();
    }

    @Override
    public Cpu findById(int id) {
        Optional<Cpu> cpu = repository.findById(id);
        return cpu.isPresent() ? cpu.get() : null;
    }

    @Override
    public Cpu save(Cpu cpu) {
        if (!repository.existsById(cpu.getId())) {
            System.out.println("Inside Save " + cpu);
            Cpu createdCpu = repository.save(cpu);
            System.out.println("Created Cpu: " + createdCpu);
            return createdCpu;
        }
        return cpu;
    }

    @Override
    public Cpu update(Cpu cpu) {
        if (repository.existsById(cpu.getId())) {
            return repository.save(cpu);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

    @Override
    public void delete(Cpu cpu) {
        repository.delete(cpu);

    }

}