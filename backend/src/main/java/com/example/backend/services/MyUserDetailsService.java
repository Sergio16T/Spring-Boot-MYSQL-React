package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.example.backend.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername: ");
        System.out.println(s);

        com.example.backend.model.User user = userRepository.findByEmail(s)
            .orElseThrow(() -> new UsernameNotFoundException("Username Not Found: " + s));

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
