package main.controller;

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

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields!");
            return;
        }

        if (isLoginMode) {
            // Login Mode
            if (userService.login(username, password)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful! Welcome to ShopFX!");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password!");
            }
        } else {
            // Register Mode
            if (userService.register(username, password)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully! You can now login.");
                handleToggle();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Username already exists! Please choose a different one.");
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
