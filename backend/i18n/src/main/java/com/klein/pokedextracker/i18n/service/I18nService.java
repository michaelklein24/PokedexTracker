package com.klein.pokedextracker.i18n.service;

import com.klein.pokedextracker.i18n.model.I18nId;
import com.klein.pokedextracker.i18n.model.I18nModel;
import com.klein.pokedextracker.i18n.repository.I18nRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class I18nService implements Ii18nService {

    private I18nRepository i18nRepo;
    private final MessageSource messageSource;

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String[] args) {
        Locale locale = Locale.ENGLISH;
        I18nId id = new I18nId(key, locale);
        I18nModel i18nModel = i18nRepo.findById(id).orElse(null);

        if (i18nModel == null) {
            String defaultMessage = "<Missing Resource: Key: \"" + key + "\" Locale: \"" + locale.toString() + "\">";
            return messageSource.getMessage(key, args, defaultMessage, locale);
        } else {
            return i18nModel.getValue();
        }
    }
}
