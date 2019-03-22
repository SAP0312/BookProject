package com.bookCRUD.controllers;

import com.bookCRUD.entities.Book;
import com.bookCRUD.services.BookService;
import com.bookCRUD.services.UploadService;
import com.utilities.ResourceNotFoundException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

// This is the rest controller for book management
@RestController
public class BookController {

    // Logger for logging
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("bookCRUDService")  //Qualifier can be changed if we want to change our implementation
    private BookService bookService;

    @Autowired
    @Qualifier("uploadService")
    private UploadService uploadService;


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
    public String FileUpload(@RequestParam("file") MultipartFile file) {
        return uploadService.fileUpload(file);
    }

    @RequestMapping(method = RequestMethod.POST, value = "books/upload")
    public String fileUpload(@RequestParam("file") HttpServletRequest request) throws IOException, FileUploadException {

        ServletFileUpload upload = new ServletFileUpload();

        FileItemIterator iterator = upload.getItemIterator(request);
        while (iterator.hasNext()) {
            FileItemStream item = iterator.next();

            if (!item.isFormField()) {
                InputStream inputStream = item.openStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = br.readLine()) != null) {
//                    LOGGER.info(line);
                }
            }

        }
        return "Uploaded Succesfully";
    }
    
  
}