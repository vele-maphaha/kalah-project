package com.maphaha.fnbkalahagame.service.rule;

import com.maphaha.fnbkalahagame.model.Game;
import com.maphaha.fnbkalahagame.model.Pit;

public class DistributePitStoneRule extends KalahRule{

    @Override
    public void apply(Game game, Pit currentPit) throws Exception {



        Integer stoneToDistribute = currentPit.getStoneCount();
        currentPit.setStoneCount(0);

        for (int i = 0; i < stoneToDistribute; i++) {
            currentPit = game.getBoard().getNextPit(currentPit);

            if (currentPit.isDistributable(game.getGameStatus())) {
                currentPit.setStoneCount(currentPit.getStoneCount() + 1);
            }else{
                i--;
            }
        }

        this.next.apply(game, currentPit);
    }
}
