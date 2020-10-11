package com.example.backend.controller;

import com.example.backend.repository.UserRepository;
import com.example.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UserController {
    
    @Autowired
    private UserRepository userRepository; 

    // get all users 
    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    // create user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User newUser = userRepository.save(user); 
        return newUser; 
    }
}
