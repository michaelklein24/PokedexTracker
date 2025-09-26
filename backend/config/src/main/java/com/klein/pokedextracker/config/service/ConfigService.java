package com.klein.pokedextracker.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigService implements IConfigService {

    private final Environment environment;

    @Autowired
    public ConfigService(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean getBool(String key, boolean defaultValue) {
        String value = environment.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    @Override
    public String getString(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

    @Override
    public Long getLong(String key, Long defaultValue) {
        String value = environment.getProperty(key);
        try {
            return value != null ? Long.parseLong(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
