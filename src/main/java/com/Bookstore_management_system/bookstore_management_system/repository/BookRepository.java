package com.Bookstore_management_system.bookstore_management_system.repository;

import com.Bookstore_management_system.bookstore_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // The `JpaRepository` already provides a `findById` method, so no need for custom queries.
}
