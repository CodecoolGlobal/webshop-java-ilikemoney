package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.ProductCategoryDaoJdbc;
import com.codecool.shop.model.ProductCategory;

import java.util.List;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {
    DataSource dataSource = Initializer.connect();

    ProductCategoryDaoTest() throws SQLException {
    }

    @Test
    public void testValidIdReturnsProductCategory() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);

        assertTrue(productCategoryDao.find(1) instanceof ProductCategory);
    }

    @Test
    public void testInvalidIdReturnsNull() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);

        assertNull(productCategoryDao.find(-1));
    }

    @Test
    public void testIsDataReturnsListOfProductCategories() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        List<ProductCategory> categories = productCategoryDao.getAll();
        boolean isProductCategory = categories.stream().allMatch(c -> c instanceof ProductCategory);

        assertTrue(isProductCategory);
    }

    @Test
    public void testNoDataReturnsNull() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        List<ProductCategory> categoryList = productCategoryDao.getAll();

        assertNull(categoryList);
    }

}