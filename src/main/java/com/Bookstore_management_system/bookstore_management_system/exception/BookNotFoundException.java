package com.Bookstore_management_system.bookstore_management_system.exception;


public class BookNotFoundException extends RuntimeException {

    // Constructor that accepts a String message
    public BookNotFoundException(String message) {
        super(message);  // Pass the message to the parent RuntimeException constructor
    }
}
