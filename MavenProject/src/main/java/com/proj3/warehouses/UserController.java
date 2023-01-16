package com.proj3.warehouses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.proj3.*;
import com.proj3.warehouses.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	
	
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewWarehouse(@RequestParam String id, @RequestParam String manufacturer, @RequestParam String model, @RequestParam int cost) {

        CpuWarehouse user = new CpuWarehouse();
        user.setId(id);
        user.setManufacturer(manufacturer);
        user.setModel(model);
        userRepository.save(user);
        return "User Created";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable < CpuWarehouse > getAllUsers() {
        return userRepository.findAll();
    }

}

