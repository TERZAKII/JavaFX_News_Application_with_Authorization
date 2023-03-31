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

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private Button login;
    @FXML
    private ImageView progress;
    public static String DATA_FOLDER = "D:\\JavaProjects\\JavaFX\\practice\\src\\main\\java\\project2";
    public static String FILE = "users.txt";
    public boolean trueResetPassword = false;

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
    public void loginAction(ActionEvent event) throws FileNotFoundException, IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Reset Password Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
            return;
        }
        if (username.length() > 9 || password.length() > 9) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Reset Password Error");
            alert.setHeaderText(null);
            alert.setContentText("Username, password must be limited to 9 characters");
            alert.showAndWait();
            return;
        }
        if (!password.equals(repeatPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Reset Password Error");
            alert.setHeaderText(null);
            alert.setContentText("Password and repeat Password are not same");
            alert.showAndWait();
            return;
        }

        updatePassword(username, password);
        if (trueResetPassword) {
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
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> {
                Parent root;
                try {
                    System.out.println("Your password changed Successfully");
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
        }

    }

    private void updatePassword(String username, String newPassword) {
        File inputFile = new File(DATA_FOLDER + File.separator + FILE);
        File tempFile = new File(DATA_FOLDER + File.separator + "user.txt");
        int userHave = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean updatePassword = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts[0].equals("Username") && parts[1].equals(username)) {
                    userHave++;
                    updatePassword = true;
                } else if (updatePassword && parts[0].equals("Password")) {
                    line = "Password: " + newPassword;
                    updatePassword = false;
                }
                writer.write(line + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete original file.");
        } else if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file.");
        } else if (userHave == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect User");
            alert.setHeaderText(null);
            alert.setContentText("Username doesn't exist");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Password");
            alert.setHeaderText(null);
            alert.setContentText("Password successfully changed");
            alert.showAndWait();
            trueResetPassword = true;
            return;
        }
    }

}
