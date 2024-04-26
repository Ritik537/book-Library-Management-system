package com.example.librarymanagement.BusinessLogicTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.librarymanagement.Models.Author;
import com.example.librarymanagement.Models.Book;
import com.example.librarymanagement.Models.Rental;
import com.example.librarymanagement.Repositories.BookRepository;
import com.example.librarymanagement.Repositories.RentalRepository;
import com.example.librarymanagement.Service.RentalService;

@SpringBootTest
public class BuisnessLogicTests {

	@Mock
    private BookRepository bookRepository;
	
	@Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    public void testRentBook() {
        
        Author author = new Author();
        author.setId(1L);

        
        Book book = new Book();
        book.setId(1L); 
        book.setAuthor(author);
        book.setRented(false);



        
        when(bookRepository.save(book)).thenReturn(book);

        
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        

        
        assertDoesNotThrow(() -> rentalService.rentBook(1L, "Ritik jindal"));
        
        when(rentalRepository.save(Mockito.any(Rental.class))).thenReturn(new Rental());

    }
    
    @Test
    public void testRentAlreadyRentedBook() {
    	
        Author author = new Author();
        author.setId(1L);

        Book book = new Book();
        book.setId(1L);
        book.setAuthor(author);
        book.setRented(true); 
        
        when(bookRepository.save(book)).thenReturn(book);



        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));


        assertThrows(RuntimeException.class, () -> rentalService.rentBook(1L, "Ritik jindal"));
        
        when(rentalRepository.save(Mockito.any(Rental.class))).thenReturn(new Rental());

    }

    @Test
    public void testReturnBook() {

    	Author author = new Author();
        author.setId(1L);


        Book book = new Book();
        book.setId(1L);
        book.setAuthor(author);
        book.setRented(false); 


        Rental rental = new Rental();
        rental.setId(1L);
        rental.setBook(book);
        rental.setRenterName("Ritik jindal");
        rental.setRentalDate(LocalDate.now().minusDays(14));
        rental.setReturnDate(LocalDate.now().minusDays(7)); 
        rental.setExpreturnDate(LocalDate.now().minusDays(14)); 


        when(rentalRepository.findById(1L)).thenReturn(java.util.Optional.of(rental));

 
        when(rentalService.getOverdueRentals()).thenReturn(List.of(rental));


        assertThrows(RuntimeException.class, () -> rentalService.returnBook(1L));
    }
    
    @Test
    public void testGetOverdueRentals() {

    	
        Author author = new Author();
        author.setId(1L);


        Book book = new Book();
        book.setId(1L);
        book.setAuthor(author);
        book.setRented(false); 


        Rental rental1 = new Rental();
        rental1.setId(1L);
        rental1.setBook(book);
        rental1.setRenterName("John Doe");
        rental1.setRentalDate(LocalDate.now().minusDays(21)); 
        rental1.setExpreturnDate(LocalDate.now().minusDays(14)); 
        rental1.setReturnDate(null); 

        Rental rental2 = new Rental();
        rental2.setId(2L);
        rental2.setBook(book);
        rental2.setRenterName("Jane Smith");
        rental2.setRentalDate(LocalDate.now().minusDays(30)); 
        rental2.setExpreturnDate(LocalDate.now().minusDays(21)); 
        rental2.setReturnDate(null); 


        when(rentalRepository.findAll()).thenReturn(List.of(rental1, rental2));


        List<Rental> overdueRentals = rentalService.getOverdueRentals();


        assertEquals(2, overdueRentals.size());
    }

    }


