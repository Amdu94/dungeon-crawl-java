package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        this.setHealth(10);
        this.setStrength(2);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
