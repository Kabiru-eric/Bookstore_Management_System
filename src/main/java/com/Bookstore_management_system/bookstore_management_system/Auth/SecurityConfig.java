package com.Bookstore_management_system.bookstore_management_system.Auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (adjust depending on your needs)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/v3/api-docs.json",
                                "/webjars/**",
                                "/actuator/**",
                                "/api/v1/**" ,
                                "/api/auth/**",
                                "/api/customers/**",
                                "/api/loans/schedules/**"
                        ).permitAll()
                        .requestMatchers("/register").permitAll()  // Allow public access to registration endpoint
                        .requestMatchers("/books").hasAnyRole("ADMIN", "USER")  // Secure books endpoint for admins and users
                        .anyRequest().authenticated()  // Secure all other endpoints
                )
                .httpBasic(Customizer.withDefaults());  // Enable Basic Authentication
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService; // UserDetailsService implementation (UserService)
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder for secure password hashing
    }
}
