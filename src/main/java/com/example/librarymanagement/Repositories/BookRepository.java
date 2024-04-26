package com.example.librarymanagement.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.Models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByIsRentedFalse();

	List<Book> findByIsRentedTrue();

	List<Book> findByAuthorId(Long authorId);
	
}
