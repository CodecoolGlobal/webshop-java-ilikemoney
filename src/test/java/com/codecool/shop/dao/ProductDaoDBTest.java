package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoDBTest {
    DataSource dataSource = Initializer.connect("connect-testdb.properties");

    ProductDaoDBTest() throws IOException {
    }

    @Test
    public void testNoDataReturnsEmptyList() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);
        List<Product> products = productDao.getAll();

        assertEquals(0, products.size());
    }

}