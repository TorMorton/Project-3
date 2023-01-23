package com.proj3.warehouses.repositories;

import java.util.Optional;

import com.proj3.warehouses.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}