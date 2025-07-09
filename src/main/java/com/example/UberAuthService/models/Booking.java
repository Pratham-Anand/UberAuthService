package com.example.UberAuthService.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" , "driver","passenger","createdAt"})

public class Booking extends com.example.UberAuthService.models.BaseModel {



    @Column(name ="booking_status")
    @Enumerated(value= EnumType.STRING)
    private BookingStatus bookingstatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private  Date endTime;

    private Long TotalDistance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;

}
