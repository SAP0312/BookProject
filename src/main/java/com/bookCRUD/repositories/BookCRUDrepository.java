package com.bookCRUD.repositories;

import com.bookCRUD.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookCRUDrepository extends CrudRepository<Book,Integer> {
}
