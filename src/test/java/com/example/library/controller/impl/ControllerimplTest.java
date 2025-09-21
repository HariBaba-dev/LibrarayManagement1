package com.example.library.controller.impl;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controllerimpl.class)
public class ControllerimplTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private BookService bookService;

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
}
