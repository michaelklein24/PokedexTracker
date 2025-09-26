package com.klein.pokedextracker.user.repository;

import com.klein.pokedextracker.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByUsername(String username);
}
