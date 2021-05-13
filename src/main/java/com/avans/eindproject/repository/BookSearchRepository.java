package com.avans.eindproject.repository;

import java.util.Collection;

import com.avans.eindproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BookSearchRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public Collection<Book> searchBooks(String text) {
		return mongoTemplate.find(Query.query(new Criteria()
						.orOperator(Criteria.where("id").regex(text, "i"),
								Criteria.where("ISBN").regex(text, "i"),
								Criteria.where("bookName").regex(text, "i"),
								Criteria.where("authorName").regex(text, "i"))
						), Book.class);
	}
	
}
