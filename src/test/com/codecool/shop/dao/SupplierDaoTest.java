package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.database.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {
    DataSource dataSource = Initializer.connect();

    SupplierDaoTest() throws SQLException {
    }


    @Test
    public void testValidIdReturnsSupplier() throws SQLException {
        SupplierDao supplierDao = new SupplierDaoJdbc(dataSource);

        assertTrue(supplierDao.find(1) instanceof Supplier);
    }

    @Test
    public void testInvalidIdReturnsNull() throws SQLException {
        SupplierDao supplierDao = new SupplierDaoJdbc(dataSource);

        assertNull(supplierDao.find(-1));
    }

    @Test
    public void testIsDataReturnsListOfSuppliers() throws SQLException {
        SupplierDao SupplierDao = new SupplierDaoJdbc(dataSource);
        List<Supplier> suppliers = SupplierDao.getAll();
        boolean isSupplier = suppliers.stream().allMatch(c -> c instanceof Supplier);

        assertTrue(isSupplier);
    }

    @Test
    public void testNoDataReturnsNull() throws SQLException {
        SupplierDao SupplierDao = new SupplierDaoJdbc(dataSource);
        List<Supplier> suppliers = SupplierDao.getAll();

        assertNull(suppliers);
    }

}