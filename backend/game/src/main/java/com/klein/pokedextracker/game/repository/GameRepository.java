package com.klein.pokedextracker.game.repository;

import com.klein.pokedextracker.game.model.Game;
import com.klein.pokedextracker.game.model.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Game> {}