package com.proj3.warehouses.repositories;

import com.proj3.warehouses.models.Product;
import com.proj3.warehouses.wrappers.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<ProductWrapper> getAllProducts();

    @Modifying
    @Transactional
    Integer updateProductStatus(@Param("status") String status, @Param("id") Integer id);

    List<ProductWrapper> getAllProductsByCategory(@Param("id") Integer id);

    ProductWrapper getProductById(@Param("id") Integer id);
}
