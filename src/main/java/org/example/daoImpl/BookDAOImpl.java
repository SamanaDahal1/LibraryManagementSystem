package org.example.daoImpl;

import org.example.dao.BookDAO;
import org.example.database.DatabaseConnection;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    return new Book(
                             resultSet.getInt("book_id"),
                             resultSet.getString("title"),
                             resultSet.getString("author"),
                             resultSet.getString("category"),
                             resultSet.getInt("total_copies"),
                             resultSet.getInt("available_copies")

                     );
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
        ArrayList<Book> books = new ArrayList<>();
        String getAllBook = "select * from books";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllBook);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()){
                Book book =  new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getInt("total_copies"),
                        resultSet.getInt("available_copies")

                );
              books.add(book);
            }
            return books;
        }
        catch (SQLException e){
            System.out.println("Something went wrong while retrieving all books: "+e.getMessage());
        }
        return books;
    }

    @Override
    public boolean updateBook(Book book) {
        String updateBook ="update books set title =?,author=?,category=?,total_copies=?,available_copies=? where book_id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateBook))
        {
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getCategory());
            preparedStatement.setInt(4,book.getTotalCopies());
            preparedStatement.setInt(5,book.getAvailableCopies());
            preparedStatement.setInt(6,book.getBookId());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected!=0){
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong while updating book: "+e.getMessage());
        }
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
