package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class LoginController {

    private HashMap<String, String> users = new HashMap<>();

    private boolean isLogin = true;

    // ✅ 2. FXML COMPONENTS
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

    @FXML
    public void initialize() {
        users.put("admin", "123456");
    }
    
    @FXML
    private void handlePrimary() {

        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (isLogin) {

            if (users.containsKey(user) && users.get(user).equals(pass)) {
                modeLabel.setText("Login successful!");
                goToShop();
            } else {
                modeLabel.setText("Invalid login!");
            }

        } else {

            if (user.isEmpty() || pass.isEmpty()) {
                modeLabel.setText("Fill all fields!");
                return;
            }

            users.put(user, pass);
            modeLabel.setText("Registered successfully!");
            switchToLogin();
        }
    }

    @FXML
    private void handleToggle() {
        isLogin = !isLogin;

        if (isLogin) {
            switchToLogin();
        } else {
            switchToRegister();
        }
    }

    private void switchToLogin() {
        isLogin = true;
        modeLabel.setText("Sign In");
        primaryBtn.setText("Login");
        toggleBtn.setText("Register");
    }

    private void switchToRegister() {
        isLogin = false;
        modeLabel.setText("Sign Up");
        primaryBtn.setText("Create Account");
        toggleBtn.setText("Back to Login");
    }

private void goToShop() {
    try {
        System.out.println("Attempting to load Shop.fxml...");

        var resource = getClass().getResource("/view/Shop.fxml");

        System.out.println("Resource: " + resource);

        if (resource == null) {
            System.out.println("❌ Shop.fxml NOT FOUND. Check path!");
            return;
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        Stage stage = (Stage) primaryBtn.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Shop");
        stage.show();

        System.out.println("✅ Scene switched successfully");

    } catch (Exception e) {
        System.out.println("❌ ERROR LOADING SHOP:");
        e.printStackTrace();
    }
}
}
