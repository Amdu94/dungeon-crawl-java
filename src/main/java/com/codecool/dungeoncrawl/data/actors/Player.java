package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

public class Player extends Actor {
    private int dx;
    private int dy;
    public Player(Cell cell) {
        super(cell);
        this.dx = 0;
        this.dy = 0;
    }

    public String getTileName() {
        return "player";
    }

    public void pickUpItem() {
        Cell currentCell = getCell();
        Cell nextCell = currentCell.getNeighbor(dx, dy);

        if (nextCell != null && nextCell.hasItem()) {
            Item item = nextCell.getItem();
            nextCell.removeItem();
            System.out.println(item);
        }
    }

}
