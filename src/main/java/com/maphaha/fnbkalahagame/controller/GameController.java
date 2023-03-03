package com.maphaha.fnbkalahagame.controller;


import com.maphaha.fnbkalahagame.model.Board;
import com.maphaha.fnbkalahagame.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/kalah")
public class GameController {
    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping(value = "/games")
    public ResponseEntity initBoard(@RequestParam(name = "stone", defaultValue = "6", required = false) Integer numberOfStone){
        log.debug("initializing kalah game");
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.initGame(numberOfStone));
    }


    @PutMapping("/games/{gameId}/pits/{pitIndex}")
    public ResponseEntity play(@PathVariable String gameId, @PathVariable Integer pitIndex) throws Exception {
        log.debug("From game {}, player {} is moving stone from pit {}",gameId, pitIndex);

        if(pitIndex > Board.PIT_END_INDEX || pitIndex < Board.PIT_START_INDEX){
            throw new Exception("Incorrect pit index");
        }

        if(pitIndex.equals(Board.PLAYER1_HOUSE) || pitIndex.equals(Board.PLAYER2_HOUSE)){
            throw new Exception("House stone is not allow to distribute");
        }

        return ResponseEntity.ok().body(gameService.play(gameId, pitIndex));
    }

}
