package com.veda.online.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veda.online.adapter.DbEntityToModel;
import com.veda.online.entity.BookingEntity;
import com.veda.online.entity.BookingTypeDetails;
import com.veda.online.entity.RowEntity;
import com.veda.online.entity.SeatEntity;
import com.veda.online.model.RegDetails;
import com.veda.online.model.Registration;
import com.veda.online.model.RowDetails;
import com.veda.online.repository.BookingRepo;
import com.veda.online.repository.BookingRowRepo;
import com.veda.online.repository.BookingSeatRepo;
import com.veda.online.repository.BookingTypeDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final BookingRepo bookingRepo;
    private final BookingTypeDetailsRepo bookingTypeDetailsRepo;
    private final BookingRowRepo bookingRowRepo;
    private final BookingSeatRepo bookingSeatRepo;

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("C:\\git-hub\\ticket-booking-service\\src\\main\\resources\\registration.json");
        Registration registration = mapper.readValue(file,
                Registration.class);
        saveData(registration);
    }

    private void saveData(Registration registration) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingType(registration.getType());
        BookingEntity newOne = bookingRepo.save(bookingEntity);
        System.out.println("Saved " + newOne.getId() + " type" + newOne.getBookingType());
        registration.getDetails().forEach(regDetails -> {
            BookingTypeDetails bookingTypeDetails = new BookingTypeDetails();
            bookingTypeDetails.setTypeId(regDetails.getTypeId());
            bookingTypeDetails.setParentId(newOne.getId());
            bookingTypeDetails.setAvailableSeats(regDetails.getAvailableSeats());
            bookingTypeDetails.setTotalSeats(regDetails.getTotalSeats());
            BookingTypeDetails typeDetails = bookingTypeDetailsRepo.save(bookingTypeDetails);
            System.out.println("Saved " + typeDetails.getId() + " type" + typeDetails.getTypeId() + " Parent-id=" + typeDetails.getParentId());
            regDetails.getRows().forEach(rowDetails -> {
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
            });
        });

    }

    public List<RegDetails> finalAllByType(String name) {
        log.info("Finding Data by==>{}", name);
        List<BookingTypeDetails> list = bookingTypeDetailsRepo.findByParentId(
                bookingRepo.findByBookingType(name).getId());
        return list.stream().map(DbEntityToModel::convert).collect(Collectors.toList());
    }

    public List<RowDetails> finalAllByTypeId(String id) {
        log.info("Finding Data by==>{}", id);
        BookingTypeDetails typeDetails = bookingTypeDetailsRepo.findByTypeId(id);
        return bookingRowRepo.findByParentId(typeDetails.getId())
                .stream().map(rowEntity -> {
                    List<SeatEntity> seats = bookingSeatRepo.findByParentId(rowEntity.getId());
                    return DbEntityToModel.convertToRows(rowEntity, seats);
                }).collect(Collectors.toList());
    }
}
