package com.proj3.warehouses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proj3.warehouses.models.User;
import com.proj3.warehouses.services.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
//	@PostMapping(path = "/signup")
//	@ResponseStatus(HttpStatus.CREATED)
//	public @ResponseBody User save(@RequestBody User user) {
//		return user;
//	}
	
	@PostMapping(path = "/signup")
	public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap) {
		try {
			return userService.signUp(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("{\"message\":\"Uh-oh try again\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
	
}