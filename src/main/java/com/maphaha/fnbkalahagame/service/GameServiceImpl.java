package com.maphaha.fnbkalahagame.service;

import com.maphaha.fnbkalahagame.model.Game;
import com.maphaha.fnbkalahagame.model.Pit;
import com.maphaha.fnbkalahagame.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;

    private final GameEngine gameEngine;

    public GameServiceImpl(GameRepository gameRepository, GameEngine gameEngine) {
        this.gameRepository = gameRepository;
        this.gameEngine = gameEngine;
    }

    @Override
    public Game initGame(Integer initialPitStoneCount) {
        return gameRepository.create(initialPitStoneCount);
    }

    @Override
    public Game play(String gameId, Integer pitIndex) throws Exception {
        log.debug("gameId {}, pitIndex {}",gameId, pitIndex);

        Game game = gameRepository.findById(gameId);
        Pit pit = game.getBoard().getPitByPitIndex(pitIndex);

        gameEngine.play(game, pit);
        return  game;
    }


}
