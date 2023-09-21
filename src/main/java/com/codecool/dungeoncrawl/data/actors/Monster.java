package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Monster extends Actor{

    public Monster(Cell cell) {
        super(cell);
        this.setStrength(20);
        this.setHealth(100);
    }
    @Override
    public String getTileName() {
        return "monster";
    }
}
