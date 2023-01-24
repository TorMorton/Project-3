package com.proj3.warehouses.services;

import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.jwt.JwtFilter;
import com.proj3.warehouses.jwt.JwtUtil;
import com.proj3.warehouses.jwt.MyUsersDetailsService;
import com.proj3.warehouses.models.User;
import com.proj3.warehouses.repositories.UserRepository;
import com.proj3.warehouses.utils.WarehouseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUsersDetailsService myUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside Signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap));
                    return WarehouseUtils.getResponseEntity("User created successfully", HttpStatus.OK);
                } else {
                    return WarehouseUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return WarehouseUtils.getResponseEntity(HttpConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name")
                && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email")
                && requestMap.containsKey("password")){
            return true;
        }
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

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside Login {}", requestMap);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestMap.get("email"),
                            requestMap.get("password"))
            );
            if (myUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                return new ResponseEntity<String>("{\token\":\"" + jwtUtil.generateToken(
                        myUsersDetailsService.getUserDetail().getEmail(),
                        myUsersDetailsService.getUserDetail().getRole()) + "\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("{\"message\":\"Admin approval necessary\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Exception in login {}", e.getMessage());
        }
        return new ResponseEntity<String>("{\"message\":\"Bad Credentials\"}", HttpStatus.BAD_REQUEST);
    }



}
