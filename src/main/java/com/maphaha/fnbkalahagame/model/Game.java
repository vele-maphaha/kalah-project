package com.maphaha.fnbkalahagame.model;

import lombok.Data;

@Data
public class Game {



    private String id;
    private Board board;
    private Player player1;
    private Player player2;
    private Player winner;
    private GameStatus gameStatus;
    private Long updateAt;
    public Game(Integer initialStoneOnPit) {
        this.player1 = new Player(Player.PLAYER1_INDEX, "player1");
        this.player2 = new Player(Player.PLAYER2_INDEX, "player2");
        this.board = new Board(initialStoneOnPit, player1, player2);
        this.gameStatus = GameStatus.INIT;
        this.updateAt = System.currentTimeMillis();
    }

    public void updateTime(){
        this.setUpdateAt(System.currentTimeMillis());
    }
}
