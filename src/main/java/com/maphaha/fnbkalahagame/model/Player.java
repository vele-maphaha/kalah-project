package com.maphaha.fnbkalahagame.model;


import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player {

    public static final Integer PLAYER1_INDEX = 1;
    public static final Integer PLAYER2_INDEX = 2;


    private Integer playerIndex;


    private String name;
}
