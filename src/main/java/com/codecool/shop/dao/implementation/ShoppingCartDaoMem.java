package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;

import java.util.HashMap;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private HashMap<Integer, Integer> LineItems = new HashMap<>();
    private static ShoppingCartDaoMem instance = null;

    public ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(int productId) {
        if (LineItems.containsKey(productId)) {
            LineItems.replace(productId, LineItems.get(productId)+1);
        } else {
            LineItems.put(productId, 1);
        }
    }

    @Override
    public HashMap<Integer, Integer> getAll() {
        return LineItems;
    }

}
