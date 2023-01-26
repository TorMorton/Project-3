package com.proj3.warehouses.controllers;


import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.models.Motherboard;
import com.proj3.warehouses.services.MotherboardService;
import com.proj3.warehouses.utils.WarehouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MotherboardControllerImpl implements MotherboardController {

//    @Autowired
//    MotherboardService motherboardService;
//
//    @Override
//    public ResponseEntity<String> addMotherboard(Map<String, String> requestMap) {
//        try {
//            return motherboardService.addMotherboard(requestMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<List<Motherboard>> getAllMotherboards(String filterValue) {
//        try {
//            return motherboardService.getAllMotherboards(filterValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//


}
