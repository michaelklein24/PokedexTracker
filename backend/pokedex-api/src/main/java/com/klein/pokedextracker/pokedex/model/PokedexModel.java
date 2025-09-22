package com.klein.pokedextracker.pokedex.model;

import com.klein.pokedextracker.game.model.Game;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pkx_pokedex")
public class PokedexModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokedex_seq")
    @SequenceGenerator(name = "pokedex_seq", sequenceName = "pokedex_seq", allocationSize = 1)
    private Long pokedexId;

    @Column(nullable = false)
    private Game game;

    @OneToMany(mappedBy = "pokedex", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PokedexEntryModel> entries = new ArrayList<>();
}
