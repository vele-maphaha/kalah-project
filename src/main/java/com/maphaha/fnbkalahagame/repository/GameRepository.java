package com.maphaha.fnbkalahagame.repository;

import com.maphaha.fnbkalahagame.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class GameRepository {

    private static final Map<String, Game> gameMap = new ConcurrentHashMap<>();

    public Game create(Integer initialPitStoneCount){
        String id = UUID.randomUUID().toString();
        Game game = new Game(initialPitStoneCount);
        game.setId(id);
        gameMap.put(id, game);
        return gameMap.get(id);
    }

    public Game findById(String id) throws Exception {
        Game game = gameMap.get(id);
        if(game == null){
            throw new Exception("Game with such id not found");
        }
        return game;
    }

    @Scheduled(fixedRate = 300000)
    public void deleteOldGame(){
        log.debug("clearing the old games");
        log.debug("size of the map {}", gameMap.size());

        for(Map.Entry<String, Game> entry: gameMap.entrySet()){
            long diff = (System.currentTimeMillis() - entry.getValue().getUpdateAt());
            long diffMinutes = diff / (60 * 1000) % 60;
            if( diffMinutes > 60){
                gameMap.remove(entry.getKey());
            }
        }
    }
}

