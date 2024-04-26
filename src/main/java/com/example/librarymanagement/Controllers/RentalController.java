package com.example.librarymanagement.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagement.Models.Rental;
import com.example.librarymanagement.Service.RentalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rentals")
@Validated
public class RentalController {

	
	private final RentalService rentalService;


    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    
    @PostMapping("/rent")
    public ResponseEntity<Rental> rentBook(@RequestParam @Valid Long bookId, @RequestParam @Valid String renterName) {
        Rental rental = rentalService.rentBook(bookId, renterName);
        return new ResponseEntity<>(rental, HttpStatus.CREATED);
    }

    @PostMapping("/return")
    public ResponseEntity<Rental> returnBook(@RequestParam @Valid Long rentalId) {
        Rental rental = rentalService.returnBook(rentalId);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }
    
    @GetMapping("/overdue")
    public ResponseEntity<List<Rental>> getOverdueRentalsEndpoint() {
        List<Rental> overdueRentals = rentalService.getOverdueRentals();
        return new ResponseEntity<>(overdueRentals, HttpStatus.OK);
    }
}
