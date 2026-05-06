package main.controller;

import java.io.IOException;
import main.App;
import main.SessionContext;
import main.services.userService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DashboardController {

    @FXML
    private void handleLogout() throws IOException {
        SessionContext.clear();
        App.setRoot("login");
    }
}