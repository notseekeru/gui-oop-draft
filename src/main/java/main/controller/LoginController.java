package main.controller;

import java.io.IOException;
import main.services.userService;
import main.App;
import main.SessionContext;
import main.model.userModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

// Added imports for scene switching and FXML loading
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML private Label modeLabel;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button primaryBtn;
    @FXML private Button toggleBtn;

    private boolean isLoginMode = true;
    private final userService userService = new userService();

    @FXML
    private void handlePrimary(ActionEvent event) { // Added ActionEvent so we can switch scenes
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Basic validation
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields!");
            return;
        }

        if (isLoginMode) {
            // Login Mode
            int userId = userService.login(username, password);
            if (userId != -1) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful! Welcome to ShopFX!");
                clearFields();
                try {
                    App.setRoot("dashboard");
                } catch (IOException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to load main page.");
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to load dashboard.");
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
}