package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.database.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.dao.database.SupplierDaoJdbc;
import com.codecool.shop.config.TemplateEngineUtil;
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
import java.util.Objects;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    DataSource dataSource = Initializer.connect();

    public ProductController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductCategoryDao productCategoryDao = null;
        ProductDao productDao = null;
        SupplierDao supplierDao = null;
        try {
            productDao = new ProductDaoJdbc(dataSource);
            productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
            supplierDao = new SupplierDaoJdbc(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", Objects.requireNonNull(productCategoryDao).getAll());
        context.setVariable("suppliers", Objects.requireNonNull(supplierDao).getAll());
        context.setVariable("products", Objects.requireNonNull(productDao).getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

}
