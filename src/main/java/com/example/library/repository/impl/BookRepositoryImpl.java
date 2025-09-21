package com.example.library.repository.impl;

import com.example.library.entity.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.library.repository.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public Book save(Book book) {
        String sql = "INSERT INTO book (title, author, isbn) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn());
        return book;
}


	@Override
	public List<Book> findAll() {
		String sql = "SELECT * FROM book";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
	}}
