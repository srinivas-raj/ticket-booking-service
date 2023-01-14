package com.veda.online.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Seat {
   private Integer seatNumber;
   private boolean available;
}
