package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Cans extends Item {
    public Cans(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "can";
    }

    @Override
    public String toString() {
        return "Can";
    }
}
