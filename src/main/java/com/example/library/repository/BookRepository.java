package com.example.library.repository;

import java.util.List;

import com.example.library.entity.Book;


public interface BookRepository {
	Book save(Book book);
	 List<Book> findAll();
}
