package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private HashMap<Product, Integer> LineItems = new HashMap<>();
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
    public void add(Product product) {
        if (LineItems.containsKey(product)) {
            LineItems.replace(product, LineItems.get(product)+1);
        } else {
            LineItems.put(product, 1);
        }
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return LineItems;
    }

}
