package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDao shoppingCart = new ShoppingCartDaoMem();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("cartItems", shoppingCart.getAll());

        engine.process("product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();
        ProductDao productDao = ProductDaoMem.getInstance();
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = productDao.find(productId);
        shoppingCart.add(product);
        System.out.println(shoppingCart.getAll());
        req.getHeader("referer");
        resp.sendRedirect(resp.encodeRedirectURL("/"));
    }
}
