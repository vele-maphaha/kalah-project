package com.maphaha.fnbkalahagame.service;

import com.maphaha.fnbkalahagame.model.Game;

public interface GameService {

    Game initGame(Integer pitNumber);

    Game play(String gameId, Integer pitId) throws Exception;
}
