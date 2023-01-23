package com.proj3.warehouses.services;

import com.proj3.warehouses.models.Gpu;

public interface GpuService {

    Iterable<Gpu> findAll();
    Gpu findById(int id);
    Gpu save(Gpu gpu);
    Gpu update(Gpu gpu);
    void delete(Gpu gpu);
    void deleteById(int id);

}
