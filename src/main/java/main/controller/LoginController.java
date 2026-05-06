package main.controller;

import java.io.IOException;
import main.App;
import main.services.userService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {

    @FXML private Label modeLabel;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button primaryBtn;
    @FXML private Button toggleBtn;

    private boolean isLoginMode = true;
    private final userService userService = new userService();

    @FXML
    private void handlePrimary() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Basic validation
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields!");
            return;
        }

        if (isLoginMode) {
            // LOGIN MODE
            if (userService.login(username, password)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");

                clearFields();

                try {
                    // MAIN APPLICATION SCREEN
                    App.setRoot("main");
                } catch (IOException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to load main page.");
                    e.printStackTrace();
                }

            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password!");
            }

        } else {
            // REGISTER MODE
            if (userService.register(username, password)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Account created! You can now login.");
                handleToggle(); // switch back to login mode
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Username already exists!");
            }
        }
    }

    @FXML
    private void handleToggle() {
        isLoginMode = !isLoginMode;

        if (isLoginMode) {
            modeLabel.setText("Sign In");
            primaryBtn.setText("Login");
            toggleBtn.setText("Register");
        } else {
            modeLabel.setText("Sign Up");
            primaryBtn.setText("Register");
            toggleBtn.setText("Login");
        }

        clearFields();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }

    @FXML
    public void initialize() {
        userService.ensureDatabaseExists();
    }
}