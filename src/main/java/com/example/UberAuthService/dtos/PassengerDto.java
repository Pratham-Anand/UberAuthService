package com.example.UberAuthService.dtos;

import com.example.UberAuthService.models.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private String id;
   private   String name;
   private   String password;
   private  String email;
   private  String phoneNumber;
   private Date createdAt;

   public static PassengerDto from(Passenger p) {
      return PassengerDto.builder()
              .id(p.getId().toString())
              .createdAt(p.getCreatedAt())
              .email(p.getEmail())
              .password(p.getPassword())
              .phoneNumber(p.getPhoneNumber())
              .name(p.getName())
              .build();
   }




}
