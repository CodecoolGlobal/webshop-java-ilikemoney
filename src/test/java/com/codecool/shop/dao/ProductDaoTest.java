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
    DataSource dataSource = Initializer.connect("connect.properties");

    ProductDaoTest() throws IOException {
    }

    @Test
    public void testValidIdIsDataReturnsProduct() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);

        assertNotNull(productDao.find(1));
    }

    @Test
    public void testInvalidIdThrowsExeption() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);

        assertThrows(IllegalArgumentException.class, () -> productDao.find(-1));
    }

    @Test
    public void testIsDataReturnsListOfProducts() throws SQLException {
        ProductDao productDao = new ProductDaoJdbc(dataSource);
        List<Product> products = productDao.getAll();

        assertNotNull(products);
    }

}