package com.bookstore.controller;
import org.springframework.web.bind.annotation.*;

import com.bookstore.repository.UserRepository;
import com.bookstore.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
    
}
