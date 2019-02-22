package com.bookCRUD.controllers;

import com.bookCRUD.entities.Book;
import com.bookCRUD.services.BookCRUDservice;
import com.bookCRUD.services.BookCRUDserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookCRUDcontroller {

    @Autowired
    private BookCRUDserviceImpl bookService;

    @RequestMapping(method = RequestMethod.POST,value = "/books")
    public void addBook(@RequestBody Book book){

        bookService.addBook(book);

    }
}
