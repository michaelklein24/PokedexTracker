package com.klein.pokedextracker.config.service;

public interface IConfigService {
    boolean getBool(String key, boolean defaultValue);
    String getString(String key, String defaultValue);
    Long getLong(String key, Long defaultValue);
}
