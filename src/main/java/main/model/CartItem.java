package main.model;

public class CartItem {

<<<<<<< HEAD
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
=======
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
>>>>>>> 9dd94d6bec931f10baf2406e8b985776663b3496
    }

    public Product getProduct() {
        return product;
    }

<<<<<<< HEAD
    public int getProductId() {
        return product.getId();
    }

    public String getProductName() {
        return product.getName();
    }

    public double getProductPrice() {
        return product.getPrice();
    }

=======
>>>>>>> 9dd94d6bec931f10baf2406e8b985776663b3496
    public int getQuantity() {
        return quantity;
    }

<<<<<<< HEAD
=======
    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

>>>>>>> 9dd94d6bec931f10baf2406e8b985776663b3496
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

<<<<<<< HEAD
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
        return String.format("%s x%d - ₱%.2f",
                product.getName(), quantity, getSubtotal());
    }
}
=======
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
>>>>>>> 9dd94d6bec931f10baf2406e8b985776663b3496
