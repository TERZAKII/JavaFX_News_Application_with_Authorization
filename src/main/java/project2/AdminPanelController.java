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
import javafx.scene.control.*;
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

public class AdminPanelController implements Initializable {
    @FXML
    private TextField adminEmailField;
    @FXML
    private PasswordField adminPasswordField;
    @FXML
    private Button login;
    @FXML
    private ImageView progress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loginAction(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        String email = adminEmailField.getText();
        String password = adminPasswordField.getText();

        if (email.equals("190103251@stu.sdu.edu.kz") && password.equals("iamadmin")) {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Login Admin");
            alert.setHeaderText(null);
            alert.setContentText("              Email and Password matched               ");
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
                        System.out.println("You Logged In Successfully!!!");
                        root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\news.fxml").toUri().toURL());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.setTitle("News page");
                    primaryStage.setScene(new Scene(root, 700, 400));
                    primaryStage.show();
                });

                FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), roots);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.play();
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect email or password");
            alert.showAndWait();
        }
    }

}
