package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class Initializer implements ServletContextListener{
    static InputStream inputStream;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public static DataSource connect() throws SQLException, IOException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        Properties prop = new Properties();
        String propFileName = "connect.properties";

        inputStream = Initializer.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        dataSource.setDatabaseName(prop.getProperty("databasename"));
        dataSource.setUser(prop.getProperty("user"));
        dataSource.setPassword(prop.getProperty("password"));

        return dataSource;
    }
}
