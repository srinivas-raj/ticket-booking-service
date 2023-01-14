package com.veda.online.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "booking_type_details")
@Data
public class BookingTypeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type_id", nullable = false)
    private String typeId;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "avl_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

}
