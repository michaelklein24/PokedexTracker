package com.klein.pokedextracker.pokedex.model;

import com.klein.pokedextracker.pokemon.model.PokemonModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pkx_pokedex_entry")
public class PokedexEntryModel {

    @EmbeddedId
    @Delegate
    private PokedexEntryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pokedexId")
    @JoinColumn(name = "pokedex_id", nullable = false, insertable=false, updatable=false)
    private PokedexModel pokedex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_sequence_number", nullable = false, insertable=false, updatable=false)
    private PokemonModel pokemon;

    @OneToMany(mappedBy = "pokedexEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeModel> challenges = new ArrayList<>();
}


