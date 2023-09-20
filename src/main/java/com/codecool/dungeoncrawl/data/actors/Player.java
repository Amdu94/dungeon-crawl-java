package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;

public class Player extends Actor {
    private int dx;
    private int dy;
    private ArrayList<Item> inventory = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
        this.dx = 0;
        this.dy = 0;
        this.setStrength(5);
        this.setHealth(50);
    }

    public String getTileName() {
        return "player";
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void pickUpItem() {
        Cell currentCell = getCell();
        Cell nextCell = currentCell.getNeighbor(dx, dy);

        if (nextCell != null && nextCell.hasItem()) {
            Item item = nextCell.getItem();
            this.inventory.add(item);
            if (item.getTileName() == CellType.CAN.getTileName()){
                setHasCan(true);
            } else if (item.getTileName() == CellType.KEY.getTileName()) {
                setHasKey(true);
            }
            nextCell.removeItem();
            System.out.println("Picked up: " + item);
            System.out.println(inventory);
        }
    }

}
