package com.maphaha.fnbkalahagame.service.rule;

import com.maphaha.fnbkalahagame.model.Game;
import com.maphaha.fnbkalahagame.model.GameStatus;
import com.maphaha.fnbkalahagame.model.Pit;

public class EndPitRule extends KalahRule {

    @Override
    public void apply(Game game, Pit endPit) throws Exception {

        lastEmptyPitRule(game, endPit);
        nextPlayerTurnRue(game, endPit);
        this.next.apply(game, endPit);
    }

    private void lastEmptyPitRule(Game game, Pit endPit) throws Exception {

        if (!endPit.isHouse() && endPit.isPlayerPit(game.getGameStatus()) && endPit.getStoneCount().equals(1) ){
            Pit oppositePit = game.getBoard().getOppositePit(endPit);
            if (oppositePit.getStoneCount() > 0) {
                Pit house = game.getBoard().getPlayerHouse(endPit.getPlayerIndex());
                house.setStoneCount((house.getStoneCount() + oppositePit.getStoneCount()) + endPit.getStoneCount());
                oppositePit.setStoneCount(0);
                endPit.setStoneCount(0);
            }
        }
    }

    private void nextPlayerTurnRue(Game game, Pit endPit){

        if(endPit.isPlayer1House() && game.getGameStatus().equals(GameStatus.PLAYER1_TURN)){
            game.setGameStatus(GameStatus.PLAYER1_TURN);
        }
        else if(endPit.isPlayer2House() && game.getGameStatus().equals(GameStatus.PLAYER2_TURN)){
            game.setGameStatus(GameStatus.PLAYER2_TURN);
        }
        else{
            GameStatus changeStage = game.getGameStatus() == GameStatus.PLAYER1_TURN? GameStatus.PLAYER2_TURN : GameStatus.PLAYER1_TURN;
            game.setGameStatus(changeStage);
        }
        game.updateTime();
    }

}
