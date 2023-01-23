package com.proj3.warehouses.services;

import java.lang.Iterable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proj3.warehouses.models.Gpu;
import com.proj3.warehouses.repositories.GpuRepository;

@Service // this tells spring boot this class is a "component" that it needs to create an instance of at startup
public class GpuServiceImpl implements GpuService {

    @Autowired
    private GpuRepository repository;

    @Override
    public Iterable<Gpu> findAll() {
        return repository.findAll();
    }

    @Override
    public Gpu findById(int id) {
        Optional<Gpu> gpu = repository.findById(id);
        return gpu.isPresent() ? gpu.get() : null;
    }

    @Override
    public Gpu save(Gpu gpu) {
        if (!repository.existsById(gpu.getId())) {
            System.out.println("Inside Save " + gpu);
            Gpu createdGpu = repository.save(gpu);
            System.out.println("Created Gpu: " + createdGpu);
            return createdGpu;
        }
        return gpu;
    }

    @Override
    public Gpu update(Gpu gpu) {
        if (repository.existsById(gpu.getId())) {
            return repository.save(gpu);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

    @Override
    public void delete(Gpu gpu) {
        repository.delete(gpu);

    }

}
