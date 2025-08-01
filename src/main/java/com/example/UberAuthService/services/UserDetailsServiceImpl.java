package com.example.UberAuthService.services;


import com.example.UberAuthService.helpers.AuthPassengerDetails;
import com.example.UberAuthService.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


//    private  final PassengerRepository passengerRepository;
//
//    public UserDetailsServiceImpl(PassengerRepository passengerRepository){
//        this.passengerRepository=passengerRepository;
//    }

    @Autowired
    private PassengerRepository passengerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger=passengerRepository.findPassengerByEmail(email);     //email is the username here.

        if(passenger.isPresent()){
            return new AuthPassengerDetails(passenger.get());
        }
        else{
            throw new UsernameNotFoundException("Cannot find the Passenger by the given Email");
        }
    }
}
