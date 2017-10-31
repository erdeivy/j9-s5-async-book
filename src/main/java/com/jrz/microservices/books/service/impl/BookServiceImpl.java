package com.jrz.microservices.books.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jrz.microservices.books.dao.BookDao;
import com.jrz.microservices.books.model.Book;
import com.jrz.microservices.books.service.BookService;
import com.jrz.microservices.dto.books.BookDto;
import com.jrz.microservices.dto.comments.CommentDto;

@Service
public class BookServiceImpl implements BookService {

	private static final String INFO_INSERT_BOOK = "Inserting book %s";

	private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class.getName());

	@Autowired
	private RestTemplate rt;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public List<BookDto> findAllBooks() {
		return bookDao.findAll().stream().map(from -> mapper.map(from, BookDto.class)).map(b -> {
			b.setComments(getBookComments(b.getIsbn()));
			return b;
		}).collect(Collectors.toList());
	}

	private List<CommentDto> getBookComments(String isbn) {
		ResponseEntity<CommentDto[]> response = rt.getForEntity("http://localhost:8081/v1/comments/" + isbn,
				CommentDto[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public BookDto insertBook(BookDto book) {
		LOGGER.info(String.format(INFO_INSERT_BOOK, book));
		return mapper.map(bookDao.insert((Book) mapper.map(book, Book.class)), BookDto.class);
	}

	@Override
	public BookDto findBook(String isbn) {
		BookDto book = mapper.map(bookDao.findByIsbn(isbn), BookDto.class);
		book.setComments(getBookComments(isbn));
		return book;
	}

}
