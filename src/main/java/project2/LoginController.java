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

public class LoginController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox rememberMeCheckbox;
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private ImageView progress;
    @FXML
    private Label lbl_username;
    @FXML
    private Label lbl_password;
    @FXML
    private Button forgotPassword;
    String email = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rememberMeCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Parent root;
                try {
                    root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\news.fxml").toUri().toURL());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = (Stage) rememberMeCheckbox.getScene().getWindow();
                stage.setTitle("News page");
                stage.setScene(new Scene(root, 700, 400));
                stage.show();
            }
        });
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
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    signUpAction(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        forgotPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    forgotPasswordAction(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    public void loginAction(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() && password.isEmpty()) {
            lbl_username.setText("Username is Empty");
            lbl_password.setText("Empty Password");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Form Error !");
            alert.setHeaderText("");
            alert.setContentText("Please, Enter your username and password !!!");
            alert.showAndWait();
            lbl_username.setText("");
            lbl_password.setText("");
            return;
        } else if (username.isEmpty() && !password.isEmpty()) {
            lbl_username.setText("Username is Empty");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Username Error !");
            alert.setHeaderText("");
            alert.setContentText("Please, Enter your username !!!");
            alert.showAndWait();
            lbl_username.setText("");
            return;
        } else if (!username.isEmpty() && password.isEmpty()) {
            lbl_password.setText("Empty Password");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Error !");
            alert.setHeaderText("");
            alert.setContentText("Please, Enter your password !!!");
            alert.showAndWait();
            lbl_password.setText("");
            return;
        }

        if (username.length() > 9 || password.length() > 9 || email.length() > 9) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Username, password and email must be limited to 9 characters");
            alert.showAndWait();
            return;
        }

        Main main = new Main();
        try {
            if (main.checkData(username, password, email) == true) {
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Login");
                alert.setHeaderText(null);
                alert.setContentText("              Username and Password matched               ");
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
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText(" The username/password incorrect");
                alert.showAndWait();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void signUpAction(ActionEvent event) throws IOException, InterruptedException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        Thread.sleep(100);
        Platform.runLater(() -> {
            Parent root;
            try {
                root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\signup.fxml").toUri().toURL());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 700, 400));
            primaryStage.show();
        });

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), roots);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.play();
    }

    @FXML
    public void forgotPasswordAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent root;
        try {
            root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\forgotPassword.fxml").toUri().toURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Reset Password Page");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

}

