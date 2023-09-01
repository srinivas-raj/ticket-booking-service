package com.veda.online.entity;

import lombok.Data;

@Data
public class SeatEntity {

    private Long id;
    private Integer seatNumber;
    private Boolean isAvailable;
    private Long parentId;
}
