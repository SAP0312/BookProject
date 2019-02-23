package com.bookCRUD.repositories;

import com.bookCRUD.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository extends CrudRepository<Book,Integer> {
}
