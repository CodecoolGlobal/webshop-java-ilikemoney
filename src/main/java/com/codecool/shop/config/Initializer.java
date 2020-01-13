package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public static DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName("codecoolshop");
        dataSource.setUser("gergo");
        dataSource.setPassword("codecool");

        return dataSource;
    }
}
