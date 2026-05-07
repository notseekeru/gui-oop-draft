package main.controller;

import java.io.IOException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import main.SessionContext;
import main.model.CartItem;
import main.services.CartService;

public class CartController {

    @FXML private TableView<CartItem> cartTable;
    @FXML private TableColumn<CartItem, String> nameCol;
    @FXML private TableColumn<CartItem, String> priceCol;
    @FXML private TableColumn<CartItem, Integer> qtyCol;
    @FXML private TableColumn<CartItem, String> subtotalCol;
    @FXML private TableColumn<CartItem, Void> actionCol;
    @FXML private Label totalLabel;

    private final CartService cartService = new CartService();
    private ObservableList<CartItem> items;
    private int currentUserId; // use int instead of String

    @FXML
    private void initialize() {
        if (SessionContext.getCurrentUser() != null) {
            this.currentUserId = SessionContext.getCurrentUser().getUserId();
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                String.format("₱%.2f", c.getValue().getProductPrice())));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        subtotalCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                String.format("₱%.2f", c.getValue().getSubtotal())));

        loadCart();
    }

    private void loadCart() {
        items = FXCollections.observableArrayList(cartService.getCartItemsForUser(currentUserId));
        cartTable.setItems(items);
        double total = cartService.getCartTotal(currentUserId);
        totalLabel.setText(String.format("Total: ₱%.2f", total));
    }

    @FXML
    private void handleCheckout() {
        String result = cartService.checkout(currentUserId, items);
        loadCart();

        if ("Order placed! Thank you." .equals(result)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Purchase Complete");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for purchasing!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Checkout");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRemove() {
        CartItem selectedItem = cartTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            cartService.removeItem(selectedItem.getId());
            loadCart();
        }
    }

    @FXML
    private void handleBackToStore() throws IOException {
        App.setRoot("store");
    }

}
