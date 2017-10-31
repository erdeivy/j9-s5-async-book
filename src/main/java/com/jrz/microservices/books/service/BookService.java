package com.jrz.microservices.books.service;

import java.util.List;

import com.jrz.microservices.dto.books.BookDto;

public interface BookService {
	public List<BookDto> findAllBooks();
	
	public BookDto findBook(String isbn);

	public BookDto insertBook(BookDto book);
}
