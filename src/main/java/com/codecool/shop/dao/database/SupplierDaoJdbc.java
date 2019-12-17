package com.codecool.shop.dao.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private DataSource dataSource;

    Connection connection;

    public SupplierDaoJdbc(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

//    @Override
//    public void add(Supplier supplier) {
//
//    }

    @Override
    public Supplier find(int id) throws SQLException {
        String SQL = "SELECT name, description FROM supplier WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Supplier supplier = new Supplier(name, description);
            return supplier;
        } else {
            return null;
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        String SQL = "SELECT id, name, description FROM supplier";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Supplier supplier = new Supplier(name, description);
                supplier.setId(id);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
