package com.zzc.demo;

import com.zzc.demo.dao.GameDao;
import com.zzc.demo.entity.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameDaoTests {

    @Autowired
    private GameDao gameDao;

    @Test
    public void main(){
        try{
            testAdd();
//            testQueryGame();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void testAdd() throws Exception{
        Game newGame=  new Game(4,"WOW","BattleShip");
        gameDao.addGame(newGame);
    }


    public void testQueryGame() throws Exception {
        List<Game> gameList = gameDao.queryGame();
        System.out.println("查询出来的数据公有："+gameList.size() + "条");
        for(Game currGame:gameList){
            System.out.println(currGame.getGame_name());
        }
        Game currGame = gameDao.findById(1);
        System.out.println(currGame);

    }

}
