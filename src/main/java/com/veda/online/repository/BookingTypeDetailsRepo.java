package com.veda.online.repository;

import com.veda.online.entity.BookingTypeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingTypeDetailsRepo extends JpaRepository<
        BookingTypeDetails, Long> {
    List<BookingTypeDetails> findByParentId(Long byBookingType);
    BookingTypeDetails findByTypeId(String id);
}
