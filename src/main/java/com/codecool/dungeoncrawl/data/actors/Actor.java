package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int strength = 6;
    private boolean hasKey = true;



    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        if((nextCell.getType() == CellType.KEY || nextCell.getType() == CellType.SWORD || nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.OPENEDDOOR) && nextCell.getActor() == null) {
//            cell.setActor(null);
//            nextCell.setActor(this);
//            cell = nextCell;
//        }else if((nextCell.getType() == CellType.CLOSEDDOOR && cell.getActor().isHasKey())){
//            nextCell.setType(CellType.OPENEDDOOR);
//        }else if(nextCell.getActor() != null){
//            if (cell.getActor() instanceof Player) {
//                this.fightMonster(nextCell.getActor());
//                System.out.println(this.getHealth());
//            }
//        }
//    }

        public void move(int dx, int dy) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if ((nextCell.getType() == CellType.KEY || nextCell.getType() == CellType.SWORD || nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.OPENEDDOOR) && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            } else if ((nextCell.getType() == CellType.CLOSEDDOOR && cell.getActor().isHasKey())) {
                typeOfCell(nextCell, dx, dy);
            } else if (nextCell.getActor() != null) {
                fightMonster(nextCell);
            }
        }

    private void typeOfCell( Cell cell, int dx, int dy){
            cell.setType(CellType.OPENEDDOOR);
    }

    private void healthAfterFight(Actor actor) {
        actor.setHealth(actor.getHealth() - this.getStrength());
        if (actor.getHealth() > 0) {
            this.setHealth(this.getHealth() - actor.getStrength());
        } else {
            actor.getCell().setActor(null);
        }
    }

    private void fightMonster(Cell nextCell){
        if (cell.getActor() instanceof Player) {
                this.healthAfterFight(nextCell.getActor());
                System.out.println(this.getHealth());
            }
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
