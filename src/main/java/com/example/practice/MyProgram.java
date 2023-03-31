package com.example.practice;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyProgram extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle();
        circle.setCenterX(150);
        circle.setCenterY(150);
        circle.setRadius(50);
        circle.setFill(Color.CORAL);

        TextArea textArea = new TextArea();

        Rectangle rectangle = new Rectangle(100, 100, Color.TAN);

        TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(100);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), rectangle);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(50);
        rotateTransition.setAutoReverse(false);

        Ellipse ellipse = new Ellipse(50, 25);
        ellipse.setFill(Color.BLUEVIOLET);

        HBox hBox = new HBox(circle, rectangle, textField, ellipse);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(20));

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), ellipse);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1.0);
        fadeTransition.setAutoReverse(true);

        VBox vBox = new VBox(hBox, textArea);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        EventHandler<MouseEvent> mouseEventEventHandlerForCircle = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                textArea.appendText("Mouse event handler has been called for Circle. Location = [" + mouseEvent.getX() + "," + mouseEvent.getX() + "]\n");
            }
        };
        circle.setOnMouseClicked(mouseEventEventHandlerForCircle);

        EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                rotateTransition.play();
                textArea.appendText("Key event handler has been called\n");
            }
        };
        textField.setOnKeyTyped(keyEventHandler);

        EventHandler<MouseEvent> mouseEventEventHandlerForEllipse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                textArea.appendText("Mouse event handler has been called for Ellipse. Location = [" + mouseEvent.getX() + "," + mouseEvent.getX() + "]\n");
                fadeTransition.play();
            }
        };
        ellipse.setOnMouseClicked(mouseEventEventHandlerForEllipse);

        Scene scene = new Scene(vBox, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Program");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
