package com.klein.pokedextracker.pokemon.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Table(name = "pkm_pokemon")
@Entity
public class PokemonModel {
    @Id
    private Long sequenceNumber;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "pkm_pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_sequence_number"),
            inverseJoinColumns = @JoinColumn(name = "type")
    )
    private Set<TypeModel> types;

}
