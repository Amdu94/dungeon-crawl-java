package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Potion extends Item{
    public Potion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "potion";
    }

    @Override
    public String toString() {
        return "Potion";
    }
}
