package main.model;

public class CartItem {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d — %s", product.getName(), quantity, formatCurrency(getSubtotal()));
    }

    private String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
}


 // Represents one item in the cart
 // Handles quantity and subtotal calculation
 // Depends on Product for price and name