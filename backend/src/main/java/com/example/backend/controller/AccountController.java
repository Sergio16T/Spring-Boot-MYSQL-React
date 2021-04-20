package com.example.backend.controller;

import com.example.backend.repository.UserRepository;
import com.example.backend.services.MyUserDetailsService;

import javax.servlet.http.HttpServletResponse;

import com.example.backend.exception.InternalServerErrorException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.AuthenticationResponse;
import com.example.backend.model.User;
import com.example.backend.utilities.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/account")

public class AccountController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<?>  signUp(@RequestBody User user) throws InternalServerErrorException {
        Boolean emailUnavailable = userRepository.checkIfEmailUnavailable(user.getEmail()).isPresent();
        if (emailUnavailable) {
            System.out.println("Email Unavailable");
            throw new InternalServerErrorException("Email Unavailable");
        }

        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
        String maxAge = String.valueOf(60 * 60 * 7);

        // Cookie cookie = new Cookie("jwt", jwt);
		// cookie.setMaxAge(60 * 60 * 7); // expires in 7 hours
		// cookie.setHttpOnly(true);
        // response.addHeader("access-control-expose-headers", "Set-Cookie");
        // response.addCookie(cookie); // @Revisit: unable to set cookie from server in development. For now set on client

        return ResponseEntity.ok(new AuthenticationResponse(jwt, maxAge));
    }
    // Signin
    @PostMapping("/signin")
    public ResponseEntity<?>  signIn(@RequestBody User user, HttpServletResponse response) throws InternalServerErrorException {
        User dbUser = userRepository.findByEmail(user.getEmail())
			.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + user.getEmail()));

        try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dbUser.getEmail(), user.getPassword())
			);
		}
		catch (BadCredentialsException e) {
            throw new InternalServerErrorException("Incorrect Credentials");
		}
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		System.out.println("user details");
		System.out.println(userDetails);

		final String jwt = jwtTokenUtil.generateToken(userDetails);
        String maxAge = String.valueOf(60 * 60 * 7); // expires in 7 hours
        // String maxAge = String.valueOf(5);

		// Cookie cookie = new Cookie("jwt", jwt);
		// cookie.setMaxAge(60 * 60 * 7); // expires in 7 hours
		// cookie.setHttpOnly(true);
        // response.addHeader("access-control-expose-headers", "Set-Cookie");
        // response.addCookie(cookie); // @Revisit: unable to set cookie from server in development. For now set on client
		// response.addHeader("Access-Control-Allow-Credentials", "true");

        return ResponseEntity.ok(new AuthenticationResponse(jwt, maxAge));
    }
}