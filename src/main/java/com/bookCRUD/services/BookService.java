package com.bookCRUD.services;

import com.bookCRUD.entities.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

// This is the interface for Book manaegement CRUD service
public interface BookService {

    public void addBook(Book book);

    public Book getBookbyId(Integer bookId);

    public Book updateBook(Integer BookId,Book book);

    public void deleteBook(Integer bookId);

    public List<Book> getAllBooks();

    public boolean ifExist(Integer bookId);

    public void saveAll(List<Book> list);
}
