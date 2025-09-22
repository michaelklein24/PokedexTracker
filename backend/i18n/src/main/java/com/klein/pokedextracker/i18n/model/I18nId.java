package com.klein.pokedextracker.i18n.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Locale;

@Embeddable
@Data
public class I18nId {
    private String key;
    private Locale locale;

    public I18nId() {
    }

    public I18nId(String key, Locale locale) {
        this.key = key;
        this.locale = locale;
    }
}
