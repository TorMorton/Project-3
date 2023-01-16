package com.proj3.warehouses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.proj3.warehouses.models.User;
import com.proj3.warehouses.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		System.out.println("Inside signup");
		try {
			if (validateSignUpMap(requestMap)) {
				User user = userRepository.findByEmailId(requestMap.get("email"));
				if (Objects.isNull(user)) {
					userRepository.save(getUserFromMap(requestMap));  // persist data into database
					return new ResponseEntity<String>("{\"message\":\"Uh-oh try again\"}", HttpStatus.CREATED);
					} else {
						return new ResponseEntity<String>("{\"message\":\"Uh-oh try again\"}", HttpStatus.BAD_REQUEST);
					}
				} else {
					return new ResponseEntity<String>("{\"message\":\"Uh-oh try again\"}", HttpStatus.BAD_REQUEST);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("{\"message\":\"Uh-oh try again\"}", HttpStatus.INTERNAL_SERVER_ERROR);

	}
		
	private boolean validateSignUpMap( Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("password"))
			return true;
		else 
			return false;
	}
	
	private User getUserFromMap(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		return user;
	}

}
