package com.example.librarymanagement.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.librarymanagement.Models.Book;
import com.example.librarymanagement.Models.Rental;
import com.example.librarymanagement.Repositories.BookRepository;
import com.example.librarymanagement.Repositories.RentalRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentalControllerIntegrationTest {

	   @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    @Autowired
	    private RentalRepository rentalRepository;
	    
	    @Autowired
	    private BookRepository bookRepository;

	    @Test
	    public void testRentBook() {
	       
	    	
	    	Book book = new Book();
	    	book.setId(1L);
	    	book.setTitle("Sample Book");
	    	
	    	bookRepository.save(book);
	    	
	        ResponseEntity<Rental> response = restTemplate.postForEntity(
	            createURL("/api/rentals/rent?bookId=1&renterName=John Doe"),
	            null,
	            Rental.class
	        );

	     
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertNotNull(response.getBody());
	        
	    }
	    
	    

	    @Test
	    public void testReturnBook() {
	        
	    	
	    	Book book = new Book();
	    	book.setId(1L); 
	    	book.setTitle("Sample Book");
	    	
	    	bookRepository.save(book);

	        
	        Rental rental = new Rental();
	        rental.setId(1L);
	        rental.setBook(book);
	        rental.setRenterName("Ritik jindal");
	        rental.setRentalDate(LocalDate.now());
	        
	        rentalRepository.save(rental);
	        
	        
	        Long rentalId = rental.getId();

	        ResponseEntity<Rental> response = restTemplate.postForEntity(
	            createURL("/api/rentals/return?rentalId=" + rentalId),
	            null,
	            Rental.class
	        );

	   
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        
	    }

	    @Test
	    public void testGetAllRentals() {
	        ResponseEntity<List<Rental>> response = restTemplate.exchange(
	            createURL("/api/rentals"),
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Rental>>() {}
	        );

	        
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        
	    }

	    @Test
	    public void testGetOverdueRentalsEndpoint() {
	        ResponseEntity<List<Rental>> response = restTemplate.exchange(
	            createURL("/api/rentals/overdue"),
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Rental>>() {}
	        );

	        
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        
	    }

	    
	    private String createURL(String uri) {
	        return "http://localhost:" + port + uri;
	    }
}
