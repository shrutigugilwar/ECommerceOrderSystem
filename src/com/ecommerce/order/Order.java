
package com.ecommerce.order;

import com.ecommerce.cart.Cart;
import com.ecommerce.cart.CartItem;
import com.ecommerce.payment.Payment;

public class Order {
    private Cart cart;
    private OrderStatus status;

    public Order(Cart cart) {
        this.cart = cart;
        this.status = OrderStatus.PENDING;
    }

    public void displayOrder() {
        System.out.println("Your Order:");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getProduct().getName() + " x " + item.getQuantity()
                    + " = " + item.getTotalPrice());
        }
        System.out.println("Total: " + cart.calculateTotal());
    }

    public void makePayment(Payment payment) {
        payment.pay(cart.calculateTotal());
        this.status = OrderStatus.PAID;
        System.out.println("Order status: " + status);
    }

    public String getStatus() {
        return status.toString();
    }
}
