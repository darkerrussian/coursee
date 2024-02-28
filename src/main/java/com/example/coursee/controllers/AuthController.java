package com.example.coursee.controllers;


import com.example.coursee.AuthService;
import com.example.coursee.dto.JwtRequest;
import com.example.coursee.dto.RegistrUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest){

        return authService.createAuthToken(jwtRequest);


    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrUserDto registrUserDto){

        return authService.createNewUser(registrUserDto);



    }
}
