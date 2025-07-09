package com.example.UberAuthService.dtos;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PassengerSignUpRequestDto {

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

}
