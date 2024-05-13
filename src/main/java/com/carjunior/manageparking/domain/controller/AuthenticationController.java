package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.authenticate.JwtDto;
import com.carjunior.manageparking.domain.dto.authenticate.UserLoginDto;
import com.carjunior.manageparking.domain.service.authentication.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("authentication")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<JwtDto> authenticate(@Valid @RequestBody UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
        );
        return ResponseEntity.ok(JwtDto.builder()
                .token(jwtService.generateToken(userLoginDto.getUsername()))
                .build()
        );
    }

}
