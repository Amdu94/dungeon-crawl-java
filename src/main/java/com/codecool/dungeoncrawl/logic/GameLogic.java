package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap("map.txt");
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public void wonGame() {
        this.map = MapLoader.loadMap("won.txt");
        setup();
    }
    public void loseGame() {
        this.map = MapLoader.loadMap("lose.txt");
        setup();
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public String getPlayerStrength() {
        return Integer.toString(map.getPlayer().getStrength());
    }

    public String getInventory() {return map.getPlayer().getInventory().toString();};


    public GameMap getMap() {
        return map;
    }
}
