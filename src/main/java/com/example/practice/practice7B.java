package com.example.practice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class practice7B extends Application {

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

        ImageView drinkImageView = new ImageView();
        Label drinkDescriptionLabel = new Label();

        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setOnAction(event -> {
            StringBuilder drink = new StringBuilder();
            StringBuilder milk = new StringBuilder();
            StringBuilder add = new StringBuilder();

            String selectedDrink = drinkComboBox.getValue();
            switch (selectedDrink) {
                case "Latte":
                    drink.append("Latte");
                    drinkImageView.setImage(new Image("D:\\JavaProjects\\JavaFX\\practice\\src\\images\\latte.jpg"));
                    drinkDescriptionLabel.setText("A latte is a coffee drink made with espresso and steamed milk.");
                    break;
                case "Americano":
                    drink.append("Americano");
                    drinkImageView.setImage(new Image("D:\\JavaProjects\\JavaFX\\practice\\src\\images\\americano.jpg"));
                    drinkDescriptionLabel.setText("An Americano is a coffee drink made with espresso and hot water.");
                    break;
                case "Cappuccino":
                    drink.append("Cappuccino");
                    drinkImageView.setImage(new Image("D:\\JavaProjects\\JavaFX\\practice\\src\\images\\Cappuccino.jpg"));
                    drinkDescriptionLabel.setText("A cappuccino is a coffee drink made with espresso and milk foam.");
                    break;
                case "Black Tea":
                    drink.append("Black Tea");
                    drinkImageView.setImage(new Image("D:\\JavaProjects\\JavaFX\\practice\\src\\images\\Black-Tea.jpg"));
                    drinkDescriptionLabel.setText("Black tea is a type of tea that is more oxidized than green, oolong, and white teas.");
                    break;
                case "Green Tea":
                    drink.append("Green Tea");
                    drinkImageView.setImage(new Image("D:\\JavaProjects\\JavaFX\\practice\\src\\images\\GreenTea.jpg"));
                    drinkDescriptionLabel.setText("Green tea is a type of tea that is made from Camellia sinensis leaves and buds that have not undergone the same withering and oxidation process used to make oolong teas and black teas.");
                    break;
                default:
                    drinkImageView.setImage(null);
                    drinkDescriptionLabel.setText("");
            }

            if (!selectedDrink.equals("")) {
                drink.append("\n");
            }

            if (!milkComboBox.getValue().equals("")) {
                milk.append("\n");
            }
            if (!add.toString().equals("")) {
                add.append("\n");
            }


        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(drinkLabel, 1, 0);
        gridPane.add(drinkComboBox, 2, 0);
        gridPane.add(milkLabel, 5, 0);
        gridPane.add(milkComboBox, 6, 0);
        gridPane.add(addonsLabel, 0, 2);
        gridPane.add(sugarCheckBox, 1, 2);
        gridPane.add(extraHotCheckBox, 1, 3);
        gridPane.add(extraMilkCheckBox, 1, 4);
        gridPane.add(strawCheckBox, 1, 5);
        gridPane.add(napkinsCheckBox, 1, 6);
        gridPane.add(placeOrderButton, 0, 9, 2, 2);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(gridPane);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10));

        // add drink image and description
        HBox drinkInfoBox = new HBox(20);
        drinkInfoBox.setAlignment(Pos.CENTER);
        drinkImageView.setFitHeight(200);
        drinkImageView.setFitWidth(200);
        drinkInfoBox.getChildren().addAll(drinkImageView, drinkDescriptionLabel);
        vBox.getChildren().add(drinkInfoBox);

        Scene scene = new Scene(vBox, 500, 500);
        stage.setScene(scene);
        stage.setTitle("My Coffee Shop");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

