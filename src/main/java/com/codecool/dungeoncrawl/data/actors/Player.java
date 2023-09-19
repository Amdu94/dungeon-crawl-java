package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        this.setStrength(5);
        this.setHealth(50);
    }

    public String getTileName() {
        return "player";
    }
}
