package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private final String name;

    public Item(Cell cell, String name) {
        this.cell = cell;
        this.name = name;
    }
}
