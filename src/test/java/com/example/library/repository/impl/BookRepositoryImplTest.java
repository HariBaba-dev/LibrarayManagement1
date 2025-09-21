package com.example.library.repository.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.library.entity.Book;


public class BookRepositoryImplTest {

	@Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookRepositoryImpl bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setIsbn("978-0134685991");
    }

    @Test
    void testSaveBook() {
        // Arrange
        String sql = "INSERT INTO book (title, author, isbn) VALUES (?, ?, ?)";
        when(jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn()))
                .thenReturn(1);

        // Act
        Book savedBook = bookRepository.save(book);

        // Assert
        assertNotNull(savedBook);
        assertEquals("Effective Java", savedBook.getTitle());
        assertEquals("Joshua Bloch", savedBook.getAuthor());
        assertEquals("978-0134685991", savedBook.getIsbn());

        verify(jdbcTemplate, times(1))
                .update(sql, book.getTitle(), book.getAuthor(), book.getIsbn());
    }
}

