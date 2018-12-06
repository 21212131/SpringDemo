package com.zzc.demo.dao;

import com.zzc.demo.entity.Game;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface GameDao {

    @Transactional
    List<Game> queryGame();
    @Transactional
    Game findById(int id);
    @Transactional
    void addGame(Game newGame);
}
