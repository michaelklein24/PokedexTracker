package com.klein.pokedextracker.pokedex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PokedexEntryId {
    @Column(name = "pokemon_sequence_number")
    private String pokemonSequenceNumber;
    @Column(name = "pokedex_id")
    private Long pokedexId;
}
