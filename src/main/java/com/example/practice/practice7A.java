package com.example.practice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class practice7A extends Application {

    @Override
    public void start(Stage stage) {
        Label drinkLabel = new Label("Drink:");
        ComboBox<String> drinkComboBox = new ComboBox<>();
        drinkComboBox.getItems().addAll("Latte", "Americano", "Cappuccino", "Black Tea", "Green Tea");


        Label milkLabel = new Label("Milk option:");
        ComboBox<String> milkComboBox = new ComboBox<>();
        milkComboBox.getItems().addAll("Whole Milk", "Soy Milk", "Almond Milk", "No Milk");

        Label addonsLabel = new Label("Add:");
        CheckBox sugarCheckBox = new CheckBox("Sugar");
        CheckBox extraHotCheckBox = new CheckBox("Extra Hot");
        CheckBox extraMilkCheckBox = new CheckBox("Extra Milk");
        CheckBox strawCheckBox = new CheckBox("Straw");
        CheckBox napkinsCheckBox = new CheckBox("Napkins");

        TextField drinkTextField = new TextField();
        TextField milkTextField = new TextField();
        TextField addTextField = new TextField();
        drinkTextField.setEditable(false);
        milkTextField.setEditable(false);
        addTextField.setEditable(false);

        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setOnAction(event -> {
            StringBuilder drink = new StringBuilder();
            StringBuilder milk = new StringBuilder();
            StringBuilder add = new StringBuilder();
            if (!drinkComboBox.getValue().equals("")) {
                drink.append("Drink: ");
            }
            drink.append(drinkComboBox.getValue()).append("\n");

            if (!milkComboBox.getValue().equals("")) {
                milk.append("Milk: ");
            }
            milk.append(milkComboBox.getValue()).append("\n");

            int count = 0;
            if (sugarCheckBox.isSelected()) {
                count++;
            }
            if (extraHotCheckBox.isSelected()) {
                count++;
            }
            if (extraMilkCheckBox.isSelected()) {
                count++;
            }
            if (strawCheckBox.isSelected()) {
                count++;
            }
            if (napkinsCheckBox.isSelected()) {
                count++;
            }

            if (count == 1) {
                add.append("Add: ");
            } else if (count > 1) {
                add.append("Adds: ");
            }

            if (sugarCheckBox.isSelected()) {
                add.append("Sugar \n");
            }
            if (extraHotCheckBox.isSelected()) {
                add.append("Extra Hot \n");
            }
            if (extraMilkCheckBox.isSelected()) {
                add.append("Extra Milk \n");
            }
            if (strawCheckBox.isSelected()) {
                add.append("Straw \n");
            }
            if (napkinsCheckBox.isSelected()) {
                add.append("Napkins \n");
            }

            drinkTextField.setText(drink.toString());
            milkTextField.setText(milk.toString());
            addTextField.setText(add.toString());
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(drinkLabel, 1, 0);
        gridPane.add(drinkComboBox, 2, 0);
        gridPane.add(milkLabel, 7, 0);
        gridPane.add(milkComboBox, 8, 0);
        gridPane.add(addonsLabel, 0, 2);
        gridPane.add(sugarCheckBox, 1, 2);
        gridPane.add(extraHotCheckBox, 1, 3);
        gridPane.add(extraMilkCheckBox, 1, 4);
        gridPane.add(strawCheckBox, 1, 5);
        gridPane.add(napkinsCheckBox, 1, 6);
        gridPane.add(placeOrderButton, 0, 9, 2, 2);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(gridPane, drinkTextField, milkTextField, addTextField);
        Scene scene = new Scene(vBox, 500, 400);
        stage.setScene(scene);
        stage.setTitle("My Coffee Shop");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

