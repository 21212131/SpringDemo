package com.zzc.demo.entity;

public class Game {
    int game_id;
    String game_name;
    String game_type;
    public Game(int id,String name,String type){
        this.game_id = id;
        this.game_name = name;
        this.game_type = type;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }
}
