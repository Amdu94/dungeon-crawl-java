package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int strength;
    private boolean hasKey = false;





    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        if ((nextCell.getType() == CellType.KEY || nextCell.getType() == CellType.SWORD || nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.OPENEDDOOR) && nextCell.getActor() == null) {
//            cell.setActor(null);
//            nextCell.setActor(this);
//            cell = nextCell;
//        } else if ((nextCell.getType() == CellType.CLOSEDDOOR && cell.getActor().isHasKey())) {
//            openDoor(nextCell);
//        } else if (nextCell.getActor() != null) {
//            fightMonster(nextCell);
//        }
//    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);

        //open door
        if (nextCell.getType().equals(CellType.CLOSEDDOOR) && isHasKey()) {
            cell.setActor(null);
            openDoor(nextCell);
            nextCell.setActor(this);
            cell = nextCell;
        }

        // player move
        if (cell.isPlayer() && nextCell.isAvailable() && !nextCell.isEnemy()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            // enemy move
        } else if (nextCell.isAvailable() && !nextCell.isEnemy() && !nextCell.isPlayer())   {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void fight(int dx, int dy){
        Cell nextCell = cell.getNeighbor(dx, dy);
        // enemy attack
        if (nextCell.isPlayer() && cell.isEnemy()) attack(nextCell.getActor(), nextCell);
        // player attack
        if (nextCell.isEnemy() && cell.isPlayer()) attack(nextCell.getActor(), nextCell);
    }

    public void attack(Actor actor, Cell cell) {
        while (true) {
            actor.setHealth(actor.getHealth() - this.strength);
            if (actor.getHealth() < 0 || this.health < 0) break;
            this.health = this.health - actor.getStrength();
            if (actor.getHealth() < 0 || this.health < 0) break;
        }
        if (actor.getHealth() <= 0)  {
            cell.setActor(null);
        } else if (this.health <= 0 ){
            cell.setActor(null);
        }
    }


    private void openDoor( Cell cell){
        cell.setType(CellType.OPENEDDOOR);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public boolean isHasKey() {
        return hasKey;
    }


}
