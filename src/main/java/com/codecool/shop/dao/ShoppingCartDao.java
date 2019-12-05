package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;

public interface ShoppingCartDao {
    void add(int productId);

    HashMap<Integer, Integer> getAll();
}
