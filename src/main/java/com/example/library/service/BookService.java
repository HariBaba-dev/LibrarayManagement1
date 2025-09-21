package com.example.library.service;

import java.util.List;

import com.example.library.entity.Book;

public interface BookService {
	Book saveBook(Book book);
	 List<Book> getAllBooks();
}
