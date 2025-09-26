package com.klein.pokedextracker.auth.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String tokenType = "Bearer";
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse() { }
}
