
package com.ecommerce.product;

public class Grocery extends Product {
    private double weightKg;

    public Grocery(String name, double price, int stock, double weightKg) {
        super(name, price, stock);
        this.weightKg = weightKg;
    }

    @Override
    public double applyDiscount() {
        return getPrice(); // No discount
    }
}
