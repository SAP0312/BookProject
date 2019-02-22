package com.bookCRUD.entities;

import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_generator")
    @SequenceGenerator(name="book_generator", sequenceName = "book_seq", initialValue = 1,allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String title;
    private String author;
    private String publisher;
    private String language;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
