package com.klein.pokedextracker.game.service;

import com.klein.pokedextracker.core.exception.EntityNotFoundException;
import com.klein.pokedextracker.game.model.Game;
import com.klein.pokedextracker.game.repository.GameRepository;
import com.klein.pokedextracker.i18n.service.Ii18nService;
import com.klein.pokedextracker.pokemon.model.PokemonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService implements IGameService {

    private final GameRepository gameRepo;
    private final Ii18nService ii18nService;

    public List<PokemonModel> getPokemonForGame(Game game) throws EntityNotFoundException {
        return gameRepo.findById(game)
                .orElseThrow(() -> new EntityNotFoundException(ii18nService.getString("game.error.notFound.message", game.name())))
                .getPokemon();
    }
}
