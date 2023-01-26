package com.proj3.warehouses.services;

import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.jwt.JwtFilter;
import com.proj3.warehouses.models.Category;
import com.proj3.warehouses.repositories.CategoryRepository;
import com.proj3.warehouses.utils.WarehouseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, false)) {
                    categoryRepository.save(getCategoryFromMap(requestMap, false));
                    return WarehouseUtils.getResponseEntity("Category added successfully.", HttpStatus.OK);
                }
            } else {
                return WarehouseUtils.getResponseEntity(HttpConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdded) {
        Category category = new Category();
        if (isAdded) {
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));
        return category;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories(String filterValue) {
        try {
            if (!(filterValue == null || filterValue.isEmpty()) && filterValue.equalsIgnoreCase("true")) {
                return new ResponseEntity<>(categoryRepository.getAllCategories(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, true)) {
                    Optional optional = categoryRepository.findById(Integer.parseInt(requestMap.get("id")));
                    if (optional.isPresent()) {
                        categoryRepository.save(getCategoryFromMap(requestMap, true));
                        return WarehouseUtils.getResponseEntity("Category updated successfully.", HttpStatus.OK);
                    } else {
                        return WarehouseUtils.getResponseEntity("Category not found.", HttpStatus.NOT_FOUND);
                    }
                }
                return WarehouseUtils.getResponseEntity(HttpConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return WarehouseUtils.getResponseEntity(HttpConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
