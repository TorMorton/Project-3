package com.proj3.warehouses.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@GetMapping("/{id}") // { } tells Spring Boot this endpoint has a path parameter we want to parse
	public @ResponseBody Warehouse findById(@PathVariable int id) { // if variable name does not match, specify it @PathVariable(name = "id")
		
		System.out.println("Inside findById");
		return service.findById(id);
	}
}
