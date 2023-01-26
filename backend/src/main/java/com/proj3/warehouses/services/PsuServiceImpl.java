package com.proj3.warehouses.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj3.warehouses.models.Psu;
import com.proj3.warehouses.repositories.PsuRepository;

@Service
public class PsuServiceImpl implements PsuService{

    @Autowired
    private PsuRepository repository;

    @Override
    public Iterable<Psu> findAll() {
        return repository.findAll();
    }

    @Override
    public Psu findById(int id) {
        Optional<Psu> psu = repository.findById(id);
        return psu.isPresent() ? psu.get() : null;
    }

    @Override
    public Psu save(Psu psu) {
        if (!repository.existsById(psu.getId())) {
            System.out.println("Inside Save " + psu);
            Psu createdPsu = repository.save(psu);
            System.out.println("Created Psu: " + createdPsu);
            return createdPsu;
        }
        return psu;
    }

    @Override
    public Psu update(Psu psu) {
        if (repository.existsById(psu.getId())) {
            return repository.save(psu);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

    @Override
    public void delete(Psu psu) {
        repository.delete(psu);

    }
}