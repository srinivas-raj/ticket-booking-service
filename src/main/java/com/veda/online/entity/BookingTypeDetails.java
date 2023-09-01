package com.veda.online.entity;

import lombok.Data;


@Data
public class BookingTypeDetails {

    private Long id;
    private String typeId;
    private Integer totalSeats;
    private Integer availableSeats;
    private Integer parentId;

}
