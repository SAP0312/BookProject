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

    @RequestMapping(method = RequestMethod.PUT, value = "/books/{bookID}")
    public Book updateBook(@RequestBody Book book,@PathVariable(value = "bookID") Integer bookID) {
        if (!bookService.ifExist(bookID)) {
            LOGGER.error("Book not found with id "+bookID);
            throw new ResourceNotFoundException("Book not found with id " + bookID);

        }

        return bookService.updateBook(bookID, book);
    }

    @RequestMapping("/books/{bookID}")
    public Book getBookbyId(@PathVariable(value = "bookID") Integer bookID) {
        return bookService.getBookbyId(bookID);
    }
    @RequestMapping( method = RequestMethod.DELETE,value="/books/{bookID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(value = "bookID")Integer bookID){

        if (!bookService.ifExist(bookID))
            throw new ResourceNotFoundException("Book not found with id " + bookID);

        bookService.deleteBook(bookID);
    }

}
