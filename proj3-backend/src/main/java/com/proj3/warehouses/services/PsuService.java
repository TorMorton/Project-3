package com.proj3.warehouses.services;

import com.proj3.warehouses.models.Psu;

public interface PsuService {

    Iterable<Psu> findAll();
    Psu findById(int id);
    Psu save(Psu psu);
    Psu update(Psu psu);
    void delete(Psu psu);
    void deleteById(int id);

}
