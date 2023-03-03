package com.maphaha.fnbkalahagame.service;

import com.maphaha.fnbkalahagame.model.Board;
import com.maphaha.fnbkalahagame.model.Game;
import com.maphaha.fnbkalahagame.model.GameStatus;
import com.maphaha.fnbkalahagame.model.Pit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GameEngineTest {

    @Autowired
    private GameEngine gameEngine;


    @Test
    public void shouldStartWithOwnPit() throws Exception {

        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(1));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(1));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(2) );
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(3));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(5));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(6));
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountByPitIndex(7));
        Assert.assertEquals(GameStatus.PLAYER1_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());

    }

    @Test
    public void shouldNotStartWithEmptyPit() throws Exception {
        //given
        Game game = new Game(6);
        Pit pit = game.getBoard().getPits().get(2);
        pit.setStoneCount(0);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(2));

    }


    public void shouldNotStartWithOpponentPit() throws Exception {
        //given
        Game game = new Game(6);
        game.setGameStatus(GameStatus.PLAYER2_TURN);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(2));
    }

    @Test
    public void shouldDistributeStoneFromPlayer2PitToPlayer1Pit() throws Exception {
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(12));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(12));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(13));
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountByPitIndex(14));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(1));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(2));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(3));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(GameStatus.PLAYER1_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }

    @Test
    public void shouldDistributeStoneFromPlayer1PitToPlayer2Pit() throws Exception {
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(4));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(5));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(6));
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountByPitIndex(7));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(8));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(9));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(10));
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }

    @Test
    public void shouldDistribute13Stone() throws Exception {
        //given
        Game game = new Game(6);
        game.getBoard().getPits().get(4).setStoneCount(13);
        game.getBoard().getPits().get(10).setStoneCount(10);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(4));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(4));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(5));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(6));
        Assert.assertEquals(Integer.valueOf(13), game.getBoard().getStoneCountByPitIndex(7));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(8));
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountByPitIndex(9));
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(10));
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(13), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }

    @Test
    public void shouldIncreaseHouseStoneOnOwnEmptyPit() throws Exception {
        //given
        Game game = new Game(6);
        Pit pit1 = game.getBoard().getPitByPitIndex(1);
        pit1.setStoneCount(2);

        Pit pit2 = game.getBoard().getPitByPitIndex(3);
        pit2.setStoneCount(0);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(1));

        //then
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(1));
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(3) );
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountByPitIndex(11));
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
        Assert.assertEquals(Integer.valueOf(7), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        Assert.assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }


    @Test
    public void shouldChangeGameToPlayerTurn1() throws Exception {
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(1));

        //then
        Assert.assertEquals(GameStatus.PLAYER1_TURN, game.getGameStatus());
    }


    @Test
    public void shouldChangeGameToPlayerTurn2() throws Exception {
        //given
        Game game = new Game(6);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(2));

        //then
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
    }


    @Test
    public void shouldChangeGameToPlayerTurn2Again() throws Exception {

        //given
        Game game = new Game(6);

        //when
        Pit pit = game.getBoard().getPits().get(8);
        pit.setStoneCount(6);
        gameEngine.play(game, game.getBoard().getPitByPitIndex(8));

        //then
        Assert.assertEquals(GameStatus.PLAYER2_TURN, game.getGameStatus());
    }


    @Test
    public void shouldGameOver() throws Exception {

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(6).setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(6));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), game.getPlayer1());
    }

    @Test
    public void shouldPlayer1Win() throws Exception {

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        Pit lastPit = game.getBoard().getPits().get(6);
        lastPit.setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(6));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), game.getPlayer1());
    }

    @Test
    public void shouldPlayer2Win() throws Exception {

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(13).setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(13));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), game.getPlayer2());
    }

    @Test
    public void shouldDraw() throws Exception {

        //given
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(6).setStoneCount(1);
        game.getBoard().getPits().get(14).setStoneCount(1);

        //when
        gameEngine.play(game, game.getBoard().getPitByPitIndex(6));

        //then
        Assert.assertEquals(GameStatus.FINISHED, game.getGameStatus());
        Assert.assertEquals(game.getWinner(), null);
    }

}


