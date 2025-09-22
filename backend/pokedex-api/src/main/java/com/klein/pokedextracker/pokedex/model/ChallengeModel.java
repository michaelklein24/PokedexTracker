package com.klein.pokedextracker.pokedex.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pkx_pokedex_entry_challenge")
public class ChallengeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_seq")
    @SequenceGenerator(name = "challenge_seq", sequenceName = "challenge_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "pokemon_sequence_number"),
            @JoinColumn(name = "pokedex_id")
    })
    private PokedexEntryModel pokedexEntry;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Long requirementQuantity;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeProgressModel> progressList = new ArrayList<>();
}
