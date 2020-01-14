package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.ProductCategoryDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoDBTest {
    DataSource dataSource = Initializer.connect();


    ProductCategoryDaoDBTest() throws SQLException {
    }

    @Test
    public void testValidIdNoDataReturnsNull() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);

        assertNull(productCategoryDao.find(1));
    }

    @Test
    public void testNoDataReturnsEmptyList() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        List<ProductCategory> categories = productCategoryDao.getAll();

        assertEquals(0, categories.size());
    }
}