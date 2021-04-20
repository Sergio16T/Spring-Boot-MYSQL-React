package com.example.backend.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -3091219679372683265L;
    private final String jwt;
    private final String maxAge;

    public AuthenticationResponse(String jwt, String maxAge) {
        this.jwt = jwt;
        this.maxAge = maxAge;
    }

    public String getJwt() {
        return jwt;
    }
    public String getMaxAge() {
        return maxAge;
    }
}
