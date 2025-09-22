package com.klein.pokedextracker.pokemon.repository;

import com.klein.pokedextracker.pokemon.model.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonModel, Long> {
}
