package main.controller;

import java.io.IOException;
import java.util.List;

import main.App;
import main.SessionContext;
import main.model.Product;
import main.services.CartService;
import main.services.ProductService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class StoreController {

    @FXML
    private Label userLabel;
    @FXML
    private FlowPane productGrid;
    @FXML
    private Button cartButton;

    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();

    @FXML
    private void initialize() {
        if (SessionContext.getCurrentUser() != null) {
            userLabel.setText("Hello, " + SessionContext.getCurrentUser().getUsername());
        }
        loadProducts();
        updateCartLabel();
    }

    private void loadProducts() {
        if (productGrid != null) {
            productGrid.getChildren().clear();
            List<Product> products = productService.findAll();
            for (Product p : products) {
                productGrid.getChildren().add(buildCard(p));
            }
        }
    }

    private VBox buildCard(Product product) {
        VBox card = new VBox(10);
        card.getStyleClass().add("product-card");
        card.setPrefWidth(200);

        Label name = new Label(product.getName());
        name.getStyleClass().add("card-title");
        name.setWrapText(true);

        Label desc = new Label(product.getDescription());
        desc.getStyleClass().add("subtitle-label");
        desc.setWrapText(true);
        desc.setMaxHeight(40);

        Label price = new Label(String.format("$%.2f", product.getPrice()));
        price.getStyleClass().add("price-label");

        Button addBtn = new Button("Add to Cart");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setOnAction(e -> {
            if (SessionContext.getCurrentUser() != null) {
                cartService.addToCart(SessionContext.getCurrentUser().getId(), product.getId());
                updateCartLabel();
            }
        });

        card.getChildren().addAll(name, desc, price, addBtn);
        return card;
    }

    private void updateCartLabel() {
        if (cartButton != null && SessionContext.getCurrentUser() != null) {
            int count = cartService.getCartCount(SessionContext.getCurrentUser().getId());
            cartButton.setText("Cart (" + count + ")");
        }
    }

    @FXML
    private void openCart() throws IOException {
        App.setRoot("cart");
    }

    @FXML
    private void logout() throws IOException {
        SessionContext.clear();
        App.setRoot("login");
    }
}