package com.veda.online.adapter;

import com.veda.online.entity.BookingTypeDetails;
import com.veda.online.entity.RowEntity;
import com.veda.online.entity.SeatEntity;
import com.veda.online.model.RegDetails;
import com.veda.online.model.RowDetails;
import com.veda.online.model.Seat;
import com.veda.online.model.TicketDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DbEntityToModel {
    public static RegDetails convert(BookingTypeDetails bookingTypeDetails,Map<String,TicketDetails> lockedData) {
        Integer lockedCount=lockedData.keySet().stream().filter(key->key.contains(
                bookingTypeDetails.getTypeId())).collect(Collectors.toList()).size();
        RegDetails regDetails = new RegDetails();
        regDetails.setAvailableSeats(bookingTypeDetails.getAvailableSeats()-lockedCount);
        regDetails.setTypeId(bookingTypeDetails.getTypeId());
        regDetails.setTotalSeats(bookingTypeDetails.getTotalSeats());
        return regDetails;
    }

    public static RowDetails convertToRows(RowEntity rowEntity, List<SeatEntity> seats,
                                           Map<String, TicketDetails> lockedData, String id) {
        RowDetails rowDetails1 = new RowDetails();
        rowDetails1.setRowId(rowEntity.getRowId());
        rowDetails1.setSeats(new ArrayList<>());
        seats.forEach(seatEntity -> {
            Seat seat = new Seat();
            seat.setSeatNumber(seatEntity.getSeatNumber());
            seat.setAvailable(lockedData.get(buildKey(id, rowEntity.getRowId(), seatEntity.getSeatNumber())) == null ?
                    true : false);
            rowDetails1.getSeats().add(seat);
        });
        return rowDetails1;
    }

    private static String buildKey(String id, String rowId, Integer seatNumber) {
        return id + rowId + seatNumber;
    }
}
