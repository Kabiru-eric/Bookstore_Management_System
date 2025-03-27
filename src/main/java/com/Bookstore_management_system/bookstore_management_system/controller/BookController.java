package com.Bookstore_management_system.bookstore_management_system.controller;

import com.Bookstore_management_system.bookstore_management_system.dto.BookDTO;
import com.Bookstore_management_system.bookstore_management_system.exception.BookNotFoundException;
import com.Bookstore_management_system.bookstore_management_system.model.Book;
import com.Bookstore_management_system.bookstore_management_system.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // POST /books - Add a new book
    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody @Valid BookDTO bookDTO, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            result.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                       .append("; ");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }

        try {
            Book createdBook = bookService.createBook(bookDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the book: " + e.getMessage());
        }
    }


    // PUT /books/{id} - Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody @Valid BookDTO bookDTO, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            result.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }

        try {
            // Call service to update the book
            Book updatedBook = bookService.updateBook(id, bookDTO);
            return ResponseEntity.ok(updatedBook);  // Successful update
        } catch (BookNotFoundException e) {
            // Book not found - Return 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // General error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the book: " + e.getMessage());
        }
    }

    // GET /books - Retrieve all books with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,      // Default page = 0
            @RequestParam(defaultValue = "10") int size,     // Default size = 10
            @RequestParam(defaultValue = "title") String sortBy) {  // Default sortBy = title

        try {
            // Call the service to get the paginated and sorted list of books
            Page<Book> booksPage = bookService.getAllBooks(page, size, sortBy);
            return ResponseEntity.ok(booksPage);  // Return paginated and sorted books
        } catch (Exception e) {
            // Return an error response in case of failure
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // GET /books/{id} - Retrieve a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving the book: " + e.getMessage());
        }
    }

    // DELETE /books/{id} - Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the book: " + e.getMessage());
        }
    }
}