package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private final Label healthTextLabel;
    private final Label healthValueLabel;
    private final Label strengthTextLabel;
    private final Label strengthValueLabel;
    private final Label inventoryTextLabel;
    private final Label inventoryValueLabel;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        strengthTextLabel = new Label("Strength: ");
        strengthValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryValueLabel = new Label();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(strengthTextLabel, 0, 1);
        ui.add(strengthValueLabel,1, 1);
        ui.add(inventoryTextLabel, 0, 2);
        ui.add(inventoryValueLabel, 1, 2);


        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setStrengthValue(String text) {
        strengthValueLabel.setText(text);
    }
    public void setInventoryValue(String text) {inventoryValueLabel.setText(text); }

}
