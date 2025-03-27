package com.Bookstore_management_system.bookstore_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    private String author;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String publishedDate;
}
