package org.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseInitializer {
    Properties properties = new Properties();
    public void initializeDatabase() {
        String query="create database if not exists library_system";
        try (InputStream inputStream =DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties"))
        {
            if (inputStream == null){
                throw new IOException();
            }
            properties.load(inputStream);
            String url = "jdbc:mysql://localhost:3306/";
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            try(Connection connection = DriverManager.getConnection(url,username,password);
                Statement statement = connection.createStatement())
            {
                statement.executeUpdate(query);
            }
        } catch (SQLException | IOException e) {
            System.out.println("Something went wrong while connecting database: " + e.getMessage());

        }

    }
}
