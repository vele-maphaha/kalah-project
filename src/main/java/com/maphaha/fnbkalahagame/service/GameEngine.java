package com.maphaha.fnbkalahagame.service;

import com.maphaha.fnbkalahagame.model.Game;
import com.maphaha.fnbkalahagame.model.Pit;
import com.maphaha.fnbkalahagame.service.rule.*;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {
    private final KalahRule chain;
    public GameEngine() {

        this.chain = new StartPitRule();
        chain.setNext(new DistributePitStoneRule())
                .setNext(new EndPitRule())
                .setNext(new GameOver());
    }

    public void play(Game game, Pit pit) throws Exception {
        this.chain.apply(game, pit);
    }

}
