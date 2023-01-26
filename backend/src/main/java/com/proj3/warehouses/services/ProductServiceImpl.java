package com.proj3.warehouses.services;

import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.jwt.JwtFilter;
import com.proj3.warehouses.models.Category;
import com.proj3.warehouses.models.Product;
import com.proj3.warehouses.repositories.ProductRepository;
import com.proj3.warehouses.utils.WarehouseUtils;
import com.proj3.warehouses.wrappers.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, false)) {
                    productRepository.save(getProductFromMap(requestMap, false));
                    return WarehouseUtils.getResponseEntity("Product Added", HttpStatus.OK);
                }
                return WarehouseUtils.getResponseEntity(HttpConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else
                return WarehouseUtils.getResponseEntity(HttpConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, boolean isAdded) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));
        Product product = new Product();
        if (isAdded) {
            product.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProducts() {
        try {
            return new ResponseEntity<>(productRepository.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, true)) {
                    Optional<Product> optional = productRepository.findById(Integer.parseInt(requestMap.get("id")));
                    if (optional.isPresent()) {
                        Product product = getProductFromMap(requestMap, true);
                        product.setStatus(optional.get().getStatus());
                        productRepository.save(product);
                        return WarehouseUtils.getResponseEntity("Product Updated", HttpStatus.OK);
                    } else {
                        return WarehouseUtils.getResponseEntity("Product Id does not exits.", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return WarehouseUtils.getResponseEntity(HttpConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return WarehouseUtils.getResponseEntity(HttpConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProductById(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
               Optional optional = productRepository.findById(id);
               if (optional.isPresent()) {
                    productRepository.deleteById(id);
                    return WarehouseUtils.getResponseEntity("Product Deleted", HttpStatus.OK);
               }
               return WarehouseUtils.getResponseEntity("Product Id does not Exist", HttpStatus.BAD_REQUEST);
            } else {
                return WarehouseUtils.getResponseEntity(HttpConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProductStatus(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = productRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (optional.isPresent()) {
                    productRepository.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return WarehouseUtils.getResponseEntity("Product Status Updated", HttpStatus.OK);
                }
                return WarehouseUtils.getResponseEntity("Product Id does not Exist", HttpStatus.BAD_REQUEST);
            } else {
                return WarehouseUtils.getResponseEntity(HttpConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProductsByCategory(Integer id) {
        try {
            return new ResponseEntity<>(productRepository.getAllProductsByCategory(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductWrapper> getProductById(Integer id) {
        try {
            return new ResponseEntity<>(productRepository.getProductById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}