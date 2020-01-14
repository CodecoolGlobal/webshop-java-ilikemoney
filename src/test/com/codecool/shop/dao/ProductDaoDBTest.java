package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoDBTest {
    DataSource dataSource = Initializer.connect();

    ProductDaoDBTest() throws SQLException {
    }

    @Test
    public void testValidIdNoDataReturnsNull() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);

        assertNull(productDao.find(1));
    }


    @Test
    public void testNoDataReturnsEmptyList() throws SQLException {
        ProductDao ProductDao = new ProductDaoJdbc(dataSource);
        List<Product> products = ProductDao.getAll();

        assertEquals(0, products.size());
    }
}