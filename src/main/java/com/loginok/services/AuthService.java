package com.loginok.services;

import com.loginok.dtos.request.LoginRequest;
import com.loginok.dtos.request.RegisterRequest;
import com.loginok.dtos.response.AuthResponse;
import com.loginok.models.ERole;
import com.loginok.models.User;
import com.loginok.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest login){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        UserDetails user = userRepository.findByUsername(login.getUsername()).orElseThrow();

        String token = jwtService.getTokenService(user);


        return AuthResponse
                .builder()
                .token(token)
                .build();
    }

    public  AuthResponse register(RegisterRequest register){
        User user = User.builder()
                .username(register.getUsername())
                .password(passwordEncoder.encode(register.getPassword()))
                .firstname(register.getFirstname())
                .lastname(register.getFirstname())
                .country(register.getCountry())
                .role(ERole.USER)
                .build();

        userRepository.save(user);

        return AuthResponse
                .builder()
                .token(jwtService.getTokenService(user))
                .build();
    }
}
