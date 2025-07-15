package com.example.UberAuthService.services;


import com.example.UberAuthService.dtos.PassengerDto;
import com.example.UberAuthService.dtos.PassengerSignUpRequestDto;
import com.example.UberAuthService.repositories.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthService(PassengerRepository passengerRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.passengerRepository=passengerRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public PassengerDto signup(PassengerSignUpRequestDto passengerSignUpRequestDto){
        Passenger passenger= Passenger.builder()
                .name(passengerSignUpRequestDto.getName())
                .email(passengerSignUpRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(passengerSignUpRequestDto.getPassword()))
                .phoneNumber(passengerSignUpRequestDto.getPhoneNumber())
                .build();

        Passenger newPassenger =passengerRepository.save(passenger);
        return PassengerDto.from(newPassenger);

    }
}
