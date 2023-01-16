package com.proj3.warehouses.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.proj3.warehouses.models.User;

public interface UserService {

	Iterable<User> findAll();
	
	ResponseEntity<String> signUp( Map<String, String> requestMap);
	
}
