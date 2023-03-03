package com.maphaha.fnbkalahagame.service.rule;

import com.maphaha.fnbkalahagame.model.*;

public class StartPitRule extends KalahRule  {

    @Override
    public void apply(Game game, Pit startPit) throws Exception {


        checkPlayerTurnRule(game, startPit);
        checkEmptyStartRULE(startPit);
        this.next.apply(game, startPit);
    }

    private void checkPlayerTurnRule(Game game, Pit startPit) throws Exception {

        if(game.getGameStatus().equals(GameStatus.INIT)) {
            GameStatus gameStatus =  startPit.getPlayerIndex().equals(Player.PLAYER1_INDEX) ? GameStatus.PLAYER1_TURN : GameStatus.PLAYER2_TURN;
            game.setGameStatus(gameStatus);
        }

        if((game.getGameStatus().equals(GameStatus.PLAYER1_TURN) && startPit.getPitIndex() >= Board.PLAYER1_HOUSE) ||
                (game.getGameStatus().equals(GameStatus.PLAYER2_TURN) && startPit.getPitIndex() <= Board.PLAYER1_HOUSE)){
            throw new Exception("Incorrect pit to play");
        }
    }

    private void checkEmptyStartRULE(Pit startPit) throws Exception {

        if(startPit.getStoneCount() == 0){
            throw new Exception("Can not start from empty pit");
        }
    }

}
