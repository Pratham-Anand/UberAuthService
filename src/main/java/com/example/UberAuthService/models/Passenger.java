package com.example.UberAuthService.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import  lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" , "bookings"})
public class Passenger extends com.example.UberAuthService.models.BaseModel {

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String  email;

    @Column(nullable=false)
    private String phoneNumber;

    @Column(nullable=false)
    private String Password;

    @OneToMany(mappedBy = "passenger",fetch = FetchType.LAZY)
    private List<Booking> bookings =new ArrayList<>();
}
