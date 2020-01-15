package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.ProductCategoryDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.List;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {
    DataSource dataSource = Initializer.connect();

    ProductCategoryDaoTest() throws SQLException {
    }

    @Test
    public void testValidIdIsDataReturnsProductCategory() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);

        assertNotNull(productCategoryDao.find(1));
    }

    @Test
    public void testInvalidIdThrowsExeption() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);

        assertThrows(IllegalArgumentException.class, () -> productCategoryDao.find(-1));
    }

    @Test
    public void testIsDataReturnsListOfProductCategories() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        List<ProductCategory> categories = productCategoryDao.getAll();
        boolean isProductCategory = categories.stream().allMatch(Objects::nonNull);

        assertTrue(isProductCategory);
    }

    @Test
    public void testNoDataReturnsEmptyList() throws SQLException {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        List<ProductCategory> categories = productCategoryDao.getAll();

        assertEquals(0, categories.size());
    }

}