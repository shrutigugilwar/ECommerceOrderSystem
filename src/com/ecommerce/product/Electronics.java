
package com.ecommerce.product;

public class Electronics extends Product {
    private int warrantyYears;

    public Electronics(String name, double price, int stock, int warrantyYears) {
        super(name, price, stock);
        this.warrantyYears = warrantyYears;
    }

    @Override
    public double applyDiscount() {
        return getPrice() * 0.90; // 10% discount
    }
}
