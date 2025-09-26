package com.klein.pokedextracker.auth.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private final String tokenType = "Bearer";
    private String token;

    public RegisterResponse(String token) {
        this.token = token;
    }

    public RegisterResponse() {
    }
}
