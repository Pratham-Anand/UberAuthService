package com.example.UberAuthService.controllers;

import com.example.UberAuthService.dtos.AuthRequestDto;
import com.example.UberAuthService.dtos.AuthResponseDto;
import com.example.UberAuthService.dtos.PassengerSignUpRequestDto;
import com.example.UberAuthService.services.AuthService;
import com.example.UberAuthService.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import com.example.UberAuthService.dtos.PassengerDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${cookie.expiry}")
    private int cookieExpiry;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final AuthService authService;

    public AuthController(AuthService authService,AuthenticationManager authenticationManager,JwtService jwtService){
        this.authService=authService;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }



    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passengerSignUpRequestDto){

        PassengerDto response=authService.signup(passengerSignUpRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse response) {
//
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),authRequestDto.getPassword()));
        if (authentication.isAuthenticated()){
            String jwtToken =jwtService.createToken(authRequestDto.getEmail());

            ResponseCookie cookie=ResponseCookie.from("JwtToken",jwtToken)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(cookieExpiry)
                    .build();

//        return null;
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return new ResponseEntity<>(AuthResponseDto.builder().success(true).build(), HttpStatus.OK);
    } else {
        throw new UsernameNotFoundException("User not found");
    }
}

@GetMapping("/validate")
    public ResponseEntity <?> validate(HttpServletRequest request,HttpServletResponse response){
    System.out.println("Inside validate controller");

    for(Cookie cookie:request.getCookies()){
        System.out.println(cookie.getName()+ " "+cookie.getValue());
    }

    return new ResponseEntity<>("Success",HttpStatus.OK);
}


}
