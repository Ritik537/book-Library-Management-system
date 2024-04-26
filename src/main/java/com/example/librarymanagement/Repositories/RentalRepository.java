package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.Models.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>{

	
	
}
