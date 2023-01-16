package com.proj3.warehouses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.proj3.warehouses.models.Gpu;
import com.proj3.warehouses.services.GpuService;


@Controller
@RequestMapping("/gpu_inventory")
public class GpuController {
    
	@Autowired
	private GpuService service;
	
	@GetMapping
	public @ResponseBody Iterable<Gpu> findAll() {
		System.out.println("Inside findAll");
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Gpu findById(@PathVariable int id) {
		System.out.println("Inside findById");
		return service.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Gpu save(@RequestBody Gpu gpu) {
		System.out.println("Inside controller save!!! " + gpu);
		Gpu gpuCreated = service.save(gpu);
		System.out.println("Inside controller save? " + gpuCreated);
		return gpu;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody Gpu update(@RequestBody Gpu gpu, @PathVariable int id) {
		System.out.println("Inside update");
		gpu.setId(id);
		return service.update(gpu);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteById(@PathVariable("id") int id) {
		System.out.println("Inside deleteById");
		service.deleteById(id);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void delete(Gpu gpu) {
		System.out.println("Inside delete");
		service.delete(gpu);
	}
	
}
    
    

