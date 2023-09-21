package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;

    private MainStage mainStage;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;
    private Timer skeletonTimer;


    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
        this.skeletonTimer = new Timer();

    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        scheduleSkeletonMoves();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic.getMap());
        }
        logic.getMap().getPlayer().pickUpItem();
        refresh();
    }

    public void refresh() {

        if(logic.getMap().getPlayer().getHealth() <= 0){
            logic.loseGame();
        }
        else if (logic.getMap().getCell(logic.getMap().getPlayer().getX(), logic.getMap().getPlayer().getY()).isOpenDoor()){
            logic.wonGame();
        }
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.hasItem()) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        mainStage.setHealthLabelText(logic.getPlayerHealth());
        mainStage.setStrengthLabelText(logic.getPlayerStrength());
        mainStage.setInventoryLabelText(logic.getInventory());
    }

    private void skeletonMove(String direction, Cell cell) {
        int dx = 0, dy = 0;

        switch (direction) {
            case "UP":
                dy = -1;
                break;
            case "DOWN":
                dy = 1;
                break;
            case "LEFT":
                dx = -1;
                break;
            case "RIGHT":
                dx = 1;
                break;
        }

        logic.getCell(cell.getX(), cell.getY()).getActor().move(dx, dy);

    }


    private void skeletonMove() {
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        Random random = new Random();

        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() instanceof Skeleton) {
                    String direction = directions[random.nextInt(4)];
                    skeletonMove(direction, cell);
                }
            }
        }
    }


    private void scheduleSkeletonMoves() {
        skeletonTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                skeletonMove();
                refresh();
            }
        }, 1000, 1000);
    }


}
