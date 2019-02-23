package com.bookCRUD.controllers;

import com.bookCRUD.entities.Book;
import com.bookCRUD.services.BookCRUDservice;
import com.bookCRUD.services.BookCRUDserviceImpl;
import com.utilities.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookCRUDcontroller {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookCRUDserviceImpl bookService;

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody Book book) {

        bookService.addBook(book);
        LOGGER.info("New book added :"+book.toString());
    }

    @RequestMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/books")

    public Book updateBook(@RequestBody Book book) {
        if (!bookService.ifExist(book.getId())) {
            LOGGER.error("Book not found with id "+ book.getId());
            throw new ResourceNotFoundException("Book not found with id " + book.getId());

        }

        return bookService.updateBook(book.getId(), book);
    }

    @RequestMapping("/books/{id}")
    public Book getBookbyId(@PathVariable(value = "id") Integer id) {
        return bookService.getBookbyId(id);
    }
    @RequestMapping( method = RequestMethod.DELETE,value="/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(value = "id")Integer id){

        if (!bookService.ifExist(id))
            throw new ResourceNotFoundException("Book not found with id " + id);

        bookService.deleteBook(id);
    }

}
