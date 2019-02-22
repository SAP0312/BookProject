package com.bookCRUD.services;

import com.bookCRUD.entities.Book;

import java.util.List;

public interface BookCRUDservice {

    public void addBook(Book book);

    public Book getBook(Integer bookId);

    public Book updateBook(Integer BookId,Book book);

    public void deleteBook(Integer bookId);

    public List<Book> getAllBooks();
}
