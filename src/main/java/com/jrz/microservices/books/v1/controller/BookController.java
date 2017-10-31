package com.jrz.microservices.books.v1.controller;

import org.springframework.http.ResponseEntity;

import com.jrz.microservices.dto.books.BookDto;

public interface BookController {
	public ResponseEntity<?> getAllBook();
	
	public ResponseEntity<BookDto> getBook(String isbn);
	
	public ResponseEntity<BookDto> postBook(BookDto book);

}
