package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private DataSource dataSource;

    Connection connection;

    public ProductCategoryDaoJdbc(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    private ProductCategory getProductCategory(ResultSet resultSet, int id) throws SQLException {
        String name = resultSet.getString("name");
        String department = resultSet.getString("department");
        String description = resultSet.getString("description");
        ProductCategory productCategory = new ProductCategory(name, department, description);
        productCategory.setId(id);
        return productCategory;
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        if (id < 0) {
            throw new IllegalArgumentException("id must be non negative!");
        }
        String SQL = "SELECT name, department, description FROM category WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            ProductCategory productCategory = getProductCategory(resultSet, id);
            resultSet.close();
            return productCategory;
        } else {
            resultSet.close();
            return null;
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategories = new ArrayList<>();
        String SQL = "SELECT id, name, department, description FROM category";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                ProductCategory productCategory = getProductCategory(resultSet, id);
                productCategories.add(productCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategories;
    }
}
