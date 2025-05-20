package com.bookstore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String rootWelcome() {
        return "Welcome to the Bookstore API!";
    }

    @GetMapping("/api/welcome")
    public Map<String, String> welcomeApi(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities()
                                     .stream()
                                     .map(GrantedAuthority::getAuthority)
                                     .findFirst()
                                     .orElse("N/A");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome " + username + "!");
        response.put("role", role);
        return response;
    }
}
