package com.klein.pokedextracker.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IsUsernameAvailableRequest {
    @NotBlank
    @Size(min = 5)
    private String username;
}
