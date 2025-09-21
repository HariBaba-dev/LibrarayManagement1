package com.example.library.controller.impl;

import com.example.library.entity.Book;
import com.example.library.service.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@WebMvcTest(Controllerimpl.class)
public class ControllerimplTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private BookService bookService;
	    
	    @InjectMocks
	    private Controllerimpl controller;
	    
	    private Book book1;
	    private Book book2;
	    
	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        book1 = new Book();
	        book1.setId(1);
	        book1.setTitle("Effective Java");
	        book1.setAuthor("Joshua Bloch");
	        book1.setIsbn("978-0134685991");

	        book2 = new Book();
	        book2.setId(2);
	        book2.setTitle("Clean Code");
	        book2.setAuthor("Robert C. Martin");
	        book2.setIsbn("978-0132350884");
	    }

	    @Test
	    void testSaveBook() throws Exception {
	        // Arrange
	        Book mockBook = new Book();
	        mockBook.setId(1);
	        mockBook.setTitle("Effective Java");
	        mockBook.setAuthor("Joshua Bloch");
	        mockBook.setIsbn("978-0134685991");

	        Mockito.when(bookService.saveBook(any(Book.class))).thenReturn(mockBook);

	        // Act + Assert
	        mockMvc.perform(post("/books")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"title\":\"Effective Java\",\"author\":\"Joshua Bloch\",\"isbn\":\"978-0134685991\"}"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(1))
	                .andExpect(jsonPath("$.title").value("Effective Java"))
	                .andExpect(jsonPath("$.author").value("Joshua Bloch"))
	                .andExpect(jsonPath("$.isbn").value("978-0134685991"));
	    }
	    @Test
	    void testGetAllBooks_ReturnsList() throws Exception {
	        // Arrange
	        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

	        // Act & Assert
	        mockMvc.perform(get("/books")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()").value(2))
	                .andExpect(jsonPath("$[0].title").value("Effective Java"))
	                .andExpect(jsonPath("$[1].title").value("Clean Code"));
	    }

	    @Test
	    void testGetAllBooks_ReturnsEmptyList() throws Exception {
	        // Arrange
	        when(bookService.getAllBooks()).thenReturn(Arrays.asList());

	        // Act & Assert
	        mockMvc.perform(get("/books")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()").value(0));
	    }
}
