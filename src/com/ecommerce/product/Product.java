
package com.ecommerce.product;

public abstract class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void reduceStock(int qty) {
        if (qty <= stock) stock -= qty;
    }

    // Polymorphism
    public abstract double applyDiscount();
}
