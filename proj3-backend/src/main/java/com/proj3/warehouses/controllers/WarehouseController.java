package com.proj3.warehouses.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj3.warehouses.models.Warehouse;
import com.proj3.warehouses.services.WarehouseService;

@Controller
@CrossOrigin
@RequestMapping("/warehouses")
public class WarehouseController {

	@Autowired
	private WarehouseService service;


	@GetMapping
	public @ResponseBody Iterable<Warehouse> findAll() {
		System.out.println("Inside findAll");
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Warehouse findById(@PathVariable int id) {
		
		System.out.println("Inside findById");
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody Warehouse update(@RequestBody Warehouse warehouse, @PathVariable int id) {
		System.out.println("Inside update");
		warehouse.setId(id);
		return service.update(warehouse);
	}
	
}
