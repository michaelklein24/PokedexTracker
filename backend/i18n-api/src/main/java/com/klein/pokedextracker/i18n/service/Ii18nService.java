package com.klein.pokedextracker.i18n.service;

public interface Ii18nService {
    String getString(String key);
    String getString(String key, String... args);
}
