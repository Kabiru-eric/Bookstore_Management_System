package com.Bookstore_management_system.bookstore_management_system.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {

    @NotEmpty(message = "Title is mandatory")
    private String title;

    @NotEmpty(message = "Author is mandatory")
    private String author;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String publishedDate;
}