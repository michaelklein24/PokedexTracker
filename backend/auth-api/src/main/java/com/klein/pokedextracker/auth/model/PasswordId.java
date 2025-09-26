package com.klein.pokedextracker.auth.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PasswordId {
    private String userId;
    private Integer sequence;

    public PasswordId(String userId, Integer sequence) {
        this.userId = userId;
        this.sequence = sequence;
    }

    public PasswordId() {
    }
}
