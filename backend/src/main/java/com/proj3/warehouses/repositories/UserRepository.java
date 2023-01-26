package com.proj3.warehouses.repositories;

import com.proj3.warehouses.wrappers.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.proj3.warehouses.models.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailId(@Param("email") String email);
	List<UserWrapper> getAllUsers();

	List<String> getAllAdmins();

	@Transactional
	@Modifying
	Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

	User findByEmail(String username);

}
