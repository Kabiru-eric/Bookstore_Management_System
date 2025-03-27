package com.Bookstore_management_system.bookstore_management_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 System.out.println("Bookstore management system has started");
	}
}
