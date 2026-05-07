package main.dao;

import main.model.CartItem;
import main.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    private static final String DB_URL = "jdbc:sqlite:shopfx.db";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Add product to cart (increment if exists)
    public void addToCart(int userId, int productId) {
        String sql = """
            INSERT INTO cart_items (user_id, product_id, quantity)
            VALUES (?, ?, 1)
            ON CONFLICT(user_id, product_id)
            DO UPDATE SET quantity = quantity + 1
        """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove one cart item by its row id
    public void removeItem(int cartItemId) {
        String sql = "DELETE FROM cart_items WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Clear all items for a user
    public void clearCart(int userId) {
        String sql = "DELETE FROM cart_items WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load cart items for a user
    public List<CartItem> getCartItems(int userId) {
        List<CartItem> items = new ArrayList<>();
        String sql = """
            SELECT ci.id, ci.user_id, ci.product_id, ci.quantity,
                   p.product_id as p_id, p.name, p.price, p.stock_quantity
            FROM cart_items ci
            JOIN products p ON p.product_id = ci.product_id
            WHERE ci.user_id = ?
            ORDER BY p.name
        """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("p_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                    );
                    items.add(new CartItem(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        product,
                        rs.getInt("quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Count total quantity for a user
    public int getCartCount(int userId) {
        String sql = "SELECT COALESCE(SUM(quantity), 0) FROM cart_items WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
