
package com.ecommerce.product;

public class Clothing extends Product {
    private String size;

    public Clothing(String name, double price, int stock, String size) {
        super(name, price, stock);
        this.size = size;
    }

    @Override
    public double applyDiscount() {
        return getPrice() * 0.80; // 20% discount
    }
}
