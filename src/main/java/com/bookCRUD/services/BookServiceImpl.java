package com.bookCRUD.services;


import com.bookCRUD.entities.Book;
import com.bookCRUD.repositories.BookRepository;
import com.utilities.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("bookCRUDService")
public class BookServiceImpl implements BookService {

    // Logger for logging
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookbyId(Integer bookId) {
       Optional<Book> bookOptional= bookRepository.findById(bookId);
       if(bookOptional.isPresent())
       return bookOptional.get();
       else {
    	   throw new ResourceNotFoundException("Book not found with id " + bookId);
	}
    }

    @Override
    public Book updateBook(Integer BookId, Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public void saveAll(List<Book> list){
        bookRepository.saveAll(list);
    }

    @Override
    public boolean ifExist(Integer bookId) {
        return bookRepository.existsById(bookId);
    }


}
