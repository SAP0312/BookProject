package com.bookCRUD.services;

import com.bookCRUD.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookCRUDservice {

    public void addBook(Book book);

    public Book getBookbyId(Integer bookId);

    public Book updateBook(Integer BookId,Book book);

    public void deleteBook(Integer bookId);

    public List<Book> getAllBooks();

    public boolean ifExist(Integer bookId);
}
