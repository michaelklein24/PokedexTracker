package com.klein.pokedextracker.i18n.repository;

import com.klein.pokedextracker.i18n.model.I18nId;
import com.klein.pokedextracker.i18n.model.I18nModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface I18nRepository extends JpaRepository<I18nModel, I18nId> {
}
