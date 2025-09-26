package com.klein.pokedextracker.auth.service;

import com.klein.pokedextracker.user.model.UserModel;

public interface IAuthService {
    UserModel registerUser(String username, String plainTextPassword, String firstName);
    UserModel loginUser(String username, String plainTextPassword);
}
