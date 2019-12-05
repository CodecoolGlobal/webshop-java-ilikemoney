package com.codecool.shop.dao;

import java.util.HashMap;

public interface ShoppingCartDao {
    void add(int productId);

    HashMap<Integer, Integer> getAll();
}
