package com.example.backend.filters;

import com.example.backend.services.MyUserDetailsService;
import com.example.backend.utilities.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Arrays;



@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String username = null;
        String jwt = null;
        UserDetails userDetails = null;
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            chain.doFilter(request, response);
            return;
        }

        Stream<Cookie> cookieStream = Arrays.stream(cookies);

        cookies = cookieStream.filter(cookie -> { return "jwt".equals(cookie.getName()); })
            .toArray(size -> new Cookie[size]);

        if (cookies.length > 0) {
            String value = cookies[0].getValue();
            jwt = value;
        }


        if (jwt != null && jwt != "") {
            System.out.println("JWT extract");
            username = jwtUtil.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            userDetails = this.userDetailsService.loadUserByUsername(username);
            System.out.println("JWT userDetails");
        }

        if (userDetails != null && jwtUtil.validateToken(jwt, userDetails)) {
            System.out.println("JWT validate");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        chain.doFilter(request, response);
    }

}