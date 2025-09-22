package com.klein.pokedextracker.pokemon.service;

import com.klein.pokedextracker.game.model.Game;
import com.klein.pokedextracker.pokemon.model.PokemonModel;
import com.klein.pokedextracker.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService implements IPokemonService {

    private final PokemonRepository pokemonRepo;

    public List<PokemonModel> getAllPokemon() {
        return pokemonRepo.findAll();
    }

    public PokemonModel getPokemonBySequenceNumber(Long sequenceNumber) {
        return null;
    }

    public List<PokemonModel> getPokemonForGame(Game game) {
        return List.of();
    }

}
