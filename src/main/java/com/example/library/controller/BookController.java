package com.example.library.controller;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.library.entity.Book;

@RequestMapping("/books")
public interface BookController {

	  @PostMapping
	    Book saveBook(@RequestBody Book book);
}
