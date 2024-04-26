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

import com.example.librarymanagement.Models.Author;
import com.example.librarymanagement.Repositories.AuthorRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerIntegrationTest {


	    @org.springframework.boot.test.web.server.LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    @Autowired
	    private AuthorRepository authorRepository;

	    @Test
	    public void testgetAllAuthors() {
	        ResponseEntity<List<Author>> response = restTemplate.exchange(
	            createURL("/api/authors"),
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Author>>() {}
	        );

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	    }

	    @Test
	    public void testgetAuthorById() {
	    	
	    	Author author = new Author();
	    	author.setId(1L); 
	    	
	    	
	    	authorRepository.save(author);

	    	
	        ResponseEntity<Author> response = restTemplate.getForEntity(
	            createURL("/api/authors/1"),
	            Author.class
	        );

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	    }

	    @Test
	    public void testCreateAuthor() {
	    	
	    	Author author = new Author();
	    	author.setId(1L);
	    

	        ResponseEntity<Author> response = restTemplate.postForEntity(
	            createURL("/api/authors"),
	            author,
	            Author.class
	        );

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertNotNull(response.getBody());
	      
	    }


	
	    private String createURL(String uri) {
	        return "http://localhost:" + port + uri;
	    }
}
