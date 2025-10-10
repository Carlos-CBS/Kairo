package com.Kario.Services.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Kario.DTOs.Auth_RegisterUser.AuthenticationRequest;
import com.Kario.DTOs.Auth_RegisterUser.AuthenticationResponse;
import com.Kario.DTOs.Auth_RegisterUser.RegisterRequest;
import com.Kario.Exceptions.ResourceAlreadyExistsException;
import com.Kario.Models.User;
import com.Kario.Models.Enums.Role;
import com.Kario.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email already in used");
        }

        var user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getEmail(), request.getPassword()
    ));

    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
    .token(jwtToken).build();    
    }
    
}
