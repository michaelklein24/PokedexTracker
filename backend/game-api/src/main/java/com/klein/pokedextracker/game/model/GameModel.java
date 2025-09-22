package com.klein.pokedextracker.game.model;

import com.klein.pokedextracker.pokemon.model.PokemonModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "gme_game")
public class GameModel {

    @Id
    @Enumerated(EnumType.STRING)
    private Game name;

    @ManyToMany
    @JoinTable(
            name = "gme_game_pokemon",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<PokemonModel> pokemon;
}
