package com.proj3.warehouses.services;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proj3.warehouses.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	private com.proj3.warehouses.models.User userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Inside loadByUsername");
		userDetail = userRepository.findByEmailId(username);
		if (!Objects.isNull(userDetail))
			return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
		else
			throw new UsernameNotFoundException("User not found.");
	}
	
	public com.proj3.warehouses.models.User getUserDetail() {
		return userDetail;
	}

}
