package com.example.library.controller.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.controller.BookController;
import com.example.library.entity.Book;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/books")
public class Controllerimpl implements BookController {

    @Autowired
    private BookService bookService;

	@Override
	public Book saveBook(Book book) {
		return bookService.saveBook(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}


	


}


	
