package com.Kario.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Kario.DTOs.Auth_RegisterUser.AuthenticationRequest;
import com.Kario.DTOs.Auth_RegisterUser.AuthenticationResponse;
import com.Kario.DTOs.Auth_RegisterUser.RegisterRequest;
import com.Kario.Services.Auth.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    
        return ResponseEntity.ok(authService.authenticate(request));
    }
    
    
}
