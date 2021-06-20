package com.example.backend.controller;

import com.example.backend.repository.AccountRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.backend.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/auth")
    public ResponseEntity< Account > authenticate(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new AccessDeniedException("Unable to find user with username: " + username));

            return ResponseEntity.ok(account);
    }
}