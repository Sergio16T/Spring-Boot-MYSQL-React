package com.example.backend.controller;

import com.example.backend.repository.AccountRepository;
import com.example.backend.services.MyUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.backend.exception.InternalServerErrorException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.AuthenticationResponse;
import com.example.backend.model.Account;
import com.example.backend.utilities.JwtUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")

public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

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
    public ResponseEntity<?> signUp(@RequestBody Account account, HttpServletResponse response) throws InternalServerErrorException {
        Boolean emailUnavailable = accountRepository.checkIfEmailUnavailable(account.getEmail()).isPresent();
        if (emailUnavailable) {
            System.out.println("Email Unavailable");
            throw new InternalServerErrorException("Email Unavailable");
        }

        String password = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(password);

        accountRepository.save(account);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(account.getEmail());

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
    @PostMapping("/account/signin")
    public ResponseEntity<?>  signIn(@RequestBody Account account, HttpServletResponse response) throws InternalServerErrorException {
        Account dbAccount = accountRepository.findByEmail(account.getEmail())
			.orElseThrow(() -> new ResourceNotFoundException("Account not found with email: " + account.getEmail()));

        try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dbAccount.getEmail(), account.getPassword())
			);
		}
		catch (BadCredentialsException e) {
            throw new InternalServerErrorException("Incorrect Credentials");
		}
        final UserDetails userDetails = userDetailsService.loadUserByUsername(account.getEmail());
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

    @PostMapping("/account/signout")
    public ResponseEntity<?> signOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return ResponseEntity.ok("Logged out");
    }

    // get all accounts
    @GetMapping("/account")
    public List<Account> getAllAccounts() {
        List<Account> account = accountRepository.findAll();
        return account;
    }

    // create account
    @PostMapping("/account")
    public int createAccount(@RequestBody Account account) {
        // Need to update to send user invitation to set password and login
        int newAccountId = accountRepository.save(account);
        return newAccountId;
    }

    // get account by id
    @GetMapping("/account/{id}") // path variable use annotation
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountRepository.findById(id) //findById can return optional so this gives us the orElseThrow method as an option
            .orElseThrow(() -> new ResourceNotFoundException("account not found with id:" + id));

        return ResponseEntity.ok(account);
    }

    // update account
    @PutMapping("/account/{id}")
    public ResponseEntity<Integer> updateaccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account account = accountRepository.findById(id) //findById can return type "optional" so this gives us the orElseThrow method as an option
            .orElseThrow(() -> new ResourceNotFoundException("account not found with id:" + id));

        account.setFirstName(accountDetails.getFirstName());
        account.setLastName(accountDetails.getLastName());
        account.setEmail(accountDetails.getEmail());

        int updatedAccount = accountRepository.update(account);
        return ResponseEntity.ok(updatedAccount);
    }

    // delete account
    @DeleteMapping("/account/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
         accountRepository.delete(id);
         Map<String, Boolean> response = new HashMap<>();
         response.put("Deleted", true);
         return ResponseEntity.ok(response);
    }
}