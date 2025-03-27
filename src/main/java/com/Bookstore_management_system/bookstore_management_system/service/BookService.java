package  com.Bookstore_management_system.bookstore_management_system.service;

import com.Bookstore_management_system.bookstore_management_system.dto.BookDTO;
import com.Bookstore_management_system.bookstore_management_system.exception.BookNotFoundException;
import com.Bookstore_management_system.bookstore_management_system.model.Book;
import com.Bookstore_management_system.bookstore_management_system.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Create a new book
    @Transactional
    public Book createBook(BookDTO bookDTO) {
        // Creating a new Book instance and setting properties from DTO
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setPublishedDate(bookDTO.getPublishedDate());

        return bookRepository.save(book);
    }
    // Update an existing book by ID
    @Transactional
    public Book updateBook(Long id, BookDTO bookDTO) {
        // Check if the book exists
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found."));

        // If the book exists, update its properties
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setPrice(bookDTO.getPrice());
        existingBook.setPublishedDate(bookDTO.getPublishedDate());

        // Save and return the updated book
        return bookRepository.save(existingBook);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found."));
    }
    // Get all books with pagination and sorting
    public Page<Book> getAllBooks(int page, int size, String sortBy) {
        // Create Pageable object with page, size, and sortBy parameters
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageRequest);  // Return paginated and sorted books
    }

    // Delete a book by ID
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with id " + id + " not found.");
        }
        bookRepository.deleteById(id);
    }
}