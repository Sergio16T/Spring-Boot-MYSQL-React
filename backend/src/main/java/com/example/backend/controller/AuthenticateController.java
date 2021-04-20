package com.example.backend.controller;

import com.example.backend.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class AuthenticateController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/auth")
    public ResponseEntity< User > authenticate(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException {
            String username =  SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AccessDeniedException("Unable to find user with user name: " + username));

            return ResponseEntity.ok(user);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return ResponseEntity.ok("Logged out");
    }
}