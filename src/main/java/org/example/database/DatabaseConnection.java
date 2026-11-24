package org.example.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection {
    private static final HikariDataSource dataSource ;
    static {
        Properties properties = new Properties();
        try(InputStream inputStream = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (inputStream==null){
                throw new IOException("File not found");
            }
            properties.load(inputStream);
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
            hikariConfig.setUsername(properties.getProperty("db.username"));
            hikariConfig.setPassword(properties.getProperty("db.password"));
            hikariConfig.setMaximumPoolSize(2);
            hikariConfig.setConnectionTimeout(3000);
            hikariConfig.setPoolName("HikariPool");
            dataSource=new HikariDataSource(hikariConfig);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
