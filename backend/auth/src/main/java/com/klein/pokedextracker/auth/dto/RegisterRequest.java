package com.klein.pokedextracker.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 5)
    private String username;

    @NotBlank()
    @Size(min = 6)
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).*$"
    )
    private String plainTextPassword;

    @NotBlank
    private String firstName;
}
