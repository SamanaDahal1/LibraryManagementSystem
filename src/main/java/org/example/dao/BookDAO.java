package org.example.dao;
import org.example.model.Book;

import java.util.List;

public interface BookDAO {
    boolean addBook(Book book);
    Book getBookById(int bookId);
    List<Book> getAllBooks();
    boolean updateBook(Book book);
    boolean deleteBook(int bookId);
    List<Book> searchBooks(String word);

}
