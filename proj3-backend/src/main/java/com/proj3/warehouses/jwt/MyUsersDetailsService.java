package com.proj3.warehouses.jwt;


import com.proj3.warehouses.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class MyUsersDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private com.proj3.warehouses.models.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", email);
        userDetail = userRepository.findByEmailId(email);
        if (!Objects.isNull(userDetail)) {
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }


    public com.proj3.warehouses.models.User getUserDetail() {
        com.proj3.warehouses.models.User user = userDetail;
        user.setPassword(null);
        return user;
    }


}
