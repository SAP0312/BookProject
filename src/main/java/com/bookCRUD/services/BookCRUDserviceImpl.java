package com.bookCRUD.services;


import com.bookCRUD.entities.Book;
import com.bookCRUD.repositories.BookCRUDrepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("bookCRUDService")
public class BookCRUDserviceImpl implements BookCRUDservice {

    // Logger for logging
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
            String line = "";
            int i = 0;
             Book book= new Book();
            while ((line = br.readLine()) != null) {
                LOGGER.info(line);
                String[] bookDetails = line.split(",");
                book.setTitle(bookDetails[0]);
                book.setAuthor(bookDetails[1]);
                book.setCategory(bookDetails[2]);
                book.setPublisher(bookDetails[3]);
                this.addBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Uploaded Succesfully";
    }
}
