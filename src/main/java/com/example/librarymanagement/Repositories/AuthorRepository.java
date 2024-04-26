package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.Models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
}
