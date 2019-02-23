package com.bookCRUD.services;

import com.bookCRUD.entities.Book;
import com.bookCRUD.repositories.BookCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("bookCRUDService")
public class BookCRUDserviceImpl implements BookCRUDservice {
    @Autowired
    private BookCRUDrepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookbyId(Integer bookId) {
        return bookRepository.findOne(bookId);
    }

    @Override
    public Book updateBook(Integer BookId, Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.delete(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public boolean ifExist(Integer bookId) {

        return bookRepository.exists(bookId);
    }
}
