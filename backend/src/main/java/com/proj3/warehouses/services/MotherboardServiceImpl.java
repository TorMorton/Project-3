package com.proj3.warehouses.services;

import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.models.Motherboard;
import com.proj3.warehouses.repositories.CategoryRepository;
import com.proj3.warehouses.repositories.MotherboardRepository;
import com.proj3.warehouses.utils.WarehouseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MotherboardServiceImpl implements MotherboardService {
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    MotherboardRepository motherboardRepository;
//
//    @Override
//    public ResponseEntity<String> addMotherboard(Map<String, String> requestMap) {
//        try {
//            //.save() method returns the saved object and automatically sets the id
//            // throws illegal argument Exception if the object is null
//            // also no need for isAdmin() checks because the user is already authenticated
//            // with token and cannot hit end points without bearer token
//            motherboardRepository.save(getMotherboardFromMap(requestMap));
//            log.info("Inside addMotherboard() method");
//            return WarehouseUtils.getResponseEntity("Motherboard added successfully.", HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<List<Motherboard>> getAllMotherboards(String filterValue) {
//        try {
//            if (!(filterValue == null || filterValue.isEmpty()) && filterValue.equalsIgnoreCase("true")) {
//                return new ResponseEntity<>(motherboardRepository.getAllMotherboards(), HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    private Motherboard getMotherboardFromMap(Map<String, String> requestMap) {
//        Motherboard motherboard = new Motherboard();
//        motherboard.setManufacturer(requestMap.get("manufacturer"));
//        motherboard.setModel(requestMap.get("model"));
//        motherboard.setPrice(Integer.parseInt(requestMap.get("price")));
//        return motherboard;
//    }



}
