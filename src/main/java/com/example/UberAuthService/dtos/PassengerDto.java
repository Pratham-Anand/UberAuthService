package com.example.UberAuthService.dtos;

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




}
