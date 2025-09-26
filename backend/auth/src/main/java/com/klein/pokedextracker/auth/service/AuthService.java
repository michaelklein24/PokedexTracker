package com.klein.pokedextracker.auth.service;

import com.klein.pokedextracker.auth.exception.UsernameAlreadyExistsException;
import com.klein.pokedextracker.i18n.service.Ii18nService;
import com.klein.pokedextracker.user.model.UserModel;
import com.klein.pokedextracker.user.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserService userService;
    private final Ii18nService i18nService;
    private final PasswordService passwordService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserModel registerUser(String username, String plainTextPassword, String firstName) {
        if (userService.getUserByUsername(username).isPresent()) {
            String message = i18nService.getString("auth.error.usernameAlreadyExists", username);
            throw new UsernameAlreadyExistsException(message);
        }

        UserModel userModel = createUserAndPassword(username, plainTextPassword, firstName);

        Authentication authentication = authenticateUser(userModel.getUserId(), plainTextPassword);
        return userModel;
    }

    @Transactional
    private UserModel createUserAndPassword(String username, String plainTextPassword, String firstName) {
        UserModel userModel = userService.createUser(username, firstName);
        passwordService.createPassword(plainTextPassword, userModel);
        return userModel;
    }

    @Override
    public UserModel loginUser(String username, String plainTextPassword) {
        UserModel userModel = userService.getUserByUsername(username).orElseThrow(
                () -> new BadCredentialsException(i18nService.getString("auth.error.userWithUsernameNotFound.message", username)));

        Authentication authentication = authenticateUser(userModel.getUserId(), plainTextPassword);

        return userModel;
    }

    private Authentication authenticateUser(String username, String plainTextPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, plainTextPassword));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
