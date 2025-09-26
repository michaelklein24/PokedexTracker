package com.klein.pokedextracker.auth.repository;

import com.klein.pokedextracker.auth.model.PasswordId;
import com.klein.pokedextracker.auth.model.PasswordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordModel, PasswordId> {
    @Query("SELECT MAX(p.id.sequence) FROM PasswordModel p WHERE p.user.userId = :userId")
    Optional<Integer> findMaxSequenceByUserId(@Param("userId") String userId);

    @Query("SELECT p FROM PasswordModel p WHERE p.user.userId = :userId ORDER BY p.id.sequence DESC")
    Optional<PasswordModel> findActivePasswordForUser(@Param("userId") String userId);
}
