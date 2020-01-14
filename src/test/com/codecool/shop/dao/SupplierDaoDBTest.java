package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoDBTest {
    DataSource dataSource = Initializer.connect();


    SupplierDaoDBTest() throws SQLException {
    }

    @Test
    public void testValidIdNoDataReturnsNull() throws SQLException {
        SupplierDao supplierDao = new SupplierDaoJdbc(dataSource);

        assertNull(supplierDao.find(1));
    }

    @Test
    public void testNoDataReturnsEmptyList() throws SQLException {
        SupplierDao SupplierDao = new SupplierDaoJdbc(dataSource);
        List<Supplier> suppliers = SupplierDao.getAll();

        assertEquals(0, suppliers.size());
    }
}