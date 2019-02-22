package com.bookCRUD.controllers;

import com.bookCRUD.entities.Book;
import com.bookCRUD.services.BookCRUDservice;
import com.bookCRUD.services.BookCRUDserviceImpl;
import com.utilities.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookCRUDcontroller {

    @Autowired
    private BookCRUDserviceImpl bookService;

    @RequestMapping(method = RequestMethod.POST,value = "/books")
    public void addBook(@RequestBody Book book){

        bookService.addBook(book);
    }

    @RequestMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/books")
    public Book updateBook(@RequestBody Book book){
        if(!bookService.ifExist(book.getId()))
            throw new ResourceNotFoundException("Book not found with id " + book.getId());

        return bookService.updateBook(book.getId(),book);
    }

    
}
