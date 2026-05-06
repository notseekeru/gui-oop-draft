package main.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.App;
import main.SessionContext;

public class DashboardController {

    @FXML private Label usersLabel;
    @FXML private Label productsLabel;
    @FXML private Label ordersLabel;

    @FXML
    public void initialize() {
        usersLabel.setText("10");
        productsLabel.setText("25");
        ordersLabel.setText("5");
   
    }

    @FXML
    private void handleLogout() throws IOException {
        SessionContext.clear();
        App.setRoot("login");
        
    }
}