package com.example.UberAuthService.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "booking_review")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends com.example.UberAuthService.models.BaseModel {

    @Column(nullable=false)
    private String content;

    private Double rating;

    @OneToOne(cascade={CascadeType.ALL,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking;  //one to one relation between booking and review;

    @Override
    public String toString(){
        return "Review: " + this.content + " " + this.rating + " " + " booking: " + this.booking.getId() + " " + this.createdAt;

    }

}
