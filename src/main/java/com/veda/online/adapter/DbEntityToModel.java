package com.veda.online.adapter;

import com.veda.online.entity.BookingTypeDetails;
import com.veda.online.entity.RowEntity;
import com.veda.online.entity.SeatEntity;
import com.veda.online.model.RegDetails;
import com.veda.online.model.RowDetails;
import com.veda.online.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class DbEntityToModel {
    public static RegDetails convert(BookingTypeDetails bookingTypeDetails) {
        RegDetails regDetails = new RegDetails();
        regDetails.setAvailableSeats(bookingTypeDetails.getAvailableSeats());
        regDetails.setTypeId(bookingTypeDetails.getTypeId());
        regDetails.setTotalSeats(bookingTypeDetails.getTotalSeats());
        return regDetails;
    }

    public static RowDetails convertToRows(RowEntity rowDetails, List<SeatEntity> seats) {
        RowDetails rowDetails1 = new RowDetails();
        rowDetails1.setRowId(rowDetails.getRowId());
        rowDetails1.setSeats(new ArrayList<>());
        seats.forEach(seatEntity -> {
            Seat seat = new Seat();
            seat.setSeatNumber(seatEntity.getSeatNumber());
            seat.setAvailable(seatEntity.getIsAvailable());
            rowDetails1.getSeats().add(seat);
        });
        return rowDetails1;
    }
}
