package com.bookstore.config;

import com.bookstore.config.JwtAuthFilter;
import com.bookstore.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // ✅ Public endpoints including Swagger URLs
                .requestMatchers(
                    "/",
                    "/api/welcome",
                    "/api/auth/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/v3/api-docs.yaml",
                    "/v3/api-docs.json",
                    "/frontend/**",
                    "/frontend/*.html",
                    "/frontend/js/**"
                ).permitAll()

                // ✅ View all books - allowed for all roles
                .requestMatchers("/api/books/**").hasAnyRole("ADMIN", "MANAGER", "CUSTOMER")

                // ✅ Admin-only endpoints
                .requestMatchers("/api/admin/books/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/authors/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/orders/**").hasRole("ADMIN")

                // ✅ Author APIs
                .requestMatchers("/api/authors/**").hasRole("ADMIN")

                // ✅ Manager endpoints
                .requestMatchers("/api/manager/books/**").hasRole("MANAGER")
                .requestMatchers("/api/manager/orders/**").hasRole("MANAGER")

                // ✅ Customer endpoints
                .requestMatchers("/api/customer/**").hasRole("CUSTOMER")

                // ✅ Catch-all for authenticated users
                .anyRequest().authenticated()
            )

            // ✅ Stateless session config for JWT
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // ✅ Attach custom authentication provider and JWT filter
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
