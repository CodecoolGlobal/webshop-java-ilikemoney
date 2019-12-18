package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    DataSource dataSource = Initializer.connect();

    ProductDaoTest() throws SQLException {
    }


    @Test
    public void testValidIdReturnsProduct() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);

        assertTrue(productDao.find(1) instanceof Product);
    }

    @Test
    public void testInvalidIdReturnsNull() throws SQLException {
        ProductDao ProductDao = new ProductDaoJdbc(dataSource);

        assertNull(ProductDao.find(-1));
    }

    @Test
    public void testIsDataReturnsListOfProducts() throws SQLException {
        ProductDao ProductDao = new ProductDaoJdbc(dataSource);
        List<Product> products = ProductDao.getAll();
        boolean isProduct = products.stream().allMatch(c -> c instanceof Product);

        assertTrue(isProduct);
    }

    @Test
    public void testNoDataReturnsNull() throws SQLException {
        ProductDao ProductDao = new ProductDaoJdbc(dataSource);
        List<Product> products = ProductDao.getAll();

        assertNull(products);
    }
}