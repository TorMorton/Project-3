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

import com.proj3.warehouses.models.Cpu;
import com.proj3.warehouses.services.CpuService;

@Controller
@CrossOrigin
@RequestMapping("/cpu_inventory")
public class CpuController {

    @Autowired
    private CpuService service;

    @GetMapping
    public @ResponseBody Iterable<Cpu> findAll() {
        System.out.println("Inside findAll");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Cpu findById(@PathVariable int id) {
        System.out.println("Inside findById");
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Cpu save(@RequestBody Cpu cpu) {
        System.out.println("Inside controller save!!! " + cpu);
        Cpu cpuCreated = service.save(cpu);
        System.out.println("Inside controller save? " + cpuCreated);
        return cpu;
    }

    @PutMapping("/{id}")
    public @ResponseBody Cpu update(@RequestBody Cpu cpu, @PathVariable int id) {
        System.out.println("Inside update");
        cpu.setId(id);
        return service.update(cpu);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteById(@PathVariable("id") int id) {
        System.out.println("Inside deleteById");
        service.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void delete(Cpu cpu) {
        System.out.println("Inside delete");
        service.delete(cpu);
    }

}