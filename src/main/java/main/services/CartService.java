package main.services;

import main.dao.CartDao;
import main.model.CartItem;

import java.util.List;

public class CartService {

    private final CartDao cartDao = new CartDao();

    // Add product to cart for a user
    public void addToCart(int userId, int productId) {
        cartDao.addToCart(userId, productId);
    }

    // Remove item by cart row id
    public void removeItem(int cartItemId) {
        cartDao.removeItem(cartItemId);
    }

    // Clear all items for a user
    public void clearCart(int userId) {
        cartDao.clearCart(userId);
    }

    // Get all cart items for a user
    public List<CartItem> getCartItemsForUser(int userId) {
        return cartDao.getCartItems(userId);
    }

    // Count total quantity of items for a user
    public int getCartCount(int userId) {
        return cartDao.getCartCount(userId);
    }

    // Calculate total price for a user’s cart
    public double getCartTotal(int userId) {
        return getCartItemsForUser(userId).stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    // Checkout logic
    public String checkout(int userId, List<CartItem> items) {
        if (items == null || items.isEmpty()) {
            return "Your cart is empty.";
        }
        clearCart(userId);
        return "Order placed! Thank you.";
    }
}