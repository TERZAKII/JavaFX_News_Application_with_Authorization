package project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main extends Application {
    public static String DATA_FOLDER = "D:\\JavaProjects\\JavaFX\\practice\\src\\main\\java\\project2";
    public static String FILE = "users.txt";

    @Override
    public void start(Stage primaryStage) throws IOException {
        createFolder();
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = FXMLLoader.load(Paths.get("D:\\JavaProjects\\JavaFX\\practice\\src\\main\\resources\\project2\\login.fxml").toUri().toURL());
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

        System.out.println(countUsername());
        System.out.println(CountLines());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void createFolder() throws FileNotFoundException, IOException {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void readFile() throws FileNotFoundException, IOException {
        File file = new File(DATA_FOLDER + File.separator + FILE);

        try {
            if (file.exists()) {
                throw new FileNotFoundException("File exists");
            } else {
                file.createNewFile();
                System.out.println("File created");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }

    public void addData(String username, String password, ToggleGroup gender, String email) throws FileNotFoundException, IOException {
        try (
                RandomAccessFile randomAccess = new RandomAccessFile(DATA_FOLDER + File.separator + FILE, "rw")) {
            randomAccess.seek(randomAccess.length());
            randomAccess.writeBytes("Username: " + username + "\nPassword: " + password + "\nGender: " + gender.getSelectedToggle() + "\nEmail: " + email + "\n" + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean checkData(String username, String password, String email) throws FileNotFoundException, IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(DATA_FOLDER + File.separator + FILE);
                BufferedReader reader = new BufferedReader(new FileReader(fileInputStream.getFD()))
        ) {
            String read;
            int count = 0;
            ArrayList<String> arrayList = new ArrayList<>();
            String line = CountLines();
            String[] lines = line.split(": ");
            int numofline = Integer.parseInt(lines[1]);
            String Username = countUsername();
            String[] Usernames = Username.split(": ");
            int numofusername = Integer.parseInt(Usernames[1]);
            String[] parts = new String[numofline];
            String[] parts1;
            String[] parts2;
            while ((read = reader.readLine()) != null) {
                arrayList.add(read);
                count++;
            }

            int i = 0;
            while (i < numofline - 1) {
                parts1 = arrayList.get(i).split(": ");
                parts2 = arrayList.get(i + 1).split(": ");
                if (parts1[0].equals("Username")) {
                    String fileUsername = parts1[1];
                    String filePassword = parts2[1];
                    if (fileUsername.equals(username)) {
                        if (filePassword.equals(password)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("File not found: " + e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public String countUsername() throws FileNotFoundException, IOException {
        int numOfUsernames = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FOLDER + File.separator + FILE))) {
            String read;
            String[] parts;
            while ((read = reader.readLine()) != null) {
                parts = read.split(": ");
                if (parts[0].equals("Username")) {
                    numOfUsernames++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "Number of Users: " + numOfUsernames;
    }

    public String CountLines() throws FileNotFoundException, IOException {
        int numOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FOLDER + File.separator + FILE))) {
            while ((reader.readLine()) != null) {
                numOfLines++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "Number of lines: " + numOfLines;
    }

    public boolean logic(String username, String password) throws FileNotFoundException, IOException {
        try (FileInputStream fileInputStream = new FileInputStream(DATA_FOLDER + File.separator + FILE);
             BufferedReader reader = new BufferedReader(new FileReader(fileInputStream.getFD()))) {
            String read;
            String line = CountLines();
            String[] lines = line.split(": ");
            int numofline = Integer.parseInt(lines[1]);
            String[] parts = new String[numofline];

            int i = 0;
            while ((read = reader.readLine()) != null) {
                parts[i] = (read);
                i++;
            }

            String[] div1;
            String[] div2;
            for (int j = 0; j < parts.length; j++) {
                div1 = parts[j].split(": ");
                div2 = parts[j + 1].split(": ");
                if (div1[0].equals("Username")) {
                    if (div1[1].equals(username)) {
                        if (div2[0].equals("Password")) {
                            if (div2[1].equals(password)) {
                                System.out.println("Username and Password matched");
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("File not found: " + e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

}

