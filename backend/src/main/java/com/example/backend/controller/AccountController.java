package com.example.backend.controller;

import com.example.backend.repository.UserRepository;
import com.example.backend.services.MyUserDetailsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.backend.exception.InternalServerErrorException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.AuthenticationResponse;
import com.example.backend.model.User;
import com.example.backend.utilities.JwtUtil;

// import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")

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
    @PostMapping("/account/signup")
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

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    // Signin
    @PostMapping("/account/signin")
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
		Cookie cookie = new Cookie("jwt", jwt);
		cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
		cookie.setHttpOnly(true);

        // response.addCookie(cookie); // @Revisit: unable to set cookie from server in development. For now set on client
		response.addHeader("Access-Control-Allow-Credentials", "true");

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    // Authenticate
    @GetMapping("/account/auth")
    public ResponseEntity< ? > authenticate(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException {
            String username =  SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AccessDeniedException("Unable to find user with user name: " + username));

            return ResponseEntity.ok(user);
    }

}