package com.example.tripadvisor.service;

import com.example.tripadvisor.config.JwtService;
import com.example.tripadvisor.dto.AuthRequest;
import com.example.tripadvisor.dto.AuthResponse;
import com.example.tripadvisor.dto.RegisterRequest;
import com.example.tripadvisor.model.Role;
import com.example.tripadvisor.model.User;
import com.example.tripadvisor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        try {
            System.out.println("Received registration request: " + request);

            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email already exists");
            }

            // Set default role if not provided
            Role userRole = (request.getRole() != null) ? request.getRole() : Role.USER;
            System.out.println("Assigning role: " + userRole);

            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(userRole)
                    .build();

            System.out.println("Saving user: " + user);
            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);
            System.out.println("Generated JWT token");

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            System.err.println("Registration error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
