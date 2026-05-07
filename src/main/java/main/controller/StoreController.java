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
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private Label pageLabel;

    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();

    private List<Product> allProducts;
    private int currentPage = 0;
    private static final int ITEMS_PER_PAGE = 25;

    @FXML
    private void initialize() {
        if (SessionContext.getCurrentUser() != null) {
            userLabel.setText("Hello, " + SessionContext.getCurrentUser().getUsername());
        }
        allProducts = productService.findAll();
        loadProducts();
        updateCartLabel();
    }

    private void loadProducts() {
        if (productGrid != null) {
            productGrid.getChildren().clear();
            int start = currentPage * ITEMS_PER_PAGE;
            int end = Math.min(start + ITEMS_PER_PAGE, allProducts.size());

            for (int i = start; i < end; i++) {
                productGrid.getChildren().add(buildCard(allProducts.get(i)));
            }

            updatePaginationControls();
        }
    }

    private void updatePaginationControls() {
        if (prevButton != null && nextButton != null && pageLabel != null) {
            prevButton.setDisable(currentPage == 0);
            nextButton.setDisable((currentPage + 1) * ITEMS_PER_PAGE >= allProducts.size());
            pageLabel.setText("Page " + (currentPage + 1));
        }
    }

    @FXML
    private void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadProducts();
        }
    }

    @FXML
    private void nextPage() {
        if ((currentPage + 1) * ITEMS_PER_PAGE < allProducts.size()) {
            currentPage++;
            loadProducts();
        }
    }

    private VBox buildCard(Product product) {
        VBox card = new VBox(10);
        card.getStyleClass().add("product-card");
        card.setPrefWidth(200);

        Label name = new Label(product.getName());
        name.getStyleClass().add("card-title");
        name.setWrapText(true);

        Label stock = new Label("Stock: " + product.getStock());
        stock.getStyleClass().add("subtitle-label");

        Label price = new Label(String.format("$%.2f", product.getPrice()));
        price.getStyleClass().add("price-label");

        Button addBtn = new Button("Add to Cart");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setOnAction(e -> {
            if (SessionContext.getCurrentUser() != null) {
                cartService.addToCart(SessionContext.getCurrentUser().getUserId(), product.getId());
                updateCartLabel();
            }
        });

        card.getChildren().addAll(name, stock, price, addBtn);
        return card;
    }

    private void updateCartLabel() {
        if (cartButton != null && SessionContext.getCurrentUser() != null) {
            int count = cartService.getCartCount(SessionContext.getCurrentUser().getUserId());
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
