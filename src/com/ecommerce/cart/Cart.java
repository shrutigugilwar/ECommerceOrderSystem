
package com.ecommerce.cart;

import java.util.ArrayList;
import java.util.List;
import com.ecommerce.product.Product;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
        product.reduceStock(quantity);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
