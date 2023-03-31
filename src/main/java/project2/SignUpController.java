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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class SignUpController extends Main implements Initializable { //extends User
    @FXML
    private AnchorPane parentPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton others;
    @FXML
    private TextField emailField;
    @FXML
    private Button login_;
    @FXML
    private Button signup_;
    @FXML
    private ImageView progress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signup_.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    signUpAction(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        login_.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loginAction(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    @FXML
    public void signUpAction(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);
        others.setToggleGroup(gender);
        if (username.isEmpty() || password.isEmpty() || gender.getSelectedToggle() == null || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign up Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
            return;
        }
        if (username.length() > 9 || password.length() > 9 || email.length() > 9) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign up Error");
            alert.setHeaderText(null);
            alert.setContentText("Username, password and email must be limited to 9 characters");
            alert.showAndWait();
            return;
        }
        try {
            if (checkData(username, password, email) == true) {
                Alert exist = new Alert(Alert.AlertType.ERROR);
                exist.setTitle("Sign Up Error");
                exist.setHeaderText(null);
                exist.setContentText("This username exists");
                exist.showAndWait();
            } else if (checkData(username, password, email) == false) {
                addData(username, password, gender, email);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sign Up Successful");
                alert.setHeaderText(null);
                alert.setContentText("You Signed Up Successfully!!!");
                alert.showAndWait();
                System.out.println("You Signed Up Successfully!!!");

                Parent root;
                try {
                    root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\login.fxml").toUri().toURL());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Login");
                stage.setScene(new Scene(root, 700, 400));
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void loginAction(ActionEvent event) throws IOException, InterruptedException {
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
                root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\login.fxml").toUri().toURL());
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
}

