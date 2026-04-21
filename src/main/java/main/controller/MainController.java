package main.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import main.controller.MainController.Product;

public class MainController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> stockColumn;
    @FXML
    private ListView<String> cartListView;
    @FXML
    private Label totalLabel;
    @FXML
    private Button checkoutButton;

    private final ObservableList<Product> products = FXCollections.observableArrayList();
    private final ObservableList<String> cartItems = FXCollections.observableArrayList();
    private double total = 0.0;
    private String currentUsername;

    // TODO: KURT ALISIN MO YUNG MGA DI KAILANGAN DITO, TANGALIN MO YUNG MAIN DASHBOARD ENTIRELY GUSTO KO BLANK LANG PARA KAY JUSTINE
    public static class Product {

        private final int id;
        private final String name;
        private final double price;
        private final int stock;

        public Product(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //sample products only
        products.addAll(
                new Product(1, "Ben 10 Boxer Shorts", 20.15, 30),
                new Product(2, "SpongeBob Boxer Shorts", 20.15, 67),
                new Product(3, "Sophia The First Dress", 25.30, 57),
                new Product(4, "Pancit Canton T-Shirt", 30.99, 30)
        );

        productTable.setItems(products);

        //Double click to add to card feature <33
        productTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleAddToCart();
            }
        });

        cartListView.setItems(cartItems);
        checkoutButton.setDisable(true); //enable only when cart has items
    }

    private void handleAddToCart() {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }

        // cart logic
        String cartEntry = selected.getName() + " -$" + selected.getPrice();
        cartItems.add(cartEntry);
        total += selected.getPrice();

        totalLabel.setText("$" + String.format("%.2f", total));
        checkoutButton.setDisable(false);
    }

    @FXML
    private void handleCheckout() {
        if (cartItems.isEmpty()) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checkout");
        alert.setHeaderText("Thank you for shopping with us, " + currentUsername + "!");
        alert.setContentText("Total paid: $" + String.format("%2f", total)); //Note to Stephen : I suggest adding payment system --Kurt
        alert.showAndWait();

        cartItems.clear();
        total = 0.0;
        totalLabel.setText("$0.00");
        checkoutButton.setDisable(true);
    }

    @FXML
    private void handleLogout() {
        try {
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load the login screen");
        }
    }

    public void setCurrentUser(String username) {
        this.currentUsername = username;
        welcomeLabel.setText("Welcome, " + username + "!");
    }
}
