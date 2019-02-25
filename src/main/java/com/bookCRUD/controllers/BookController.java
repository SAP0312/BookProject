package com.bookCRUD.controllers;

import com.bookCRUD.entities.Book;
import com.bookCRUD.services.BookService;
import com.utilities.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// This is the rest controller for book management
@RestController
public class BookController {

    // Logger for logging
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("bookCRUDService")  //Qualifier can be changed if we want to change our implementation
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
        LOGGER.info("New book added :" + book.toString());
    }

    @RequestMapping("/books")
    public List<Book> getAllBooks() {

        return bookService.getAllBooks();
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/books/{bookID}")
    public Book updateBook(@RequestBody Book book, @PathVariable(value = "bookID") Integer bookID) {
        if (!bookService.ifExist(bookID)) {
            LOGGER.error("Book not found with id " + bookID);
            throw new ResourceNotFoundException("Book not found with id " + bookID);
        }

        return bookService.updateBook(bookID, book);
    }

    @RequestMapping("/books/{bookID}")
    public Book getBookbyId(@PathVariable(value = "bookID") Integer bookID) {

        return bookService.getBookbyId(bookID);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{bookID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(value = "bookID") Integer bookID) {

        if (!bookService.ifExist(bookID))
            throw new ResourceNotFoundException("Book not found with id " + bookID);
        bookService.deleteBook(bookID);
    }

    @RequestMapping(method = RequestMethod.POST, value = "books/uploadFile")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        try {
//            byte[] bytes = file.getBytes();
           InputStream inputFilestream = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
            String line = "";
            int i = 0;
            Book book;
            List<Book> listBooks = new ArrayList<Book>();
            while ((line = br.readLine()) != null) {
                book = new Book();
                LOGGER.info(line);
                String[] bookDetails = line.split(",");
                book.setTitle(bookDetails[0]);
                book.setAuthor(bookDetails[1]);
                book.setCategory(bookDetails[2]);
                book.setPublisher(bookDetails[3]);
                book.setLanguage(bookDetails[4]);
//                bookService.addBook(book);
                listBooks.add(book);
            }
            bookService.saveAll(listBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Uploaded Succesfully";
    }
}
