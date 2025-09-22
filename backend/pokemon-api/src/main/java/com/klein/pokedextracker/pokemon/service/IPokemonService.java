package com.klein.pokedextracker.pokemon.service;


import com.klein.pokedextracker.pokemon.model.PokemonModel;

import java.util.List;

public interface IPokemonService {
    List<PokemonModel> getAllPokemon();
    PokemonModel getPokemonBySequenceNumber(Long sequenceNumber);
}
