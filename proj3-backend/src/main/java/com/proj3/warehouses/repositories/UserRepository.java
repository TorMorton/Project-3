package com.proj3.warehouses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.proj3.warehouses.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailId(@Param("email") String email);
}
