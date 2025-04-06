package com.Bookstore_management_system.bookstore_management_system.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")

public class AuthController {

    //@Autowired
    private  CustomService customService;

    public AuthController( CustomService customService) {
        this.customService = customService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam Role role) {
        return ResponseEntity.ok(customService.registerUser(username, password, role));
    }
}
