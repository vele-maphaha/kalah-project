package com.maphaha.fnbkalahagame.repository;


import com.maphaha.fnbkalahagame.model.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository  gameRepository;

    @Test
    public void shouldCreateGame() throws Exception {
        //given
        Game game1 = gameRepository.create(6);

        //when
        Game game = gameRepository.findById(game1.getId());

        //assert
        Assert.assertNotNull(game);
        Assert.assertEquals(game1, game1);
    }
}
