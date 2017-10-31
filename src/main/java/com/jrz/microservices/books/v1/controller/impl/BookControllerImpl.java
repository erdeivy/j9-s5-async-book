package com.jrz.microservices.books.v1.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jrz.microservices.books.service.BookService;
import com.jrz.microservices.books.v1.controller.BookController;
import com.jrz.microservices.dto.books.BookDto;

@RestController
@RequestMapping(value = "/v1/books")
public class BookControllerImpl implements BookController {

	@Autowired
	private BookService bookService;

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllBook() {
		return ResponseEntity.ok(bookService.findAllBooks());
	}
	
	@Override
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	public ResponseEntity<BookDto> getBook(@PathVariable String isbn){
		return ResponseEntity.ok(bookService.findBook(isbn));
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BookDto> postBook(@RequestBody BookDto book) {
		return ResponseEntity.ok(bookService.insertBook(book));
	}

}
