package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    DataSource dataSource = Initializer.connect();

    ProductDaoTest() throws SQLException, IOException {
    }

    @Test
    public void testValidIdIsDataReturnsProduct() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);

        assertTrue(productDao.find(1) instanceof Product);
    }

    @Test
    public void testValidIdNoDataReturnsNull() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);

        assertNull(productDao.find(1));
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
    public void testNoDataReturnsEmptyList() throws SQLException {
        ProductDao ProductDao = new ProductDaoJdbc(dataSource);
        List<Product> products = ProductDao.getAll();

        assertEquals(0, products.size());
    }

}