package com.proj3.warehouses.services;

import com.proj3.warehouses.jwt.JwtFilter;
import com.proj3.warehouses.wrappers.UserWrapper;
import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.jwt.JwtUtil;
import com.proj3.warehouses.jwt.MyUsersDetailsService;
import com.proj3.warehouses.models.User;
import com.proj3.warehouses.repositories.UserRepository;
import com.proj3.warehouses.utils.EmailUtils;
import com.proj3.warehouses.utils.WarehouseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;


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
                && requestMap.containsKey("password")) {
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
                return new ResponseEntity<String>("{\"token\":\"" + jwtUtil.generateToken(
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

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error("Exception in getAllUsers {}", e.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateUser(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (optional.isPresent()) {
                    userRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmins(requestMap.get("status"), optional.get().getEmail(), userRepository.getAllAdmins());
                    return WarehouseUtils.getResponseEntity("User updated successfully", HttpStatus.OK);

                } else {
                    return WarehouseUtils.getResponseEntity("User not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return WarehouseUtils.getResponseEntity(HttpConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error("Exception in updateUser {}", e.getMessage());
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmins(String status, String user, List<String> allAdmins) {
        allAdmins.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true"))  {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved",
                    "USER: " + user +
                            "\nhas been approved by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmins);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled",
                    "USER: " + user +
                            "\nhas been disabled by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmins);
        }

    }

    @Override
    public ResponseEntity<String> checkToken() {
        return WarehouseUtils.getResponseEntity("Token is valid", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User user = userRepository.findByEmail(jwtFilter.getCurrentUser());
            if (user.getPassword().equals(requestMap.get("oldPassword"))) {
                user.setPassword(requestMap.get("newPassword"));
                userRepository.save(user);
                return WarehouseUtils.getResponseEntity("Password changed successfully", HttpStatus.OK);
            } else {
                return WarehouseUtils.getResponseEntity("Old password is incorrect", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Exception in changePassword {}", e.getMessage());
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            User user = userRepository.findByEmail(requestMap.get("email"));
            if (user != null && !user.getEmail().isEmpty()) {
                emailUtils.forgotMail(user.getEmail(), "Credentials by GameRigs Computing Management System", user.getPassword());
            }
            return WarehouseUtils.getResponseEntity("Please check your email.", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity(HttpConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

