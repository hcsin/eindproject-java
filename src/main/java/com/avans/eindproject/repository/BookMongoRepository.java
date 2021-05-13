package com.avans.eindproject.repository;

import com.avans.eindproject.model.Book;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface BookMongoRepository extends CrudRepository<Book, Integer>{
    int findById();
}