package com.bookstore.controller;

import com.bookstore.dto.AuthResponse;
import com.bookstore.dto.RegisterRequest;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.dto.LoginRequest; // ✅ Import LoginRequest
import com.bookstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Autowired
    private AuthorRepository authorRepository;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) { // ✅ Fix type
        return ResponseEntity.ok(authService.login(request)); // ✅ Use directly
    }
}