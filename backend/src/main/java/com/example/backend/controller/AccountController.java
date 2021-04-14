package com.example.backend.controller;

import com.example.backend.repository.UserRepository;
import com.example.backend.exception.InternalServerErrorException;
import com.example.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")

public class AccountController {
    @Autowired
    private UserRepository userRepository;

    // Signup
    @PostMapping("/account/signup")
    public ResponseEntity<Integer>  signUp(@RequestBody User user) throws InternalServerErrorException {
        Boolean emailUnavailable = userRepository.findByEmail(user.getEmail()).isPresent();
        if (emailUnavailable) {
            System.out.println("Email Unavailable");
            throw new InternalServerErrorException("Email Unavailable");
        }
        System.out.println("signup user");
        int newUserId = userRepository.save(user);
        return ResponseEntity.ok(newUserId);
    }
}