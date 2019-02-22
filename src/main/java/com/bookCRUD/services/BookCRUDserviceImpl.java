package com.bookCRUD.services;

import com.bookCRUD.entities.Book;
import com.bookCRUD.repositories.BookCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class BookCRUDserviceImpl implements BookCRUDservice {
    @Autowired
    @Qualifier("First")
    private BookCRUDrepository bookRepository;

    @Override
    public void addBook(Book book){
        bookRepository.save(book);
    }

    @Override
    public Book getBook(Integer bookId){
        return bookRepository.findOne(bookId);
    }

    @Override
    public Book updateBook(Integer BookId,Book book){
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer bookId){
        bookRepository.delete(bookId);
    }

    @Override
    public List<Book> getAllBooks(){
        List<Book> books=new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }
}
