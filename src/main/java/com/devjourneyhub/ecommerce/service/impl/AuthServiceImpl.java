package com.devjourneyhub.ecommerce.service.impl;

import com.devjourneyhub.ecommerce.dto.AuthRequest;
import com.devjourneyhub.ecommerce.dto.AuthResponse;
import com.devjourneyhub.ecommerce.dto.RegisterRequest;
import com.devjourneyhub.ecommerce.entity.Role;
import com.devjourneyhub.ecommerce.entity.User;
import com.devjourneyhub.ecommerce.repository.UserRepository;
import com.devjourneyhub.ecommerce.service.AuthService;
import com.devjourneyhub.ecommerce.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // avoid duplicate username
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        UserDetails ud = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtils.generateToken(ud);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            // Attempt authentication (this will throw if credentials invalid)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid credentials");
        }

        UserDetails ud = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.generateToken(ud);
        return new AuthResponse(token);
    }
}