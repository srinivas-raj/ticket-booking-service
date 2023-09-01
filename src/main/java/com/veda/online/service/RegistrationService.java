package com.veda.online.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veda.online.adapter.DbEntityToModel;
import com.veda.online.entity.BookingEntity;
import com.veda.online.entity.BookingTypeDetails;
import com.veda.online.entity.RowEntity;
import com.veda.online.entity.SeatEntity;
import com.veda.online.exception.TicketSelectedException;
import com.veda.online.model.RegDetails;
import com.veda.online.model.Registration;
import com.veda.online.model.RowDetails;
import com.veda.online.model.TicketDetails;
import com.veda.online.repository.BookingRepo;
import com.veda.online.repository.BookingRowRepo;
import com.veda.online.repository.BookingSeatRepo;
import com.veda.online.repository.BookingTypeDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final BookingRepo bookingRepo;
    private final BookingTypeDetailsRepo bookingTypeDetailsRepo;
    //private final BookingRowRepo bookingRowRepo;
  //  private final BookingSeatRepo bookingSeatRepo;

    private Map<String, TicketDetails> lockedData = new HashMap<>();

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("C:\\git-hub\\ticket-booking-service\\src\\main\\resources\\registration.json");
        List<Registration> registration = mapper.readValue(file,
                new TypeReference<List<Registration>>() {
                });
        registration.forEach(registration1 -> {
            saveData(registration1);
        });
    }

    private void saveData(Registration registration) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingType(registration.getType());
        Integer newOne = bookingRepo.save(bookingEntity);
        System.out.println("Saved " + bookingEntity.getId() + " type" + registration.getType());
        registration.getDetails().forEach(regDetails -> {
            BookingTypeDetails bookingTypeDetails = new BookingTypeDetails();
            bookingTypeDetails.setTypeId(regDetails.getTypeId());
            bookingTypeDetails.setParentId(bookingEntity.getId());
            bookingTypeDetails.setAvailableSeats(regDetails.getAvailableSeats());
            bookingTypeDetails.setTotalSeats(regDetails.getTotalSeats());
            Integer typeDetails = bookingTypeDetailsRepo.save(bookingTypeDetails);
            System.out.println("Saved " + bookingTypeDetails.getId() + " type" + regDetails.getTypeId()
                    + " Parent-id=" + newOne);
            /*regDetails.getRows().forEach(rowDetails -> {
                RowEntity rowEntity = new RowEntity();
                rowEntity.setRowId(rowDetails.getRowId());
                rowEntity.setParentId(typeDetails.getId());
                RowEntity id = bookingRowRepo.save(rowEntity);
                List<SeatEntity> seatEntities = new ArrayList<>();
                rowDetails.getSeats().forEach(seat -> {
                    SeatEntity seatEntity = new SeatEntity();
                    seatEntity.setSeatNumber(seat.getSeatNumber());
                    seatEntity.setIsAvailable(seat.isAvailable());
                    seatEntity.setParentId(id.getId());
                    seatEntities.add(seatEntity);
                });
                bookingSeatRepo.saveAll(seatEntities);
            });*/
        });

    }

    public BookingEntity findByName(String name){
        return bookingRepo.findByBookingType(name);
    }

   public List<RegDetails> finalAllByType(String name) {
        log.info("Finding Data by==>{}", name);
        List<BookingTypeDetails> list = bookingTypeDetailsRepo.findByParentId(
                bookingRepo.findByBookingType(name).getId());
        return list.stream().map(typeData -> DbEntityToModel.convert(typeData, lockedData)).collect(Collectors.toList());
    }
/*
    public List<RowDetails> finalAllByTypeId(String id) {
        log.info("Finding Data by==>{}", id);
        BookingTypeDetails typeDetails = bookingTypeDetailsRepo.findByTypeId(id);
        return bookingRowRepo.findByParentId(typeDetails.getId())
                .stream().map(rowEntity -> {
                    List<SeatEntity> seats = bookingSeatRepo.findByParentId(rowEntity.getId());
                    return DbEntityToModel.convertToRows(rowEntity, seats, lockedData, id);
                }).collect(Collectors.toList());
    }

    public String lockTickets(String id, List<TicketDetails> ticketDetails) {

        ticketDetails.forEach(ticketDetails1 -> {
            synchronized (this) {
                String key = getKey(id, ticketDetails1);
                if (null == lockedData.get(key)) {
                    lockedData.put(key, ticketDetails1);
                } else {
                    throw new TicketSelectedException("Select another ticket");
                }
            }
        });

        return "success";
    }

    public String unLock(String id, List<TicketDetails> ticketDetails) {
        ticketDetails.forEach(ticketDetails1 -> {
            lockedData.remove(getKey(id, ticketDetails1));
        });
        return "success";
    }

    private static String getKey(String id, TicketDetails ticketDetails1) {
        return id + ticketDetails1.getRowId() + ticketDetails1.getSeatNumber();
    }

    public String bookTicket(String id, List<TicketDetails> ticketDetails) {
        //Do Payment
        //if payment success then lock seat in DB and update avaiable count
        //Remove locked data from cache/hash map
        return "success";
    }*/
}
