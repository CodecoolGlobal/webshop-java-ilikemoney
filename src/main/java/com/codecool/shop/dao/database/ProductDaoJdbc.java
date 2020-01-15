package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private DataSource dataSource;

    Connection connection;

    public ProductDaoJdbc(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    private Product getProduct(ResultSet resultSet, int id) throws SQLException {
        String name = resultSet.getString("name");
        float defaultPrice = resultSet.getFloat("default_price");
        String currency = resultSet.getString("currency");
        String description = resultSet.getString("description");
        int categoryId = resultSet.getInt("category_id");
        int supplierId = resultSet.getInt("supplier_id");
        ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
        SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
        Product product = new Product(name, defaultPrice, currency, description, productCategoryDaoJdbc.find(categoryId), supplierDaoJdbc.find(supplierId));
        product.setId(id);
        return product;
    }

//    @Override
//    public void add(Product product) {
//
//    }

    @Override
    public Product find(int id) throws SQLException {
        if (id < 0) {
            throw new IllegalArgumentException("id must be non negative!");
        }
        String SQL = "SELECT name, default_price, currency, description, category_id, supplier_id FROM product WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Product product = getProduct(resultSet, id);
            return product;
        } else {
            return null;
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String SQL = "SELECT id, name, default_price, currency, description, category_id, supplier_id FROM product";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Product product = getProduct(resultSet, id);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
