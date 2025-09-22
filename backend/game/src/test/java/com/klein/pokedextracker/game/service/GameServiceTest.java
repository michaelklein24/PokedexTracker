package com.klein.pokedextracker.game.service;

import com.klein.pokedextracker.core.exception.EntityNotFoundException;
import com.klein.pokedextracker.game.model.Game;
import com.klein.pokedextracker.game.model.GameModel;
import com.klein.pokedextracker.game.repository.GameRepository;
import com.klein.pokedextracker.i18n.service.Ii18nService;
import com.klein.pokedextracker.pokemon.model.PokemonModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Mock
    private GameRepository gameRepo;

    @Mock
    private Ii18nService ii18nService;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getPokemonForGame_success() throws EntityNotFoundException {
        GameModel game = new GameModel();

        PokemonModel pokemon1 = new PokemonModel();
        PokemonModel pokemon2 = new PokemonModel();
        game.setPokemon(List.of(pokemon1, pokemon2));

        when(gameRepo.findById(Game.LZA)).thenReturn(Optional.of(game));

        // Act
        List<PokemonModel> result = gameService.getPokemonForGame(Game.LZA);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(gameRepo, times(1)).findById(Game.LZA);
        verifyNoMoreInteractions(gameRepo);
    }

    @Test
    void test_getPokemonForGame_throwEntityNotFoundExceptionForGameNotFound() {
        Game game = Game.LA;
        when(gameRepo.findById(Game.LA)).thenReturn(Optional.empty());
        when(ii18nService.getString("game.error.notFound.message", game.name()))
                .thenReturn("Game not found: " + game.toString());

        // Act & Assert
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> gameService.getPokemonForGame(game));

        assertEquals("Game not found: LA", ex.getMessage());
        verify(gameRepo, times(1)).findById(game);
        verify(ii18nService, times(1)).getString("game.error.notFound.message", game.name());
    }
}
