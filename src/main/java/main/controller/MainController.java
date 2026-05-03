package main.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;

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

    private final ObservableList<Product> products = FXCollections.observableArrayList();
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
