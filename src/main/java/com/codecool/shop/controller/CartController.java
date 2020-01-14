package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.config.TemplateEngineUtil;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    DataSource dataSource = Initializer.connect();

    public CartController() throws SQLException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = null;
        try {
            productDao = new ProductDaoJdbc(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<Integer, Integer> shoppingCart = ShoppingCartDaoMem.getInstance().getAll();
        Map<Product, Integer> cartItems = new HashMap<>();

        for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()) {
            try {
                assert productDao != null;
                cartItems.put(productDao.find(entry.getKey()), entry.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("cartItems", cartItems);
        context.setVariable("totalPrice", getTotalPrice(cartItems));

        engine.process("product/cart.html", context, resp.getWriter());
    }

    private int getTotalPrice(Map<Product, Integer> cartItems) {
        int totalPrice = 0;
        for (Map.Entry<Product,Integer> entry : cartItems.entrySet()){
            switch (entry.getKey().getDefaultCurrency().toString()){
                case "HUF":{
                    totalPrice+=entry.getKey().getDefaultPrice();
                    break;
                }
                case "NOK":{
                    totalPrice+=entry.getKey().getDefaultPrice()*10;
                    break;
                }
                case "SOS":{
                    totalPrice+=entry.getKey().getDefaultPrice()*100;
                    break;
                }
            }
        }
        return totalPrice;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();
        int productId = Integer.parseInt(req.getParameter("productId"));
        shoppingCart.add(productId);
        resp.sendRedirect(resp.encodeRedirectURL("/"));
    }
}
