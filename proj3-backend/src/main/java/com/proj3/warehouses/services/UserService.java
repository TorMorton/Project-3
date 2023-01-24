package com.proj3.warehouses.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

public interface UserService {

    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> login(Map<String, String> requestMap);

}
