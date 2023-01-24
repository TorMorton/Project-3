package com.proj3.warehouses.controllers;

import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.services.UserService;
import com.proj3.warehouses.utils.WarehouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
