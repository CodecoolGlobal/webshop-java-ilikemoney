package com.codecool.shop.dao.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private DataSource dataSource;

//    Connection connection;

    public SupplierDaoJdbc(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
//        this.connection = dataSource.getConnection();
    }

    private Supplier getSupplier(ResultSet resultSet, int id) throws SQLException {
        Connection connection = dataSource.getConnection();

        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Supplier supplier = new Supplier(name, description);
        supplier.setId(id);

        connection.close();

        return supplier;
    }

    @Override
    public Supplier find(int id) throws SQLException {
        Connection connection = dataSource.getConnection();

        if (id < 0) {
            throw new IllegalArgumentException("id must be non negative!");
        }
        String SQL = "SELECT name, description FROM supplier WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        connection.close();

        if (resultSet.next()) {
            Supplier supplier = getSupplier(resultSet, id);
            return supplier;
        } else {
            return null;
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        Connection connection = dataSource.getConnection();

        List<Supplier> suppliers = new ArrayList<>();
        String SQL = "SELECT id, name, description FROM supplier";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Supplier supplier = getSupplier(resultSet, id);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return suppliers;
    }
}
