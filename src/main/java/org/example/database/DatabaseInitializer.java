package org.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseInitializer {
    public void initializeDatabase() {
        Properties properties = new Properties();
        String query="create database if not exists library_system";
        try (InputStream inputStream =DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties"))
        {
            if (inputStream == null){
                throw new IOException("db.properties file not found");
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
            try (Connection connection1 =DatabaseConnection.getConnection())
            {
                createBooksTable(connection1);
                createMembersTable(connection1);
            }
        }
        catch (SQLException | IOException e) {
            System.out.println("Something went wrong while connecting database: " + e.getMessage());

        }

    }
    private void createBooksTable(Connection connection) throws SQLException{
        String createBook ="create table if not exists books (book_id int primary key auto_increment,title varchar(100) not null,author varchar(100) not null,category varchar(50),total_copies int not null,available_copies int not null)";
        try( Statement statement = connection.createStatement()) {
            statement.executeUpdate(createBook);
        }
    }
    private void createMembersTable(Connection connection)throws SQLException{
        String createMember="create table if not exists members(member_id int primary key auto_increment,name varchar(100) not null,email varchar(100) not null unique, phone varchar(20))";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(createMember);
        }
    }
}
