package com.veda.online.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "seat")
@Data
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "available", nullable = false)
    private Boolean isAvailable;

    //same as row entity id
    @Column(name = "parent_id", nullable = false)
    private Long parentId;
}
