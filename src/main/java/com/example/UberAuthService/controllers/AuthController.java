package com.example.UberAuthService.controllers;

import com.example.UberAuthService.dtos.PassengerSignUpRequestDto;
import org.springframework.http.ResponseEntity;
import com.example.UberAuthService.dtos.PassengerDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passengerSignUpRequestDto){

        return null;
    }






}
