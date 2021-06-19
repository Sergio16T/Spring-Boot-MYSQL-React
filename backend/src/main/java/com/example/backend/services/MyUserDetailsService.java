package com.example.backend.services;

import com.example.backend.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.example.backend.repository.AccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Account user = accountRepository.findByEmail(s)
            .orElseThrow(() -> new UsernameNotFoundException("Username Not Found: " + s));

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
