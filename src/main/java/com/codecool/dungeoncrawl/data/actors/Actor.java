package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private boolean hasKey = false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if ((nextCell.getType() == CellType.KEY || nextCell.getType() == CellType.SWORD || nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.OPENEDDOOR) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if ((nextCell.getType() == CellType.CLOSEDDOOR && cell.getActor().isHasKey())) {
            nextCell.setType(CellType.OPENEDDOOR);
        }
    }

    public int getHealth() {
        return health;
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
