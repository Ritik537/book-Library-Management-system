package com.example.librarymanagement.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.librarymanagement.Models.Book;
import com.example.librarymanagement.Repositories.BookRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @org.springframework.boot.test.web.server.LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetAllBooks() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(
            createURL("/api/books"),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Book>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetBookById() {
    	
    	Book book = new Book();
    	book.setId(1L); 
    	book.setTitle("Sample Book");
    	
        bookRepository.save(book);

    	
        ResponseEntity<Book> response = restTemplate.getForEntity(
            createURL("/api/books/1"),
            Book.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
    }

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setId(1L);
       

        ResponseEntity<Book> response = restTemplate.postForEntity(
            createURL("/api/books"),
            book,
            Book.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        
    }



    private String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
