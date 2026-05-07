package main.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    public void setCurrentUser(int userId) {
        this.currentUserId = userId;
        loadCart();
    }

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                String.format("₱%.2f", c.getValue().getProductPrice())));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        subtotalCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                String.format("₱%.2f", c.getValue().getSubtotal())));

        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Remove");
            {
                btn.setOnAction(e -> {
                    TableRow<CartItem> row = getTableRow();
                    CartItem item = row.getItem();
                    cartService.removeItem(item.getId());
                    loadCart();
                });
            }
            @Override
            protected void updateItem(Void v, boolean empty) {
                super.updateItem(v, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // don’t call loadCart() here until userId is set
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

}
