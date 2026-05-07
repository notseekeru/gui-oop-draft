package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.App;
import main.services.CartService;

public class MainController {

    @FXML private TableView<?> productTable;
    @FXML private TableColumn<?, ?> idColumn;
    @FXML private TableColumn<?, ?> nameColumn;
    @FXML private TableColumn<?, ?> priceColumn;
    @FXML private TableColumn<?, ?> stockColumn;
    @FXML private ListView<?> cartListView;
    @FXML private Label totalLabel;
    @FXML private Button checkoutButton;
    @FXML private Button logoutBtn;
    @FXML private Label welcomeLabel;
    @FXML private Label cartCountLabel;

    private int currentUserId;
    private final CartService cartService = new CartService();

    public void setCurrentUser(int userId) {
        this.currentUserId = userId;
        updateCartInfo();
    }

    private void updateCartInfo() {
        int count = cartService.getCartCount(currentUserId);
        cartCountLabel.setText("Items: " + count);
        double total = cartService.getCartTotal(currentUserId);
        totalLabel.setText(String.format("₱%.2f", total));
    }

    @FXML
    private void handleLogout() {
        try {
            App.setRoot("login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCheckout() {
        try {
            App.setRoot("cart");
            // After setting root, get the controller and set user
            main.controller.CartController cartController = (main.controller.CartController) App.getController();
            if (cartController != null) {
                cartController.setCurrentUser(currentUserId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
