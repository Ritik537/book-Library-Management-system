package com.example.librarymanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.Models.Book;
import com.example.librarymanagement.Repositories.BookRepository;

@Service
public class BookService {
	
	
	private final BookRepository bookRepository;
	
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book createBook(Book book) {
        
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
    
    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsRentedFalse();
    }

    public List<Book> getRentedBooks() {
        return bookRepository.findByIsRentedTrue();
    }
    
    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
