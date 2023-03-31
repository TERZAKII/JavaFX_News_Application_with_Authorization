package com.example.practice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class drawEllipse extends Application {

    @Override
    public void start(Stage primaryStage) {
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(300);
        ellipse.setCenterY(150);
        ellipse.setRadiusX(80);
        ellipse.setRadiusY(20);
        ellipse.setFill(Color.LIGHTBLUE);
        ellipse.setStroke(Color.BLACK);

        Text text = new Text();
        text.setText("This is my ellipse");
        text.setX(245);
        text.setY(190);
        text.setFont(Font.font("Helvetica", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC, 15));
        text.setFill(Color.YELLOW);
        text.setStroke(Color.BLACK);
        text.setUnderline(true);

        Group root = new Group();
        root.getChildren().addAll(ellipse, text);

        Scene scene = new Scene(root, 600, 300);

        primaryStage.setTitle("My JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
