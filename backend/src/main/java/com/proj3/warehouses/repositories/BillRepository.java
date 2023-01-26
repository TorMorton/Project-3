package com.proj3.warehouses.repositories;

import com.proj3.warehouses.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> getAllBills();

    List<Bill> getBillsByUserName(@Param("username") String username);

}
