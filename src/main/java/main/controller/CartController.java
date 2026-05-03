package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.CartItem;
import main.model.Product;

public class CartController {

    private final ObservableList<CartItem> cartItems = FXCollections.observableArrayList();

    public ObservableList<CartItem> getCartItems() {
        return cartItems;
    }

    public void addProduct(Product product) {
        if (product == null) {
            return;
        }

        CartItem existing = cartItems.stream()
                .filter(item -> item.getProduct().getId() == product.getId())
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.incrementQuantity();
        } else {
            cartItems.add(new CartItem(product, 1));
        }
    }

    public void removeItem(CartItem item) {
        if (item == null) {
            return;
        }

        if (item.getQuantity() > 1) {
            item.decrementQuantity();
        } else {
            cartItems.remove(item);
        }
    }

    public double getTotal() {
        return cartItems.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public void clearCart() {
        cartItems.clear();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}

// Main cart manager (handles all cart operations)
 // Adds, removes, updates items and calculates total
 // Depends on CartItem and Product