package com.example.librarymanagement.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.Models.Book;
import com.example.librarymanagement.Models.Rental;
import com.example.librarymanagement.Repositories.BookRepository;
import com.example.librarymanagement.Repositories.RentalRepository;

@Service
public class RentalService {
	
    
	private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    



    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
    }
    
    public Rental rentBook(Long bookId, String renterName) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.isRented()) {
            throw new RuntimeException("Book already rented");
        }

        Rental rental = new Rental();
        rental.setBook(book);
        rental.setRenterName(renterName);
        rental.setRentalDate(LocalDate.now());
        rental.setExpreturnDate(LocalDate.now().plusDays(14));
        rental.setReturnDate(null);
        book.setRented(true);

        bookRepository.save(book); 
        return rentalRepository.save(rental);
    }
    
    public Rental returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        if (rental.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        Book book = rental.getBook();
        book.setRented(false);
        bookRepository.save(book); 

        rental.setReturnDate(LocalDate.now());
        return rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    
    public List<Rental> getOverdueRentals() {
        LocalDate today = LocalDate.now();
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getReturnDate() == null && rental.getExpreturnDate().isBefore(today))
                .collect(Collectors.toList());
    }
}
