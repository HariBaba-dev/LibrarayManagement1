package com.example.library.service.impl;

import com.example.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	  @Autowired
	    private BookRepository bookRepository;
	

	@Override
	public Book saveBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
		
	}


}
