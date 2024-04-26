package com.example.librarymanagement.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
public class Book {
	

	
	@Id
    private Long id;
	
	@NotEmpty(message = "Title is required") 
	private String title;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
    
    @NotEmpty(message = "ISBN is required")
    @Pattern(regexp = "\\d{3}-\\d{10}", message = "ISBN must be in the format xxx-xxxxxxxxxx")
    private String isbn;
    
    @Positive(message = "Publication year must be a positive number")
    @Digits(integer = 4, fraction = 0, message = "Publication year must be a four-digit number")    
    private int publicationYear;
    
    private boolean isRented;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public boolean isRented() {
		return isRented;
	}
	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}
    
    
}
