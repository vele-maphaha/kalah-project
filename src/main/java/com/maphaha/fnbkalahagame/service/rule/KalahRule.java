package com.maphaha.fnbkalahagame.service.rule;

import com.maphaha.fnbkalahagame.model.Game;
import com.maphaha.fnbkalahagame.model.Pit;

public abstract class KalahRule {

    protected KalahRule next;
    public abstract void apply(Game game, Pit currentPit) throws Exception;

    public KalahRule setNext(KalahRule next) {
        this.next = next;
        return next;
    }
}
