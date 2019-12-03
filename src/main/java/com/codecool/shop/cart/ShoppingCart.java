package com.codecool.shop.cart;

import com.codecool.shop.model.Product;

import java.util.HashMap;

public class ShoppingCart {
    public HashMap<Product, Integer> LineItems = new HashMap<>();
    private double subtotalPrice;
    private double totalPrice;
}
