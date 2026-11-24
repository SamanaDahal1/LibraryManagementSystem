package org.example.daoImpl;

import org.example.dao.BookDAO;
import org.example.model.Book;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean addBook(Book book) {

    }

    @Override
    public Book getBookById(int bookId) {
        return null;
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
