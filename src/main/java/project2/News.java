package project2;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class News implements Initializable {
    @FXML
    private Button technology;
    @FXML
    private Button sports;
    @FXML
    private Button business;
    @FXML
    private Button economy;
    @FXML
    private Button logout;
    @FXML
    private ImageView progress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        technology.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                technologyAction(event);
            }
        });
        sports.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sportsAction(event);
            }
        });
        business.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                businessAction(event);
            }
        });
        economy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                economyAction(event);
            }
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Logout");
                alert.setHeaderText(null);
                alert.setContentText("              You logged out          ");
                alert.show();

                FadeTransition fadeOutAlert = new FadeTransition(Duration.seconds(0.5), alert.getDialogPane());
                fadeOutAlert.setFromValue(1);
                fadeOutAlert.setToValue(0);
                fadeOutAlert.play();
                fadeOutAlert.setOnFinished(e -> alert.setResult(ButtonType.CLOSE));

                Platform.runLater(() -> {
                    StackPane roots = new StackPane();
                    Image loadingImage = new Image("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\icon1.png");
                    progress = new ImageView(loadingImage);
                    Rotate rotate = new Rotate(0, progress.getFitWidth(), progress.getFitHeight());
                    progress.getTransforms().add(rotate);
                    Label loadingLabel = new Label("Loading...");
                    roots.getChildren().addAll(progress, loadingLabel);
                    Scene scene = new Scene(roots, 700, 400);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Platform.runLater(() -> {
                        Parent root;
                        try {
                            System.out.println("You Logged Out Successfully!!!");
                            root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\login.fxml").toUri().toURL());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        primaryStage.setTitle("Login");
                        primaryStage.setScene(new Scene(root, 700, 400));
                        primaryStage.show();
                    });

                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), roots);
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.play();
                });
            }
        });
    }

    @FXML
    public void technologyAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\technology.fxml").toUri().toURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Technology News");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    @FXML
    public void sportsAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\sports.fxml").toUri().toURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Sports News");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    @FXML
    public void businessAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\business.fxml").toUri().toURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Business News");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    @FXML
    public void economyAction(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\economy.fxml").toUri().toURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Economy News");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }


}
