package com.example.backend.controller;

import com.example.backend.repository.UserRepository;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int createUser(@RequestBody User user) {
        int newUserId = userRepository.save(user); 
        return newUserId; 
    }

    //get user by id 
    @GetMapping("/users/{id}") // path variable use annotation
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id) //findById can return optional so this gives us the orElseThrow method as an option
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id)); 

        return ResponseEntity.ok(user);
    }

    // update user 
    @PutMapping("/users/{id}")
    public ResponseEntity<Integer> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id) //findById can return type "optional" so this gives us the orElseThrow method as an option
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id)); 

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

        int updatedUser = userRepository.update(user); 
        return ResponseEntity.ok(updatedUser);
    }

    // delete user 
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
         userRepository.delete(id);
         Map<String, Boolean> response = new HashMap<>(); 
         response.put("Deleted", true); 
         return ResponseEntity.ok(response); 
    }
}
