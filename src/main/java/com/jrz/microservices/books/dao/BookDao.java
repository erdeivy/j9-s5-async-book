package com.jrz.microservices.books.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jrz.microservices.books.model.Book;

@Repository
public interface BookDao extends MongoRepository<Book, ObjectId> {
	public Book insert(Book book);

	public Book findByIsbn(String isbn);
}
