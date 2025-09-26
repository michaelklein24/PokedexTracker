package com.klein.pokedextracker.user.service;

import com.klein.pokedextracker.user.model.UserModel;
import com.klein.pokedextracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepo;

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<UserModel> getUserByUserId(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public UserModel createUser(String username, String firstName) {
        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setFirstName(firstName);
        return userRepo.save(userModel);
    }
}
