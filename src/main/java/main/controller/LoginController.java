package main.controller;

import java.io.IOException;
import main.services.UserService;
import main.App;
import main.SessionContext;
import main.model.UserModel;
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
    private final UserService userService = new UserService();

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
            UserModel user = userService.login(username, password);
            if (user != null) {
                // Populate session context
                SessionContext.setCurrentUser(user);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful! Welcome to Shapora!");
                clearFields();
                try {
                    App.setRoot("store");
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
                try {
                    App.setRoot("login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handleToggle();
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
