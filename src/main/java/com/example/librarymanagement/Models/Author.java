package com.example.librarymanagement.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Author {
	
	@Id
    private Long id;
	
	@NotEmpty(message = "name is required")
    private String name;
	
	@NotEmpty(message = "biography is required")
    private String biography;
    
    @OneToMany(mappedBy = "author", orphanRemoval = true)
	private List<Book> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
