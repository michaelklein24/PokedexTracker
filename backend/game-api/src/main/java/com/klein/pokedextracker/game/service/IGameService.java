package com.klein.pokedextracker.game.service;

import com.klein.pokedextracker.game.model.Game;
import com.klein.pokedextracker.pokemon.model.PokemonModel;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IGameService {
    public List<PokemonModel> getPokemonForGame(Game game) throws EntityNotFoundException;
}
