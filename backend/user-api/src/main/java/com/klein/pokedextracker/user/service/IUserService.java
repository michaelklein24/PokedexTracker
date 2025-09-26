package com.klein.pokedextracker.user.service;

import com.klein.pokedextracker.user.model.UserModel;

import java.util.Optional;

public interface IUserService {
    Optional<UserModel> getUserByUsername(String username);
    Optional<UserModel> getUserByUserId(String userId);
    UserModel createUser(String username, String firstName);
}
