package com.proj3.warehouses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.proj3.warehouses.models.Psu;
import com.proj3.warehouses.services.PsuService;

@Controller
@CrossOrigin
@RequestMapping("/psu_inventory")
public class PsuController {

    @Autowired
    private PsuService service;

    @GetMapping
    public @ResponseBody Iterable<Psu> findAll() {
        System.out.println("Inside findAll");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Psu findById(@PathVariable int id) {
        System.out.println("Inside findById");
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Psu save(@RequestBody Psu psu) {
        System.out.println("Inside controller save!!! " + psu);
        Psu psuCreated = service.save(psu);
        System.out.println("Inside controller save? " + psuCreated);
        return psu;
    }

    @PutMapping("/{id}")
    public @ResponseBody Psu update(@RequestBody Psu psu, @PathVariable int id) {
        System.out.println("Inside update");
        psu.setId(id);
        return service.update(psu);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteById(@PathVariable("id") int id) {
        System.out.println("Inside deleteById");
        service.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void delete(Psu psu) {
        System.out.println("Inside delete");
        service.delete(psu);
    }

}