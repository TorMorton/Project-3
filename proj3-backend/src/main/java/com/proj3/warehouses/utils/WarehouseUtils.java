package com.proj3.warehouses.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WarehouseUtils {

    private WarehouseUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
