package com.klein.pokedextracker.i18n.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Delegate;

@Data
@Entity
@Table(name = "i18n_override")
public class I18nModel {

    @EmbeddedId
    @Delegate
    private I18nId id;
    private String value;
}
