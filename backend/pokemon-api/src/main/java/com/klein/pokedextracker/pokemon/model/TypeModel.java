package com.klein.pokedextracker.pokemon.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pkm_type")
public class TypeModel {

    @Id
    private String name;
}