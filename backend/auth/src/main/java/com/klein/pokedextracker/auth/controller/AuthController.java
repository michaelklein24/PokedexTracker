package com.klein.pokedextracker.auth.controller;

import com.klein.pokedextracker.auth.dto.LoginRequest;
import com.klein.pokedextracker.auth.dto.LoginResponse;
import com.klein.pokedextracker.auth.dto.RegisterRequest;
import com.klein.pokedextracker.auth.dto.RegisterResponse;
import com.klein.pokedextracker.auth.service.IAuthService;
import com.klein.pokedextracker.auth.service.JwtHelper;
import com.klein.pokedextracker.user.model.UserModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;
    private final JwtHelper jwtHelper;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged user in",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        UserModel loggedInUser = authService.loginUser(request.getUsername(), request.getPlainTextPassword());
        String token = jwtHelper.generateToken(loggedInUser);
        return new LoginResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registers a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        UserModel registeredUser = authService.registerUser(
                request.getUsername(), request.getPlainTextPassword(), request.getFirstName());
        String token = jwtHelper.generateToken(registeredUser);
        return new RegisterResponse(token);
    }
}
