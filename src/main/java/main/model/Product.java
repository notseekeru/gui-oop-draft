package main.model;

public class Product {

    private final int id;
    private final String name;
    private final double price;
    private final int stock;
    private final String imageUrl;

    public Product(int id, String name, double price, int stock, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }
}
