package com.bookstore.service;

import com.bookstore.dto.AuthResponse;
import com.bookstore.dto.LoginRequest;
import com.bookstore.dto.RegisterRequest;
import com.bookstore.entity.User;
import com.bookstore.entity.UserRole;
import com.bookstore.repository.UserRepository;
import com.bookstore.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        UserRole role = request.getRole();

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);

        UserPrincipal userPrincipal = new UserPrincipal(user);
        String jwtToken = jwtService.generateToken(userPrincipal);

        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        UserPrincipal userPrincipal = new UserPrincipal(user);
        String jwtToken = jwtService.generateToken(userPrincipal);

        return new AuthResponse(jwtToken);
    }
}
