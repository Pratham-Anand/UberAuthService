package com.example.UberAuthService.controllers;

import com.example.UberAuthService.dtos.PassengerSignUpRequestDto;
import com.example.UberAuthService.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import com.example.UberAuthService.dtos.PassengerDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }



    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passengerSignUpRequestDto){

        PassengerDto response=authService.signup(passengerSignUpRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signIn() {
//
//        return null;
        return new ResponseEntity<>(10, HttpStatus.CREATED);
    }






}
