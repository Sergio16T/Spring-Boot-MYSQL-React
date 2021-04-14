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


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Credentials", "true");
        String username = null;
        String jwt = null;
        System.out.println("FILTER COOKIES");
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);

        if (cookies != null && cookies.length > 0) {
            System.out.println("cookie!");
            System.out.println(cookies);
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name);
                System.out.println(value);

                if ("jwt".equals(cookie.getName())) {
                    System.out.println(name);
                    jwt = value;
                }
            }
        }

        System.out.println("FILTER COOKIES");
        System.out.println(jwt);

        if (jwt != null) {
            System.out.println("JWT extract");
            System.out.println(jwt);
            username = jwtUtil.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            System.out.println("JWT userDetails");
            if (jwtUtil.validateToken(jwt, userDetails)) {
                System.out.println("JWT validate");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}