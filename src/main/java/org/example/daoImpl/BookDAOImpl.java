package org.example.daoImpl;

import org.example.dao.BookDAO;
import org.example.database.DatabaseConnection;
import org.example.model.Book;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean addBook(Book book) {
        String insertBook="insert into books (title,author,category,total_copies,available_copies) values (?,?,?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertBook))
        {
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getCategory());
            preparedStatement.setInt(4,book.getTotalCopies());
            preparedStatement.setInt(5,book.getAvailableCopies());
            int affectedRow= preparedStatement.executeUpdate();
            if(affectedRow!=0){
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong while adding book: "+e.getMessage());
        }
        return false;
    }

    @Override
    public Book getBookById(int bookId) {
        String getBook = "select * from books where book_id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getBook))
        {
            preparedStatement.setInt(1,bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                   Book book = new Book(
                            resultSet.getInt("book_id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("category"),
                            resultSet.getInt("total_copies"),
                            resultSet.getInt("available_copies")

                    );
                    return book;
                }
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong while retrieving book detail through id: "+e.getMessage());
        }
        return  null;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.of();
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public boolean deleteBook(int bookId) {
        return false;
    }

    @Override
    public List<Book> searchBooks(String word) {
        return List.of();
    }
}
