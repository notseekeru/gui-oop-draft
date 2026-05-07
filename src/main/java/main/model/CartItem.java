package main.model;

public class CartItem {

    private int id;             // row id in cart_items table
    private int userId;         // which user owns this cart item
    private Product product;    // the product
    private int quantity;       // how many of this product

    public CartItem(int id, int userId, Product product, int quantity) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(Product product, int quantity) {
        this(0, 0, product, quantity); // temporary for some uses
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductId() {
        return product.getId();
    }

    public String getProductName() {
        return product.getName();
    }

    public double getProductPrice() {
        return product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }

    @Override
    public String toString() {
        return String.format("%s x%d - ₱%.2f", product.getName(), quantity, getSubtotal());
    }
}
