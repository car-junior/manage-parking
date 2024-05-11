package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.authenticate.UserLoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("authentication")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<String> authenticate(@Valid @RequestBody UserLoginDto userLoginDto) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
        );
        return null;
    }

}
