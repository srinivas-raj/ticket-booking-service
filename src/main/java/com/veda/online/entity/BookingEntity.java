package com.veda.online.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "booking_parent")
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bookingType", nullable = false)
    private String bookingType;

}
