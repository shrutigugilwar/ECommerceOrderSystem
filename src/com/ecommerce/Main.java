package com.ecommerce;

import com.ecommerce.cart.Cart;
import com.ecommerce.cart.CartItem;
import com.ecommerce.order.Order;
import com.ecommerce.payment.CreditCardPayment;
import com.ecommerce.payment.UPIPayment;
import com.ecommerce.payment.WalletPayment;
import com.ecommerce.product.Clothing;
import com.ecommerce.product.Electronics;
import com.ecommerce.product.Grocery;
import com.ecommerce.product.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cart cart = new Cart();

        System.out.println("=== Welcome to E-Commerce System ===");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Cart");
            System.out.println("3. Remove Product");
            System.out.println("4. Checkout & Pay");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addProductToCart(sc, cart);
                case 2 -> viewCart(cart);
                case 3 -> removeProduct(sc, cart);
                case 4 -> checkout(sc, cart);
                case 5 -> {
                    System.out.println("Thank you for visiting!");
                    running = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }

    // Add product method
    private static void addProductToCart(Scanner sc, Cart cart) {
        System.out.println("\nChoose a product type:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Grocery");

        int type = sc.nextInt();
        Product product = null;

        switch (type) {
            case 1 -> {
                System.out.print("Enter Electronics name: ");
                String name = sc.next();
                System.out.print("Enter price: ");
                double price = sc.nextDouble();
                System.out.print("Enter stock: ");
                int stock = sc.nextInt();
                System.out.print("Enter warranty years: ");
                int warranty = sc.nextInt();
                product = new Electronics(name, price, stock, warranty);
            }
            case 2 -> {
                System.out.print("Enter Clothing name: ");
                String name = sc.next();
                System.out.print("Enter price: ");
                double price = sc.nextDouble();
                System.out.print("Enter stock: ");
                int stock = sc.nextInt();
                System.out.print("Enter size (S/M/L): ");
                String size = sc.next();
                product = new Clothing(name, price, stock, size);
            }
            case 3 -> {
                System.out.print("Enter Grocery name: ");
                String name = sc.next();
                System.out.print("Enter price: ");
                double price = sc.nextDouble();
                System.out.print("Enter stock: ");
                int stock = sc.nextInt();
                System.out.print("Enter weight (kg): ");
                double weight = sc.nextDouble();
                product = new Grocery(name, price, stock, weight);
            }
            default -> System.out.println("Invalid product type!");
        }

        if (product != null) {
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            cart.addProduct(product, qty);
            System.out.println(product.getName() + " added to cart.");
        }
    }

    // View cart
    private static void viewCart(Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("\nYour Cart:");
            for (CartItem item : cart.getItems()) {
                System.out.println(item.getProduct().getName() + " x " + item.getQuantity() +
                        " = " + item.getTotalPrice());
            }
            System.out.println("Total: " + cart.calculateTotal());
        }
    }

    // Remove product
    private static void removeProduct(Scanner sc, Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\nSelect product to remove:");
        for (int i = 0; i < cart.getItems().size(); i++) {
            CartItem item = cart.getItems().get(i);
            System.out.println((i + 1) + ". " + item.getProduct().getName() + " x " + item.getQuantity());
        }

        int removeIndex = sc.nextInt() - 1;
        if (removeIndex >= 0 && removeIndex < cart.getItems().size()) {
            String removed = cart.getItems().get(removeIndex).getProduct().getName();
            cart.getItems().remove(removeIndex);
            System.out.println(removed + " removed from cart.");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Checkout
    private static void checkout(Scanner sc, Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty. Add products first!");
            return;
        }

        Order order = new Order(cart);
        order.displayOrder();

        System.out.println("\nChoose Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        System.out.println("3. Wallet");
        int payChoice = sc.nextInt();

        switch (payChoice) {
            case 1 -> order.makePayment(new CreditCardPayment());
            case 2 -> order.makePayment(new UPIPayment());
            case 3 -> order.makePayment(new WalletPayment());
            default -> System.out.println("Invalid payment method!");
        }
        System.out.println("\n=== Order Summary ===");
        order.displayOrder();
        System.out.println("Order Status: " + order.getStatus());
    }
}
