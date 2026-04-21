package main.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.App;

public class LoginController {

    @FXML
    private Label modeLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button primaryBtn;
    @FXML
    private Button toggleBtn;

    private boolean isLoginMode = true;
    private static final String DB_URL = "jdbc:sqlite:shop.db";

    @FXML
    private void handlePrimary() throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please fill in all fields !");
            return;
        }

        if (isLoginMode) {
            // LOGIN SUCCESS
            if (authenticate(username, password)) {
                showAlert(AlertType.INFORMATION, "Sucess", "Login Successful! Welcome to ShopFX!");

                App.setRoot("main");

                Object ctrl = App.getController();
                if (ctrl instanceof MainController mainCtrl) {
                    mainCtrl.setCurrentUser(username);
                }

                clearFields();

            } else {
                showAlert(AlertType.ERROR, "Error", "Invalid username or password!");
            }
        } else {
            //REGISTER mode
            if (register(username, password)) {
                showAlert(AlertType.INFORMATION, "Success", "Account created! You can now login!");
                handleToggle();
            } else {
                showAlert(AlertType.ERROR, "Error", "Username already exists! Enter a unique one.");
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

        usernameField.clear();
        passwordField.clear();

    }

    private boolean authenticate(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("password").equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    private boolean register(String username, String password) {
        String checksql = "SELECT username FROM users WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(checksql)) {
            stmt.setString(1, username);
            if (stmt.executeQuery().next()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Inserting new users
        String insertSql = "INSERT INTO users (username, password) VALUES(?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        usernameField.clear();;
        passwordField.clear();
    }

    private void ensureDatabaseExists() {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(50) NOT NULL
            );
        """;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
        } catch (Exception e) {
            System.out.println("Failed to create tables!");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        ensureDatabaseExists();
    }
}
